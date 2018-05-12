package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class controller {

    //跳转到上传文件的页面
    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String goUpload() {
        //跳转到 templates 目录下的 upload.html
        return "/upload";
    }

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String showsthAll(){
        return "/index";
    }

    @RequestMapping(value="/testupload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSth(@RequestParam("file") MultipartFile file) {

//        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
//        System.out.println("fileName-->" + fileName);
//        System.out.println("getContentType-->" + contentType);
        try {
            FileUtil.uploadFile(file.getBytes(),fileName);
        } catch (Exception e) {
            // TODO: handle exception
            return "upload warnning";
        }
        return "upload success";
    }
    //处理文件上传
}