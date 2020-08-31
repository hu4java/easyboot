package com.hu4java.generate.controller;

import com.hu4java.generate.helper.CodeHelper;
import com.hu4java.generate.helper.XmlHelper;
import com.hu4java.generate.request.GenerateRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 * @author hu4java
 */
@Slf4j
@RestController
@RequestMapping("/generate")
public class GenerateController {

    /**
     * 生成代码
     * @param request       数据
     * @param response
     * @throws Exception
     */
    @PostMapping("/code")
    public void generate(@RequestBody @Validated GenerateRequest request, HttpServletResponse response) throws Exception {

        // 构建相关代码
        String entity = CodeHelper.buildEntity(request.getJavaPackage(), request);
        String condition = CodeHelper.buildCondition(request.getJavaPackage(), request);
        String mapper = CodeHelper.buildMapper(request.getJavaPackage(), request);
        String service = CodeHelper.buildService(request.getJavaPackage(), request);
        String serviceImpl = CodeHelper.buildServiceImpl(request.getJavaPackage(), request);
        String controller = CodeHelper.buildController(request.getJavaPackage(), request);
        Document document = XmlHelper.buildMapper(request.getJavaPackage(), request);

        // 构造代码文件路径
        String path = request.getJavaPackage().replaceAll("\\.", "/");
        String javaPath = "src/main/java/" + path;
        String entityJavaPath = javaPath + "/entity/" + request.getEntityName() + ".java";
        String mapperJavaPath = javaPath + "/mapper/" + request.getEntityName() + "Mapper.java";
        String serviceJavaPath = javaPath + "/service/" + request.getEntityName() + "Service.java";
        String serviceImplJavaPath = javaPath + "/service/impl/" + request.getEntityName() + "ServiceImpl.java";
        String conditionJavaPath = javaPath + "/condition/" + request.getEntityName() + "Condition.java";
        String controllerJavaPath = javaPath + "/controller/" + request.getEntityName() + "Controller.java";

        String xmlPath = "src/main/resources/mapper/" + request.getModule() + "/" + request.getEntityName() + "Mapper.xml";

        // 生成zip
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(os);

        putFile(zipOutputStream, entity, entityJavaPath);
        putFile(zipOutputStream, condition, conditionJavaPath);
        putFile(zipOutputStream, mapper, mapperJavaPath);
        putFile(zipOutputStream, service, serviceJavaPath);
        putFile(zipOutputStream, serviceImpl, serviceImplJavaPath);
        putFile(zipOutputStream, controller, controllerJavaPath);
        putXmlFile(zipOutputStream, document, xmlPath);
        zipOutputStream.flush();
        zipOutputStream.close();

        // 响应前端下载
        byte[] bytes = os.toByteArray();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename="+ request.getModule() +".zip");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(bytes,response.getOutputStream());

        os.flush();
        os.close();
    }

    /**
     * zip 文件
     * @param zipOutputStream   压缩输出流
     * @param source            代码
     * @param path              源码文件路径
     * @throws IOException      异常
     */
    private void putFile(ZipOutputStream zipOutputStream, String source, String path) throws IOException {
        ZipEntry zipEntry = new ZipEntry(path);
        zipOutputStream.putNextEntry(zipEntry);
        IOUtils.write(source, zipOutputStream, StandardCharsets.UTF_8);
        zipOutputStream.flush();
        zipOutputStream.closeEntry();
    }

    /**
     * 压缩xml文件
     * @param zipOutputStream
     * @param xmlDoc
     * @param path
     * @throws IOException
     */
    private void putXmlFile(ZipOutputStream zipOutputStream, Document xmlDoc, String path) throws IOException {
        ZipEntry zipEntry = new ZipEntry(path);
        zipOutputStream.putNextEntry(zipEntry);

        OutputFormat format = new OutputFormat();
        format.setIndentSize(4);  // 行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setTrimText(false); // 去重空格
        format.setPadText(true);
        format.setNewLineAfterDeclaration(true); // 放置xml文件中第二行为空白行
        XMLWriter xmlWriter= new XMLWriter( zipOutputStream, format);
        xmlWriter.write(xmlDoc);
        zipOutputStream.flush();
        zipOutputStream.closeEntry();
        xmlWriter.close();
    }
}
