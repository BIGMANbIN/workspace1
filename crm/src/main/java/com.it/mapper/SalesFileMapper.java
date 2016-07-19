package com.it.mapper;


import com.it.pojo.SalesFile;

import java.util.List;

public interface SalesFileMapper {

    void save(SalesFile salesFile);

    SalesFile findById(Integer id);

    List<SalesFile> findBySalesId(Integer salesid);

    void delSalesFile(List<SalesFile> salesFileList);
}
