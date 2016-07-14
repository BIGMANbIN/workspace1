package com.it.mapper;


import com.it.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {

    List<Customer> findByParam(Map<String, Object> params);

    Long count();

    Long countByParam(Map<String, Object> params);

    List<Customer> findByType(String type);

    Customer findById(Integer id);

    void add(Customer customer);

    List<Customer> findCompanyLikeName(String keyword);

    void del(Integer id);

    List<Customer> finByCompanyId(Integer id);

    void update();
}
