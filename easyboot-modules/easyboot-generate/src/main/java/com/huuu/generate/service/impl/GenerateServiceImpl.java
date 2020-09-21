package com.huuu.generate.service.impl;

import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateCodeService;
import com.huuu.generate.service.GenerateService;
import com.huuu.generate.service.GenerateXmlService;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author chenzhenhu
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    private static List<GenerateCodeService> codeServiceList = new ArrayList<>();

    @PostConstruct
    public void init() {
        codeServiceList.add(new GenerateEntityCodeImpl());
        codeServiceList.add(new GenerateConditionCodeImpl());
        codeServiceList.add(new GenerateMapperCodeImpl());
        codeServiceList.add(new GenerateServiceCodeImpl());
        codeServiceList.add(new GenerateServiceImplCodeImpl());
        codeServiceList.add(new GenerateControllerCodeImpl());
    }

    @Override
    public void generate(GenerateRequest request, ZipOutputStream zipOutputStream) throws Exception {

        for (GenerateCodeService service : codeServiceList) {
            putFile(zipOutputStream, service.generate(request), service.path(request));
        }

        GenerateXmlService generateXmlService = new GenerateMapperXmlServiceImpl();
        Document mapperDocument = generateXmlService.generateXml(request);

        putXmlFile(zipOutputStream, mapperDocument, generateXmlService.path(request));
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
