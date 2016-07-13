package com.it.mapper;


import com.it.pojo.Document;

import java.util.List;

public interface DocumentMapper {

    List<Document> findByFid(Integer fid);


    void saveDir(Document document);

    Document findById(Integer id);

}
