package cn.tqq.ssm.test;

import cn.tqq.ssm.entity.Department;
import cn.tqq.ssm.entity.Employee;
import cn.tqq.ssm.mapper.DepartmentMapper;
import cn.tqq.ssm.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/14 19:25
 */
public class MapperTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        Department department = new Department();
        department.setDeptName("开发部");
        departmentMapper.addDepartment(department);
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(null, "测试部", null));
        departments.add(new Department(null, "人事部", null));
        departmentMapper.batchAddDepartment(departments);
    }

    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        Employee employee = new Employee(null, "zhangsan", 'M', "zhangsan@gmail.com", new java.util.Date(), 2, null);
        employeeMapper.addEmployee(employee);
    }

    @Test
    public void test4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null, "lisi", 'W', "lisi@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "wangwu", 'M', "wangwu@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "zhaoliu", 'M', "zhaoliu@163.com", new Date(), 2, null));
        employees.add(new Employee(null, "niuqi", 'M', "niuqi@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "zhangsan", 'M', "zhangsan@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "jerry", 'W', "jerry@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "tom", 'M', "tom@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "messi", 'M', "messi@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "ronaldo", 'M', "ronaldo@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "yibu", 'M', "yibu@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "boerte", 'M', "boerte@qq.com", new Date(), 3, null));
        employeeMapper.batchAddEmployee(employees);
    }

    @Test
    public void test5() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        Employee employee = new Employee(4, "niuqi", 'M', "niuqi@qq.com", new Date(), 3, null);
        employeeMapper.updateEmployee(employee);
    }

    @Test
    public void test6() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        List<Employee> employees = employeeMapper.getEmployeeByDeptId(2);
        System.out.println(employees);
    }

    @Test
    public void test7() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        employeeMapper.deleteAllEmployee();
    }

    @Test
    public void test8() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        List<Employee> employees = employeeMapper.getAllEmployee();
        if (employees != null && employees.size() > 0) {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

}
