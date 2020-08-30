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
 * @author hu4java
 */
@Slf4j
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @PostMapping("/code")
    public void generate(@RequestBody @Validated GenerateRequest request, HttpServletResponse response) throws Exception {
        String entity = CodeHelper.buildEntity(request.getJavaPackage(), request);
        String mapper = CodeHelper.buildMapper(request.getJavaPackage(), request);
        String service = CodeHelper.buildService(request.getJavaPackage(), request);
        String serviceImpl = CodeHelper.buildServiceImpl(request.getJavaPackage(), request);
        String controller = CodeHelper.buildController(request.getJavaPackage(), request);
        Document document = XmlHelper.buildMapper(request.getJavaPackage(), request);


        String path = request.getJavaPackage().replaceAll("\\.", "/");
        String javaPath = "src/main/java/" + path;
        String entityJavaPath = javaPath + "/entity/" + request.getEntityName() + ".java";
        String mapperJavaPath = javaPath + "/mapper/" + request.getEntityName() + "Mapper.java";
        String serviceJavaPath = javaPath + "/service/" + request.getEntityName() + "Service.java";
        String serviceImplJavaPath = javaPath + "/service/impl/" + request.getEntityName() + "ServiceImpl.java";
        String controllerJavaPath = javaPath + "/controller/" + request.getEntityName() + "Controller.java";

        String xmlPath = "src/main/resources/mapper/" + request.getModule() + "/" + request.getEntityName() + "Mapper.xml";

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(os);

        putFile(zipOutputStream, entity, entityJavaPath);
        putFile(zipOutputStream, mapper, mapperJavaPath);
        putFile(zipOutputStream, service, serviceJavaPath);
        putFile(zipOutputStream, serviceImpl, serviceImplJavaPath);
        putFile(zipOutputStream, controller, controllerJavaPath);

        ByteArrayOutputStream xmlOutputStream = new ByteArrayOutputStream();
        OutputFormat format = new OutputFormat();
        format.setIndentSize(4);  // 行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setTrimText(false); // 去重空格
        format.setPadText(true);
        format.setNewLineAfterDeclaration(true); // 放置xml文件中第二行为空白行
        XMLWriter xmlWriter= new XMLWriter( xmlOutputStream, format);
        xmlWriter.write(document);

        putFile(zipOutputStream, IOUtils.toString(xmlOutputStream.toByteArray(), StandardCharsets.UTF_8.name()), xmlPath);
        xmlOutputStream.flush();
        xmlOutputStream.close();
        
        byte[] bytes = os.toByteArray();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\""+ request.getModule() +".zip\"");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(bytes,response.getOutputStream());
        zipOutputStream.flush();
        zipOutputStream.close();
        os.flush();
        os.close();
    }

    private void putFile(ZipOutputStream zipOutputStream, String source, String path) throws IOException {
        ZipEntry zipEntry = new ZipEntry(path);
        zipOutputStream.putNextEntry(zipEntry);
        IOUtils.write(source, zipOutputStream, StandardCharsets.UTF_8);
        zipOutputStream.closeEntry();
    }
}
