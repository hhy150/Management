package com.example.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.management.entity.Department;



public interface DepartmentService extends IService<Department> {

    Boolean deleteById(Long id);
    Boolean insert(Department department);
    Boolean update(Department dept);

}
