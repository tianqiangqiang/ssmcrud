package cn.tqq.ssm.controller;

import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listAllEmp(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, Model model) {
        PageHelper.startPage(pageNumber, 5);
        List<Employee> employees = employeeService.getAllEmployee();
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

}
