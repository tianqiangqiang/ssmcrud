package cn.tqq.ssm.controller;

import cn.tqq.ssm.entity.Department;
import cn.tqq.ssm.entity.GenericInfo;
import cn.tqq.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/23 19:27
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/getAllDept")
    @ResponseBody
    public GenericInfo getAllDept() {
        List<Department> departments = departmentService.getAllDepartment();
        return GenericInfo.success().add("departments", departments);
    }

}
