package cn.tqq.ssm.controller;

import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/15 19:47
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/listAllEmp")
    public String listAllEmp(Model model) {
        List<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("emps", employees);
        return "list";
    }

}
