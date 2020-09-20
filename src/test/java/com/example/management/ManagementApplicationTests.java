package com.example.management;


import com.example.management.entity.ExcelData;
import com.example.management.entity.Student;
import com.example.management.mapper.ClubMapper;
import com.example.management.mapper.DepartmentMapper;
import com.example.management.mapper.StudentMapper;
import com.example.management.util.ExcelUtil;
import com.example.management.service.StuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletResponse;


@SpringBootTest
@AutoConfigureMockMvc
class ManagementApplicationTests {

    @Test
    public void contextLoads(){

    }

}
