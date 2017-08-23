package cn.tqq.ssm.service.impl;

import cn.tqq.ssm.entity.Department;
import cn.tqq.ssm.mapper.DepartmentMapper;
import cn.tqq.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/23 19:23
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartment() {
        List<Department> departments = departmentMapper.getAllDepartment();
        return departments;
    }

}
