package cn.tqq.ssm.entity;

import java.util.Date;

/**
 * User: TianQiangQiang
 * Date: 2017/08/13 17:49
 */
public class Employee {

    private Integer empId;
    private String empName;
    private char gender;
    private String email;
    private Date hiredate;
    private Integer deptId;
    private Department department;

    public Employee() {
    }

    public Employee(Integer empId, String empName, char gender, String email, Date hiredate, Integer deptId, Department department) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.hiredate = hiredate;
        this.deptId = deptId;
        this.department = department;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", hiredate=" + hiredate +
                ", deptId=" + deptId +
                ", department=" + department +
                '}';
    }
}
