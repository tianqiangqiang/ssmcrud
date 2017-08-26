package cn.tqq.ssm.controller;

import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.entity.GenericInfo;
import cn.tqq.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: TianQiangQiang
 * Date: 2017/08/15 19:47
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/getAllEmpWithJson")
    @ResponseBody
    public GenericInfo getAllEmpWithJson(@RequestParam(value = "pageNumber") Integer pageNumber) {
        PageHelper.startPage(pageNumber, 5);
        List<Employee> employees = employeeService.getAllEmployee();
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        return GenericInfo.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/listAllEmp")
    public String listAllEmp(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, Model model) {
        PageHelper.startPage(pageNumber, 5);
        List<Employee> employees = employeeService.getAllEmployee();
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public GenericInfo saveEmployee(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return GenericInfo.fail().add("map", map);
        }
        employeeService.addEmployee(employee);
        return GenericInfo.success();
    }

    @RequestMapping(value = "/validateEmpName")
    @ResponseBody
    public GenericInfo validateEmpName(String empName) {
        boolean result = employeeService.validateEmpName(empName);
        if (result == false) {
            return GenericInfo.fail();
        }
        return GenericInfo.success();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GenericInfo getEmployeeById(@PathVariable(value = "id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return GenericInfo.success().add("employee",employee);
    }

}
