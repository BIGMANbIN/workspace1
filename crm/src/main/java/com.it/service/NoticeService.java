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

    /**
     * 根据搜索条件查询notice集合
     * @param param
     * @return
     */
    public List<Notice> findByParam(Map<String, Object> param) {

        return noticeMapper.findByParam(param);
    }

    /**
     * 查询公告的总数量
     * @return
     */
    public Long count() {

        return noticeMapper.count();
    }

    /**
     *跟姐主键id查找公告内容
     * @param id
     * @return
     */
    public Notice findById(Integer id) {
        return noticeMapper.findById(id);
    }
}
