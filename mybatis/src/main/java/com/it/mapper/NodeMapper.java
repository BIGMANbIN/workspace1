package com.it.mapper;


import com.it.pojo.Node;

import java.util.List;

public interface NodeMapper {

    //批量增加
    void batchSave(List<Node> nodeList);

    List<Node> findByIds(List<Integer> idList);

    Node findById(Integer id);
}
