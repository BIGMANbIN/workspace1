package com.it.service;

import com.it.mapper.NoticeMapper;
import com.it.pojo.Notice;
import com.it.util.ShiroUtil;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class NoticeService {

    @Inject
    private NoticeMapper noticeMapper;

    /**
     *保存新的公告
     * @param notice
     */
    public void saveNotice(Notice notice) {
        notice.setUserid(ShiroUtil.getCurrentUserID());
        notice.setRealname(ShiroUtil.getCurrentRealName());
        noticeMapper.addNotice(notice);

        //TODO 微信通知
    }

    public List<Notice> findByParam(Map<String, Object> param) {

        return noticeMapper.findByParam(param);
    }

    public Long count() {

        return noticeMapper.count();
    }
}
