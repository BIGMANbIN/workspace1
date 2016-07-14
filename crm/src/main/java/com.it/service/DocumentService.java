package com.it.service;

import com.google.common.collect.Lists;
import com.it.mapper.DocumentMapper;
import com.it.pojo.Document;
import com.it.util.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named
public class DocumentService {

    @Inject
    private DocumentMapper documentMapper;
    @Value("${imagePath}")
    private String savePath;


    public List<Document> findBydocFid(int fid) {
        return documentMapper.findByFid(fid);
    }

    /**
     * 新建文件夹
     *
     * @param name
     * @param fid
     */
    public void saveDir(String name, Integer fid) {
        Document document = new Document();
        document.setName(name);
        document.setFid(fid);
        document.setCreateuser(ShiroUtil.getCurrentRealName());
        document.setType(Document.TYPE_DIR);

        documentMapper.saveDir(document);
    }


    /**
     * 保存文件
     *
     * @param inputStream      文件输入流
     * @param originalFileName 文件真实名称
     * @param contextType      文件MIME类型
     * @param size             文件大小
     * @param fid              文件级别(父ID)
     * @return
     */
    @Transactional
    public void saveFile(InputStream inputStream, String originalFileName, String contextType, Long size, Integer fid) {

        String extName = "";
        if (originalFileName.lastIndexOf(".") != -1) {
            extName = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String newFileName = UUID.randomUUID().toString() + extName;

        try {
            FileOutputStream outputStream = new FileOutputStream(new File(savePath, newFileName));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Document document = new Document();
        document.setName(originalFileName);
        document.setFid(fid);
        document.setType(Document.TYPE_DOC);
        document.setCreateuser(ShiroUtil.getCurrentRealName());
        document.setContexttype(contextType);
        document.setSize(FileUtils.byteCountToDisplaySize(size));
        document.setFilename(newFileName);

        documentMapper.saveDir(document);
    }

    /**
     * 根据ID获取文件
     *
     * @param id
     * @return
     */
    public Document findDocumentById(Integer id) {
        return documentMapper.findById(id);
    }


    /**
     * 面包屑
     *
     * @param fid
     * @return
     */
    public List<Document> breadCrumb(Integer fid) {
        List<Document> documentList = new ArrayList<>();
        if (fid == 0) {
            return documentList;
        }
        while (fid > 0) {
            Document document = findDocumentById(fid);
            documentList.add(document);
            fid = document.getFid();
        }

        List<Document> documentList1 = Lists.newArrayList();
        for (int i = 0; i < documentList.size(); i++) {
            documentList1.add(documentList.get(documentList.size()-i-1));

        }
        return documentList1;
    }
}
