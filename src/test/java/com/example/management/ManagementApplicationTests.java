package com.example.management;


import com.example.management.entity.ExcelData;
import com.example.management.entity.enums.GenderType;
import com.example.management.entity.Student;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.util.EnumUtil;
import com.example.management.util.ExcelUtil;
import com.example.management.util.MD5Util;
import com.sun.deploy.net.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ManagementApplicationTests {

    @Test
    public void contextLoads(){

    }

    @Autowired
    private ClubMapper clubMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DepartmentMapper departmentMapper;


    @Test
    public void test12() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        String[] head={"姓名","学号","学院","专业","性别","南邮邮箱","电话号码","QQ"};
        String fileName="D://export/成员表.xls";
        List<Student> students = clubMapper.selectAllStuByClub(2L);
        List<String[]> data=new ArrayList<>();
        String[] stu = new String[9];
        for (Student s : students) {
            //将一个stu化成String[]
            String gender = s.getStuGender().getGender();
            //如何把这个也放到redis中》》

            stu[0]=s.getStuName();
            stu[1]=s.getStuId();
            stu[2]=s.getStuCollege();
            stu[3]=s.getStuMajor();
            stu[4]=gender;
            stu[5]=s.getStuMail();
            stu[6]=s.getStuPhone();
            stu[7]=s.getStuQq();
            //  String dept = departmentMapper.selectById(s.getDeptId()).getName();
           // stu[8]=dept;

            data.add(stu);
        }
        ExcelData excelData = new ExcelData(fileName, head, data);
        ExcelUtil.exportExcel(response,excelData,"校科协");

    }

/*    @Test
    public void test13(){
        //测试导入
        List<Object[]> list = ExcelUtil.importExcel;
        Iterator<Object[]> iterator = list.iterator();
        while (iterator.hasNext()){
            Object[] objects = iterator.next();

            String gender=(String) objects[4];
            GenderType sex = EnumUtil.sex(gender);

            Student student = new Student();
            student.setStuName((String) objects[0]);
            student.setStuId((String) objects[1]);
            student.setStuCollege((String) objects[2]);
            student.setStuMajor((String) objects[3]);
            student.setStuGender(sex);
            student.setStuPhone(String.valueOf( objects[5]));
            student.setStuQq( String.valueOf( objects[6]));
            student.setStuMail(String.valueOf( objects[7]));
//            //这个是club获取stu。（类型转化还不对了。）
//            student.setDeptId(Long.valueOf((long)objects[8]));
            //deptId和password得自己设定
            student.setStuPassword(MD5Util.Md5("xxx"));
            student.setDeptId(2L);
            System.out.println(student.toString());
            studentMapper.insert(student);
        }

    }*/

}
