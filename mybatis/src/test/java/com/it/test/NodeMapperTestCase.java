package com.it.test;


import com.google.common.collect.Lists;
import com.it.mapper.NodeMapper;
import com.it.pojo.Node;
import com.it.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class NodeMapperTestCase {

    private Logger logger = LoggerFactory.getLogger(NodeMapperTestCase.class);

    @Test
    public void testNodeSave(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        List<Node> nodeList = Lists.newArrayList();

        nodeList.add(new Node("node1"));
        nodeList.add(new Node("node2"));
        nodeList.add(new Node("node3"));
        nodeList.add(new Node("node4"));
        nodeList.add(new Node("node5"));
        nodeList.add(new Node("node6"));

        nodeMapper.batchSave(nodeList);


        sqlSession.close();
    }

    @Test
    public void testFindByIds(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        List<Integer> idList = Lists.newArrayList(1,2,3,4);

        List<Node> nodeList = nodeMapper.findByIds(idList);
        for (Node node: nodeList) {

            logger.debug("{}",node);
        }


        sqlSession.close();
    }

}
