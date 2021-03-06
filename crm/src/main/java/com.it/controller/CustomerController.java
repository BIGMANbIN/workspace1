package com.it.controller;


import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.it.dto.DataTablesResult;
import com.it.exception.ForbiddenException;
import com.it.exception.NotFoundException;
import com.it.pojo.Customer;
import com.it.pojo.Sales;
import com.it.pojo.Task;
import com.it.pojo.User;
import com.it.service.CustomerService;
import com.it.service.SalesService;
import com.it.service.TaskService;
import com.it.service.UserService;
import com.it.util.ShiroUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;
    @Inject
    private UserService userService;
    @Inject
    private SalesService salesService;
    @Inject
    private TaskService taskService;


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
     * 查找所有公司信息
     *
     * @return
     */
    @RequestMapping(value = "companys.json", method = RequestMethod.GET)
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

    /**
     * 显示客户信息
     *
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public String viewCus(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findCustomerById(id);

        if (customer == null) {
            throw new NotFoundException();
        }
        if (customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        model.addAttribute("customer", customer);

        if (customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
            List<Customer> customerList = customerService.findCustomerByCompanyId(id);
            model.addAttribute("customerList", customerList);
        }

        //加载所有员工
        List<User> userList = userService.findAllUser();
        model.addAttribute("userList", userList);

        //加载客户对听的销售机会列表
        List<Sales> salesList = salesService.findSalesByCustId(id);
        model.addAttribute("salesList",salesList);

        return "customer/customerview";
    }


    /**
     * 编辑客户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id:\\d+}.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editCus(@PathVariable Integer id) {
        Customer customer = customerService.findCustomerById(id);
        Map<String, Object> result = Maps.newHashMap();

        if (customer == null) {
            result.put("state", "error");
            result.put("message", "找不到对应的客户");
        } else {
            List<Customer> customerList = customerService.findAllCompany();
            result.put("state", "success");
            result.put("customer", customer);
            result.put("companyList", customerList);
        }
        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(Customer customer) {
        customerService.editCustomer(customer);
        return "success";
    }

    /**
     * 公开客户
     */
    @RequestMapping(value = "/open/{id:\\d+}", method = RequestMethod.GET)
    public String openCustomer(@PathVariable Integer id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID())
                && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.openCustomer(customer);
        return "redirect:/customer/" + id;
    }

    /**
     * 转交客户
     *
     * @param id
     * @param userid
     * @return
     */
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public String moveCustomer(Integer id, Integer userid) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (customer.getUserid() != null && customer.getUserid().equals(ShiroUtil.getCurrentUserID())
                && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.moveCustomer(customer, userid);
        return "redirect:/customer";
    }

    /**
     * 将客户信息生成二维码
     * @param id
     * @param response
     * @throws WriterException
     * @throws IOException
     */
    @RequestMapping(value = "/qrcode/{id:\\d+}.png", method = RequestMethod.GET)
    public void makeQrCoe(@PathVariable Integer id, HttpServletResponse response) throws WriterException, IOException {
        String mecard = customerService.makeMeCard(id);

        Map<EncodeHintType, String> hints = Maps.newHashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(mecard, BarcodeFormat.QR_CODE, 200, 200, hints);

        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 给关联客户添加待办事项
     * @param task
     * @param hour
     * @param min
     * @return
     */
    @RequestMapping(value = "/task/new",method = RequestMethod.POST)
    @ResponseBody
    public String newTask(Task task,String hour,String min){
        taskService.saveTask(task, hour, min);
        return "redirect:/customer"+task.getCustid();
    }
}
