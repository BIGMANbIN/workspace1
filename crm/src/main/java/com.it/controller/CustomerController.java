package com.it.controller;


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Customer;
import com.it.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET)
    public String cusList(Model model) {

        model.addAttribute("companyList", customerService.findAllCompany());
        return "/customer/customerlist";
    }

    /**
     * 显示客户列表
     *
     * @return
     */
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Customer> load(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        String length = request.getParameter("length");
        String start = request.getParameter("start");
        String keyword = request.getParameter("search[value]");

        Map<String, Object> params = Maps.newHashMap();
        params.put("start", start);
        params.put("length", length);
        params.put("keyword", keyword);

        List<Customer> customerList = customerService.findCustomerByParams(params);
        Long count = customerService.count();
        Long filterCount = customerService.countByParam(params);

        return new DataTablesResult<>(draw, customerList, count, filterCount);

    }

    /**
     * 显示所有公司信息
     *
     * @return
     */
    @RequestMapping(value = "company.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> showAllCompanyJson() {
        return customerService.findAllCompany();
    }

    /**
     * 新增客户
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String addcus(Customer customer) {
        customerService.addCustomer(customer);
        return "success";
    }


    /**
     * 根据ID 删除客户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{id:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public String delCus(@PathVariable Integer id) {
        customerService.delCustomer(id);
        return "success";
    }
}
