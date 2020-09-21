package com.huuu.web.generate.controller;

import com.huuu.generate.request.GenerateRequest;
import com.huuu.generate.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成-生成代码
 * @author huuu
 */
@Slf4j
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    /**
     * 生成代码
     * @param request       数据
     * @param response
     * @throws Exception
     */
    @PostMapping("/code")
    @RequiresPermissions("tools:generate:code")
    public void generate(@RequestBody @Validated GenerateRequest request, HttpServletResponse response) throws Exception {

        // 生成zip
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(os);

        generateService.generate(request, zipOutputStream);

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


}
