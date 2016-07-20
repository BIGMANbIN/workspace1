package com.it.service;

import com.it.mapper.CustomerMapper;
import com.it.mapper.SalesMapper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class ChartService {

    @Inject
    private CustomerMapper customerMapper;
    @Inject
    private SalesMapper salesMapper;

    /**
     * 统计区间内新增客户的shuliang
     *
     * @param start
     * @param end
     * @return
     */
    public Long findNewCustomerCount(String start,String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }

        return customerMapper.findNewCustomerCount(start,end);
    }

    /**
     * 统计区间内完成交易的销售机会数量
     *
     * @param start
     * @param end
     * @return
     */
    public Long findSaleCount(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return salesMapper.findStateCount(start,end,"交易完成");
    }


    /**
     * 统计区间内完成交易总额
     *
     * @param start
     * @param end
     * @return
     */
    public Float findSaleMoney(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return salesMapper.findStateMoney(start,end,"交易完成");
    }

    /**
     * 饼图显示销售机会信息
     * @param start
     * @param end
     * @return
     */
    public List<Map<String, Object>> loadPieData(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return salesMapper.countProgress(start,end);
    }

    /**
     * 柱状图显示员工业绩
     * @param start
     * @param end
     * @return
     */
    public List<Map<String, Object>> loadBarData(String start, String end) {
        DateTime now = DateTime.now();
        if(StringUtils.isEmpty(start)) {
            start = now.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        }
        if(StringUtils.isEmpty(end)) {
            end = now.toString("yyyy-MM-dd");
        }
        return salesMapper.totalUserMoney(start,end);
    }
}
