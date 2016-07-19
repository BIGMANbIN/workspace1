package com.it.mapper;

import com.it.pojo.SalesLog;

import java.util.List;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public interface SalesLogMapper {

    void save(SalesLog salesLog);

    List<SalesLog> findBySalesId(Integer salesId);

    void delSalesLog(List<SalesLog> salesLogList);
}
