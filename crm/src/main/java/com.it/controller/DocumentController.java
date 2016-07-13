package com.it.controller;


import com.google.common.collect.Maps;
import com.it.exception.NotFoundException;
import com.it.pojo.Document;
import com.it.service.DocumentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import javax.inject.Inject;
import java.io.*;
import java.util.List;


@RequestMapping("/doc")
@Controller
public class DocumentController {

    @Inject
    private DocumentService documentService;

    @Value("${imagePath}")
    private String savePath;


    @RequestMapping(method = RequestMethod.GET)
    public String docList(Model model,
                          @RequestParam(required = false, defaultValue = "0") Integer fid) {

        List<Document> documentList = documentService.findBydocFid(fid);
        List<Document> crumbList = documentService.breadCrumb(fid);
        model.addAttribute("documentList", documentList);
        model.addAttribute("crumbList",crumbList);
        model.addAttribute("fid", fid);

        return "/document/doclist";
    }

    /**
     * 保存新建文件夹
     *
     * @return
     */
    @RequestMapping(value = "/dir/new", method = RequestMethod.POST)
    public String saveDir(String name, Integer fid) {
        documentService.saveDir(name, fid);
        return "redirect:/doc?fid=" + fid;


    }

    /**
     * 文件上传
     *
     * @param file
     * @param fid
     * @return
     */

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public String saveFile(MultipartFile file, Integer fid) throws IOException {
        if (file.isEmpty()) {
            throw new NotFoundException();
        } else {
            documentService.saveFile(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), file.getSize(), fid);
        }
        return "success";
    }


    /**
     * 文件下载
     * @param id
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/download/{id:\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Integer id) throws FileNotFoundException, UnsupportedEncodingException {
        Document document = documentService.findDocumentById(id);

        if (document == null) {
            throw new NotFoundException();
        }
        File file = new File(savePath, document.getFilename());
        if (!file.exists()) {
            throw new NotFoundException();
        }

        FileInputStream inputStream = new FileInputStream(file);
        String fileName = document.getName();
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(document.getContexttype()))
                .contentLength(file.length())
                .header("Content-Disposition", "attachment;filename=\"" + fileName + "\"")
                .body(new InputStreamResource(inputStream));
    }


}
