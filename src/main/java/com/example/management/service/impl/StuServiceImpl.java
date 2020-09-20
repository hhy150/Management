package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.management.entity.*;
import com.example.management.entity.enums.GenderType;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.LoginMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.service.StuService;
import com.example.management.util.ConstantUtils;
import com.example.management.util.EnumUtil;
import com.example.management.util.ExcelUtil;
import com.example.management.util.MD5Util;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@CacheConfig(cacheNames = "stu")
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    StudentMapper stuMapper;
    @Autowired
    DepartmentMapper deptMapper;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    HttpSession httpSession;
    @Autowired
    private LoginMapper loginMapper;

    @CachePut(value = "stu",key = "#student.stuId",condition = "#result==1")
    @Override
    public int  addStu(Student student, HttpServletRequest request) {
        Object  name= request.getSession().getAttribute(ConstantUtils.USER_NAME);
        int role = getAttributes(httpSession).getRole();
        int judge=0;
        Student stu =null;
        if(name==null)
            return 0;
        //1社员2社团部门管理员3社团admin4superadmin 0 no suc
        switch (role){
            case 2:
                Department dept=deptMapper.selectOne(new QueryWrapper<Department>().eq("username",name));
                stu=stuMapper.getStuByStuId(student.getStuId(),dept.getId());
                student.setDeptId(dept.getId());
                if(stu!=null)
                    judge=2;
                judge = stuMapper.addStu(student);
                break;
            case 3:
                //根据dept_id判断stu
                stu=stuMapper.getStuByStuId(student.getStuId(),student.getDeptId());
                if(stu!=null)
                    judge=2;
                judge=stuMapper.addStu(student);
                break;
            default:
                judge=0;
        }
        return judge;
    }

    @CachePut(value = "stu",key = "#student.stuId",condition = "#result==true")
    @Override
    public boolean updateStu(Student student){
        Student stu=stuMapper.getStuByStuId(student.getStuId(),student.getDeptId());
        if(stu!=null){
            stuMapper.updateStu(student);
            return true;
        }else {
            return false;
        }
    }

    @CacheEvict(value = "stu",key = "#student.stuId",condition = "#result==true")
    @Override
    public boolean deleteStu(Student student) {
        if(student.getStuId()!=null) {
            stuMapper.deleteStu(student.getStuId());
            return true;
        }else{
            return false;
        }
    }

    //根据dept_id获得stuList
    @Cacheable(key = "#p0")
    @Override
    public List<Student> getList(Long deptId){
        return stuMapper.getStuList(deptId);
    }


    @Cacheable(value = "stu",key = "#name+deptId",condition = "#result==true")
    @Override
    public Student getStuByName(String name,Long deptId){
        return stuMapper.getStuByName(name,deptId);
    }

    @Override
    public Integer getStuCount() {
        int role= attributes().getRole();
        String username =attributes().getUsername();
        switch (role){
            case 2:
                //部门
                QueryWrapper<Department> wrapper = new QueryWrapper<Department>()
                        .eq("username", username);
                Department department = deptMapper.selectOne(wrapper);
                return deptMapper.getStuCountByDept( department.getId());
            case 3:
                //社团
                QueryWrapper<Club> wrapper1 = new QueryWrapper<Club>()
                        .eq("username", username);
                Club club = clubMapper.selectOne(wrapper1);
                return clubMapper.getStuCountByClub(club.getId());
        }
        return null;
    }

    @Cacheable(value = "stu",key = "#stuId")
    @Override
    public Student getStuByStuId(String stuId) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<Student>()
                .eq("stuid", stuId);
        Student student = stuMapper.selectOne(wrapper);
        return student;
    }


    //可以提取出来获取session中的东西
    public SessionAttributes attributes(){
        return getAttributes(httpSession);
    }

    @NotNull
    static SessionAttributes getAttributes(HttpSession httpSession) {
        int role=0;
        String username=" ";
        Object o = httpSession.getAttribute(ConstantUtils.USER_ROLE);
        Object name= httpSession.getAttribute(ConstantUtils.USER_NAME);
        if(o!=null&& name!=null){
            role=(int)o;
            username = String.valueOf(name);
        }
        SessionAttributes attributes = new SessionAttributes(role, username);
        return attributes;
    }

    /**
     *导出成员信息数据到Excel表中（根据不同角色）
     */
    @Override
    public Boolean export(String fileName) throws IOException {
        int role=attributes().getRole();
        String username =attributes().getUsername();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();

        List<Student> students=null;
        String[] head=new String[9];
        switch (role){
            case 2:
                //dept：
                String[] titles={"姓名","学号","学院","专业","性别","南邮邮箱","电话号码","QQ"};
                head =titles;
                Department department = loginMapper.getDeptByName(username);
                students=deptMapper.selectAllStuByDept(department.getId());
                break;
            case 3:
                //club
                String[] titles2={"姓名","学号","学院","专业","性别","南邮邮箱","电话号码","QQ","部门"};
                head =titles2;
                Club club = loginMapper.getClubByName(username);
                students=clubMapper.selectAllStuByClub(club.getId());
                break;
        }
        List<String[]> data=new ArrayList<>();
        String[] stu = new String[9];
        for (Student s : students) {
            //将一个stu化成String[]
            String gender = s.getStuGender().getGender();

            stu[0]=s.getStuName();
            stu[1]=s.getStuId();
            stu[2]=s.getStuCollege();
            stu[3]=s.getStuMajor();
            stu[4]=gender;
            stu[5]=s.getStuMail();
            stu[6]=s.getStuPhone();
            stu[7]=s.getStuQq();
            if(role==3){
                //club做的
                String dept = deptMapper.selectById(s.getDeptId()).getName();
                stu[8]=dept;
            }
            data.add(stu);
        }
        ExcelData excelData = new ExcelData(fileName, head, data);
        if(ExcelUtil.exportExcel(response, excelData, username))
            return true;
        return false;
    }



    @Override
    public Boolean readExcel(MultipartFile file, String password) throws IOException {
        password = MD5Util.Md5(password);
        SessionAttributes attributes = getAttributes(httpSession);
        int role = attributes.getRole();
        String username = attributes.getUsername();

        List<Object[]> lists = ExcelUtil.importExcel(file);
        if(lists.size()==0)
            return false;
        Iterator<Object[]> iterator = lists.iterator();
        int num=0,i=0;
        while (iterator.hasNext()) {
            Object[] objects = iterator.next();
            //转换gender
            Student student = new Student();
            String gender = (String) objects[4];
            GenderType sex = EnumUtil.sex(gender);

            student.setStuName((String) objects[0]);
            student.setStuId((String) objects[1]);
            student.setStuCollege((String) objects[2]);
            student.setStuMajor((String) objects[3]);
            student.setStuGender(sex);
            student.setStuMail(String.valueOf(objects[5]));
            student.setStuPhone(String.valueOf(objects[6]));
            student.setStuQq(String.valueOf(objects[7]));
            //分角色不同插入dept_id
            if (role == 3) {
                String s1 = String.valueOf(objects[8]);
                username = username+ "-" + s1;
            }
            Department dept = loginMapper.getDeptByName(username);
            student.setDeptId(dept.getId());
            student.setStuPassword(password);
            num += stuMapper.insert(student);
            i++;
        }
        if(i==num)
            return true;
        return false;
    }
}
