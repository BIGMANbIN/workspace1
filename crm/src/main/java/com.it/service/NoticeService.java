package com.it.service;

import com.it.mapper.NoticeMapper;
import com.it.pojo.Notice;
import com.it.util.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;


import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class NoticeService {

    @Inject
    private NoticeMapper noticeMapper;
    @Value("${imagePath}")
    private String imageSavePth;

    /**
     * 保存新的公告
     *
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
     *
     * @param param
     * @return
     */
    public List<Notice> findByParam(Map<String, Object> param) {

        return noticeMapper.findByParam(param);
    }

    /**
     * 查询公告的总数量
     *
     * @return
     */
    public Long count() {

        return noticeMapper.count();
    }

    /**
     * 跟姐主键id查找公告内容
     *
     * @param id
     * @return
     */
    public Notice findById(Integer id) {
        return noticeMapper.findById(id);
    }

    /**
     * 将在线编辑器中的上传文件保存
     *
     * @param inputStream
     * @param fileName
     * @return
     */
    public String saveImage(InputStream inputStream, String fileName) throws IOException {
        //String exName = filename.substring(filename.lastIndexOf("."));

        String newFileName = UUID.randomUUID().toString();

        FileOutputStream outputStream = new FileOutputStream(newFileName);
        IOUtils.copy(inputStream, outputStream);

        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return "/preview" + newFileName;
    }
}
