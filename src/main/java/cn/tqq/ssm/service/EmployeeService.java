package cn.tqq.ssm.service;

import cn.tqq.ssm.entity.Employee;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/15 20:42
 */
public interface EmployeeService {

    public abstract List<Employee> getAllEmployee();

    public abstract void addEmployee(Employee employee);

    public abstract boolean validateEmpName(String empName);

    public abstract Employee getEmployeeById(Integer id);
}
