package com.it.service;


import com.google.common.collect.Maps;
import com.it.mapper.CustomerMapper;
import com.it.pojo.Customer;
import com.it.util.ShiroUtil;
import com.it.util.Strings;
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
        if(ShiroUtil.isEmployee()){
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.findByParam(params);
    }

    /**
     *客户总数
     *@return
     */
    public Long count() {
        Map<String,Object> params = Maps.newHashMap();
        if(ShiroUtil.isEmployee()){
            params.put("userid",ShiroUtil.getCurrentUserID());
        }

        return customerMapper.count();
    }


    /**
     * 过滤后的客户数量
     * @param params
     * @return
     */
    public Long countByParam(Map<String, Object> params) {
        if(ShiroUtil.isEmployee()){
            params.put("userid",ShiroUtil.getCurrentUserID());
        }

        return customerMapper.countByParam(params);
    }

    /**
     * 查询客户中的所有公司
     * @return
     */
    public List<Customer> findAllCompany(){
        return customerMapper.findByType(Customer.SUSTOMER_TYPE_COMPANY);
    }

    /**
     * 增加新客户
     * @param customer
     */
    public void addCustomer(Customer customer) {
        if(customer.getCompanyid() != null){
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
     * @param id
     */
    @Transactional
    public void delCustomer(Integer id) {
        Customer customer = customerMapper.findById(id);
        if(customer != null){
            if(customer.getType().equals(Customer.SUSTOMER_TYPE_COMPANY)){
                //如果删除的是公司，则查找是否关联客户，如果有关联则设置公司ID和名称为空
                List<Customer> customerList = customerMapper.finByCompanyId(id);
                for (Customer cus : customerList) {
                    cus.setCompanyid(null);
                    cus.setCompanyname(null);
                    customerMapper.update();

                }
            }
            customerMapper.del(id);
        }
    }
}
