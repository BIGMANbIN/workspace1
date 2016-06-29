package com.it.service;


import com.it.dao.MessageDao;
import com.it.entity.Message;

import java.util.List;

public class MessageService {

    private MessageDao messageDao = new MessageDao();

    public List<Message> findAll(){

        return messageDao.findAll();
    }

    public List<Message> findByMaxId(String maxId){

        return messageDao.findByMaxId(maxId);
    }
}
