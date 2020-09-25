package com.example.management.app.controller;


import com.example.management.entity.Department;
import com.example.management.entity.ResultBody;
import com.example.management.service.DepartmentService;
import com.example.management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    public final LoginService loginService;
    @Autowired
    public DepartmentController(DepartmentService departmentService, LoginService loginService) {
        this.departmentService = departmentService;
        this.loginService = loginService;
    }

    /**
     *添加部门
     */
    @PostMapping("add")
    public ResultBody deptAdd(@Valid Department department){
        if(!departmentService.insert(department))
              return ResultBody.error("添加失败");
        return ResultBody.success();
    }

    /**
     *删除部门
     */
    @PutMapping("delete/{id}")
    public ResultBody deptDelete(@PathVariable("id") Long id){
        if(departmentService.deleteById(id))
            return ResultBody.success();
        return ResultBody.error("删除失败,该部门不存在或者该部门下还有成员");
    }

    /**
     *更新部门
     */
    @PutMapping("update")
    public ResultBody deptUpdate(@Valid Department department){
        if(!departmentService.update(department))
                  return ResultBody.error("更新失败");
        return ResultBody.success();
    }

    /**
     * 获得deptList
     * */
    @GetMapping("list")
    public ResultBody deptList( @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        return ResultBody.success(loginService.getListByRole(pageNum,pageSize));
    }


}
