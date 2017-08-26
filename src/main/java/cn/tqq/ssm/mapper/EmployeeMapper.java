package cn.tqq.ssm.mapper;

import cn.tqq.ssm.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/13 18:56
 */
@Repository
public interface EmployeeMapper {

    public abstract void addEmployee(Employee employee);

    public abstract void batchAddEmployee(List<Employee> employees);

    public abstract void deleteEmployeeById(Integer id);

    public abstract void deleteEmployeeByIds(List<Integer> ids);

    public abstract void deleteAllEmployee();

    public abstract void updateEmployee(Employee employee);

    public abstract Employee getEmployeeById(Integer id);

    public abstract Employee getEmployeeByName(String empName);

    public abstract List<Employee> getEmployeeByDeptId(Integer deptId);

    public abstract List<Employee> getAllEmployee();

}
