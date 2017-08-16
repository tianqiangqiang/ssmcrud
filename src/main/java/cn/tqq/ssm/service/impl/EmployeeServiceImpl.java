package cn.tqq.ssm.service.impl;

import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.mapper.EmployeeMapper;
import cn.tqq.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/15 20:43
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeMapper.getAllEmployee();
        return employees;
    }

}
