package com.example.management;

import com.example.management.entity.Club;
import com.example.management.mapper.ClubMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class ManagementApplicationTests {

    @Test
    public void contextLoads(){

    }

    @Autowired
    private ClubMapper clubMapper;
    @Test
    public void test(){
        Long id =1L;
        String newPwd ="sdad";
        Club club =clubMapper.selectById(id);
        club.setPassword(newPwd);
        int b = clubMapper.updateById(club);
    }

}
