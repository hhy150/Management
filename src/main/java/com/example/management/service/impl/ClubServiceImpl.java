package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.entity.Club;
import com.example.management.entity.Department;
import com.example.management.entity.Student;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.LoginMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.ClubService;
import com.example.management.util.ConstantUtils;
import com.example.management.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@CacheConfig(cacheNames = "club")
@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {

    private ClubMapper clubMapper;
    private DepartmentMapper departmentMapper;
    private  HttpSession httpSession;
    @Autowired
    public ClubServiceImpl(ClubMapper clubMapper, DepartmentMapper departmentMapper, HttpSession httpSession){
        this.clubMapper=clubMapper;
        this.departmentMapper=departmentMapper;
        this.httpSession = httpSession;
    }


    /**
     * 根据名字模糊查询
     */
    @Override
    public IPage<Club> getBatchByName(String name,int pageNum,int pageSize) {
        QueryWrapper<Club> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        Page<Club> page = clubMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return page;
    }


    /**
     *根据id删除一个部门(内存判断成功吗？)
     */
    @CacheEvict(key = "#id",condition = "#result==true")
    @Override
    public Boolean deleteById(Long id) {

        if( clubMapper.selectById(id) == null)  return false;
        QueryWrapper wrapper1 = new QueryWrapper<Department>()
                .eq("club_id", id);
        List<Department> departments = departmentMapper.selectList(wrapper1);
        if(departments.size() != 0 )  return false;
        if(clubMapper.deleteById(id)==0)  return false;
        return true;
    }

     /**
     *插入一个部门
     */
     @CachePut(key = "#club.id",condition = "#result==true")
    @Override
    public Boolean insert(Club club) {
        club.setPassword(MD5Util.Md5(club.getPassword()));
        if( clubMapper.insert(club)==0)
            return false;
        return true;
    }

     /**
     *获得所有的社团
     */
//    @Cacheable(key = "#root.methodName")
    @Override
    public IPage<Club> getAllClub(int pageNum, int pageSize) {
        IPage<Club> page = clubMapper.selectPage(new Page<>(pageNum, pageSize), null);
        if(page==null) return  null;
        return page;
    }

     /**
      * 获取某社团下的部门信息
      */
/*    @Override
    public IPage<Department> getBatchDeptByClub(String clubName, int pageNum, int pageSize) {
        Club club = loginMapper.getClubByName(clubName);
        if(club==null)   return null;
        QueryWrapper wrapper1 = new QueryWrapper<Department>()
                .eq("club_id", club.getId());
        IPage<Department> page =new Page<>(pageNum,pageSize);
        IPage page1 = departmentMapper.selectPage(page, wrapper1);
        return page1;
    }*/

    /**
     *根据名字查找某社团下的某部门
     */
    /*@Override
    public Department getDeptByName(String clubName, String deptName) {
        Club club = loginMapper.getClubByName(clubName);
        if(club==null) return null;
        QueryWrapper wrapper1 = new QueryWrapper<Department>()
                .eq("club_id", club.getId());
        List<Department> departments = departmentMapper.selectList(wrapper1);
        for (Department d:departments) {
            if(d.getName().equals(deptName)) return d;
        }
        return null;
    }
*/
     /**
     *根据部门查看所有成员
     */
   /* @Override
    public IPage<Student> getBatchStuByDept(String clubName, String deptName, int pageNum, int pageSize) {
        Department dept = getDeptByName(clubName, deptName);
        if(dept==null) return null;
        QueryWrapper wrapper2 = new QueryWrapper<Student>()
                .eq("dept_id", dept.getId());
        IPage<Student> page =new Page<>(pageNum,pageSize);
        IPage page1 = studentMapper.selectPage(page, wrapper2);
        return  page1;
    }*/


    /**
     *根据姓名查找某社团-部门下的某成员
     */
  /*  @Override
    public Student selectByStuId(String clubName, String deptName,String stuId) {
        Department dept = getDeptByName(clubName, deptName);
        if(dept==null)   return null;
        QueryWrapper wrapper2 = new QueryWrapper<Student>()
                .eq("dept_id", dept.getId());
        List<Student> students = studentMapper.selectList(wrapper2);
        if(students==null) return  null;
        for (Student stu:students) {
            if(stuId.equals(stu.getStuId()))
                return stu;
        }
        return null;
    }*/

    /**
     * 获取社团的部门数量
     */
    @Override
    public Integer getDeptCount() {
        //获取session中的姓名。
        String username=String.valueOf(httpSession.getAttribute(ConstantUtils.USER_NAME));
        QueryWrapper<Club> wrapper = new QueryWrapper<Club>()
                .eq("username", username);
        Club club = clubMapper.selectOne(wrapper);
        return clubMapper.getDeptCount(club.getId());
    }




}
