package com.it.mapper;


import com.it.pojo.Customer;
import com.it.pojo.Sales;
import org.apache.ibatis.annotations.Param;

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

    void update(Customer cus);

    List<Customer> findAll(@Param("userId") Integer userId);

    Long findNewCustomerCount(@Param("start") String start,@Param("end") String end);
}
