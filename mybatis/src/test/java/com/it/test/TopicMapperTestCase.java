package com.it.test;


import com.it.mapper.TopicMapper;
import com.it.pojo.Topic;
import com.it.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicMapperTestCase {

    Logger logger = LoggerFactory.getLogger(TopicMapperTestCase.class);

    @Test
    public void testFindTopicById(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

        Topic topic = topicMapper.findTopicById(1);

        logger.debug("{}", topic);

        logger.debug("UserName:{} UserAvatar:{}",topic.getUser().getUsername(),topic.getUser().getAvatar());
        logger.debug("NodeName:{}",topic.getNode().getNodename());
        sqlSession.close();


    }
}
