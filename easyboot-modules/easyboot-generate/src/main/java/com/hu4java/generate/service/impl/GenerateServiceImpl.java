package com.hu4java.generate.service.impl;

import com.hu4java.generate.entity.Tpl;
import com.hu4java.generate.request.GenerateRequest;
import com.hu4java.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 * @author hu4java
 */
@Slf4j
@Service
public class GenerateServiceImpl implements GenerateService {


    @Override
    public void generate(GenerateRequest request, ZipOutputStream outputStream) {

        VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.INPUT_ENCODING, StandardCharsets.UTF_8);
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        engine.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE, IncludeRelativePath.class.getName());
        engine.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
//        engine.setProperty("jar.resource.loader.path", "jar:file:template");
        engine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "classpath*:template");
        engine.init();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("data", request);

        List<Tpl> tplList = tplList(request);

        for (Tpl tpl : tplList) {
            StringWriter stringWriter = new StringWriter();
            Template template = engine.getTemplate(tpl.getName(), StandardCharsets.UTF_8.name());
            template.merge(velocityContext, stringWriter);

            try {
                new ZipEntry(tpl.getPath());
                IOUtils.write(stringWriter.toString(), outputStream, StandardCharsets.UTF_8);
                stringWriter.close();
                outputStream.closeEntry();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private List<Tpl> tplList(GenerateRequest request) {

        String path = request.getJavaPackage().replaceAll("\\.", "/");
        String javaPath = "src/main/java/" + path;

        Tpl entityTpl = new Tpl("template/entity.java.vm", javaPath + "/" + request.getEntityName() + ".java");
        Tpl mapperTpl = new Tpl("template/mapper.java", javaPath + "/" + request.getEntityName() + "Mapper.java");
        Tpl serviceTpl = new Tpl("template/service.java", javaPath + "/" + request.getEntityName() + "Service.java");
        Tpl serviceImplTpl = new Tpl("template/serviceImpl.java", javaPath + "/" + request.getEntityName() + "ServiceImpl.java");
        Tpl controllerTpl = new Tpl("template/controller.java", javaPath + "/" + request.getEntityName() + "Controller.java");
        Tpl conditionTpl = new Tpl("template/condition.java", javaPath + "/" + request.getEntityName() + "Condition.java");

        List<Tpl> tplList = new ArrayList<>();
        tplList.add(entityTpl);
        tplList.add(mapperTpl);
        tplList.add(serviceTpl);
        tplList.add(serviceImplTpl);
        tplList.add(controllerTpl);
        tplList.add(conditionTpl);

        return tplList;
    }
}
