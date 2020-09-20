package com.example.management;


import com.example.management.entity.ExcelData;
import com.example.management.entity.Student;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.util.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

            data.add(stu);
        }
        ExcelData excelData = new ExcelData(fileName, head, data);
        ExcelUtil.exportExcel(response,excelData,"校科协");

    }
}
