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
        Employee employee = new Employee(null, "zhangsan", "男", "zhangsan@gmail.com", new java.util.Date(), 2, null);
        employeeMapper.addEmployee(employee);
    }

    @Test
    public void test4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(null, "aaa", "女", "aaa@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "bbb", "女", "bbb@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "ccc", "男", "ccc@163.com", new Date(), 2, null));
        employees.add(new Employee(null, "ddd", "男", "ddd@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "eee", "女", "eee@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "fff", "女", "fff@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "ggg", "男", "ggg@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "hhh", "女", "hhh@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "iii", "男", "iii@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "jjj", "女", "jjj@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "kkk", "男", "kkk@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "lll", "女", "lll@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "mmm", "男", "mmm@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "nnn", "女", "nnn@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "ooo", "男", "ooo@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "ppp", "女", "ppp@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "qqq", "女", "qqq@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "rrr", "男", "rrr@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "sss", "女", "sss@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "ttt", "男", "ttt@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "uuu", "女", "uuu@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "vvv", "男", "vvv@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "www", "女", "www@qq.com", new Date(), 1, null));
        employees.add(new Employee(null, "xxx", "男", "xxx@qq.com", new Date(), 2, null));
        employees.add(new Employee(null, "yyy", "男", "yyy@qq.com", new Date(), 3, null));
        employees.add(new Employee(null, "zzz", "女", "zzz@qq.com", new Date(), 2, null));
        employeeMapper.batchAddEmployee(employees);
    }

    @Test
    public void test5() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        EmployeeMapper employeeMapper = applicationContext.getBean(EmployeeMapper.class);
        Employee employee = new Employee(4, "niuqi", "男", "niuqi@qq.com", new Date(), 3, null);
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

    @Test
    public void test9() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
        List<Department> departments = departmentMapper.getAllDepartment();
        if (departments != null && departments.size() > 0) {
            for (Department department : departments) {
                System.out.println(department);
            }
        }
    }

}
