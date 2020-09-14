package com.example.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.entity.Department;
import com.example.management.entity.Student;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.DepartmentService;
import com.example.management.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import java.util.List;

@CacheConfig(cacheNames = "dept")
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper,Department> implements DepartmentService {

    private DepartmentMapper departmentMapper;
    private StudentMapper studentMapper;
    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper,StudentMapper studentMapper){
        this.departmentMapper=departmentMapper;
        this.studentMapper=studentMapper;
    }

    /**
     *删除部门
     */
    @CacheEvict(key = "#id",condition = "#result==true")
    @Override
    public Boolean deleteById(Long id) {
        if(departmentMapper.selectById(id)==null)
            return false;
        QueryWrapper<Student> wrapper= new QueryWrapper<Student>()
                .eq("dept_id", id);
        List<Student> students = studentMapper.selectList(wrapper);
        if(students.size()!=0) return false;
        if(departmentMapper.deleteById(id)==0)
            return false;
        return true;
    }

    /**
     *插入部门
     */
    @CachePut(key = "#department.id")
    @Override
    public Boolean insert(Department department) {
        department.setPassword(MD5Util.Md5(department.getPassword()));
        int insert = departmentMapper.insert(department);
        if(insert==0) return false;
        return true;
    }

    @CachePut(key = "#dept.id")
    @Override
    public Boolean update(Department dept){
        dept.setPassword(MD5Util.Md5(dept.getPassword()));
       if(departmentMapper.updateById(dept)==0)
           return false;
       return true;
    }
}
