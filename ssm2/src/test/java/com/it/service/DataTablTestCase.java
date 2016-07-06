package com.it.service;


import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DataTablTestCase {

    Logger logger = LoggerFactory.getLogger(BookServiceTestCase.class);
    @Inject
    private BookService bookService;
    @Test
    public void testFindByDataTable(){
        Map<String,Object> param = Maps.newHashMap();

        param.put("start",1);
        param.put("length",10);
        param.put("keyword","JavaScript权威指南");

        bookService.findByDtaTables(param);
        logger.debug("param:{}",param);

    }
}
