package com.it.dto;

import java.util.List;

/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class DataTablesResult<T> {

    private String draw;
    private List<T> data;
    private Long recordsTotal;
    private Long recordsFilterer;

    public DataTablesResult(String draw, List<T> data, Long recordsTotal, Long getRecordsFilterer) {
        this.draw = draw;
        this.data = data;
        this.recordsTotal = recordsTotal;
        this.recordsFilterer = getRecordsFilterer;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFilterer() {
        return recordsFilterer;
    }

    public void setRecordsFilterer(Long recordsFilterer) {
        this.recordsFilterer = recordsFilterer;
    }
}
