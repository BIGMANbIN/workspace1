package com.it.mapper;


import com.it.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    void addNotice(Notice notice);

    List<Notice> findByParam(Map<String, Object> param);

    Long count();

}
