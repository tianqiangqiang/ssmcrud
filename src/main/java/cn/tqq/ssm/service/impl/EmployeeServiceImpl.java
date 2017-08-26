package cn.tqq.ssm.service.impl;

import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.mapper.EmployeeMapper;
import cn.tqq.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/15 20:43
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeMapper.getAllEmployee();
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public boolean validateEmpName(String empName) {
        Employee employee = employeeMapper.getEmployeeByName(empName);
        return employee == null;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;
    }

}
