package cn.tqq.ssm.mapper;

import cn.tqq.ssm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: TianQiangQiang
 * Date: 2017/08/13 18:56
 */
@Repository
public interface DepartmentMapper {

    public abstract void addDepartment(Department department);

    public abstract void batchAddDepartment(List<Department> departments);

    public abstract void deleteDepartmentById(Integer id);

    public abstract void deleteDepartmentByIds(List<Integer> ids);

    public abstract void deleteAllDepartment();

    public abstract void updateDepartment(Department department);

    public abstract Department getDepartmentById(Integer id);

    public abstract List<Department> getAllDepartment();

}
