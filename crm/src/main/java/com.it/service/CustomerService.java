package com.it.service;


import com.google.common.collect.Maps;
import com.it.mapper.CustomerMapper;
import com.it.pojo.Customer;
import com.it.util.ShiroUtil;
import com.it.util.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class CustomerService {

    @Inject
    private CustomerMapper customerMapper;


    public List<Customer> findCustomerByParams(Map<String, Object> params) {
        if (ShiroUtil.isEmployee()) {
            params.put("userid", ShiroUtil.getCurrentUserID());
        }
        return customerMapper.findByParam(params);
    }

    /**
     * 客户总数
     *
     * @return
     */
    public Long count() {
        Map<String, Object> params = Maps.newHashMap();
        if (ShiroUtil.isEmployee()) {
            params.put("userid", ShiroUtil.getCurrentUserID());
        }

        return customerMapper.count();
    }


    /**
     * 过滤后的客户数量
     *
     * @param params
     * @return
     */
    public Long countByParam(Map<String, Object> params) {
        if (ShiroUtil.isEmployee()) {
            params.put("userid", ShiroUtil.getCurrentUserID());
        }

        return customerMapper.countByParam(params);
    }

    /**
     * 查询客户中的所有公司
     *
     * @return
     */
    public List<Customer> findAllCompany() {
        return customerMapper.findByType(Customer.CUSTOMER_TYPE_COMPANY);
    }

    /**
     * 增加新客户
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        if (customer.getCompanyid() != null) {
            Customer company = customerMapper.findById(customer.getCompanyid());
            customer.setCompanyname(company.getName());
        }

        customer.setUserid(ShiroUtil.getCurrentUserID());
        customer.setPinyin(Strings.toPinyin(customer.getName()));
        customerMapper.add(customer);
    }

    public List<Customer> findCompanyByKeyword(String keyword) {
        return customerMapper.findCompanyLikeName(keyword);
    }


    /**
     * 根据ID删除客户信息
     *
     * @param id
     */
    @Transactional
    public void delCustomer(Integer id) {
        Customer customer = customerMapper.findById(id);
        if (customer != null) {
            if (customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
                //如果删除的是公司，则查找是否关联客户，如果有关联则设置公司ID和名称为空
                List<Customer> customerList = customerMapper.finByCompanyId(id);
                for (Customer cus : customerList) {
                    cus.setCompanyid(null);
                    cus.setCompanyname(null);
                    customerMapper.update(cus);

                }
            }
            customerMapper.del(id);
        }
    }


    /**
     * 根据ID 查找客户
     *
     * @param id
     * @return
     */
    public Customer findCustomerById(Integer id) {
        return customerMapper.findById(id);
    }


    /**
     * 根据公司ID 查找所有客户
     *
     * @param id
     * @return
     */
    public List<Customer> findCustomerByCompanyId(Integer id) {
        return customerMapper.finByCompanyId(id);
    }

    /**
     * 修改客户
     *
     * @param customer
     */
    public void editCustomer(Customer customer) {
        if (customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
            //找到关联客户，进行修改

            List<Customer> customerList = customerMapper.finByCompanyId(customer.getId());
            for (Customer cus : customerList) {
                cus.setCompanyid(customer.getId());
                cus.setCompanyname(customer.getName());
                customerMapper.update(cus);
            }
        } else {
            if (customer.getCompanyid() != null) {
                Customer company = customerMapper.findById(customer.getCompanyid());
                customer.setCompanyname(company.getName());
            }
        }
        customer.setPinyin(Strings.toPinyin(customer.getName()));
        customerMapper.update(customer);
    }

    /**
     * 将客户公开
     *
     * @param customer
     */
    public void openCustomer(Customer customer) {
        customer.setUserid(null);
        customerMapper.update(customer);
    }

    /**
     * 转交客户
     *
     * @param customer
     * @param userid
     */
    public void moveCustomer(Customer customer, Integer userid) {
        customer.setUserid(userid);
        customerMapper.update(customer);
    }

    /**
     * 将客户信息生成二维码（MECard格式）
     *
     * @param id
     * @return
     */

    public String makeMeCard(Integer id) {
        Customer customer = customerMapper.findById(id);

        StringBuilder mecard = new StringBuilder("MECARD:");
        if (StringUtils.isNotEmpty(customer.getName())) {
            mecard.append("N:" + customer.getName() + ";");
        }
        if (StringUtils.isNotEmpty(customer.getTel())) {
            mecard.append("TEL:" + customer.getTel() + ";");
        }
        if (StringUtils.isNotEmpty(customer.getEmail())) {
            mecard.append("EMAIL:" + customer.getEmail() + ";");
        }
        if (StringUtils.isNotEmpty(customer.getAddress())) {
            mecard.append("ADR:" + customer.getAddress() + ";");
        }
        if (StringUtils.isNotEmpty(customer.getCompanyname())) {
            mecard.append("ORG:" + customer.getCompanyname() + ";");
        }
        mecard.append(";");

        return mecard.toString();
    }
}