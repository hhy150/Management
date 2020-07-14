package com.example.management;


import com.example.management.entity.Community;
import com.example.management.service.CommunityService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Autowired
    private CommunityService communityService;
    @Test
   // @Rollback(false)
    public void testAdd(){
        Community community = new Community();
        community.setName("SAST");
        boolean save = communityService.save(community);
        Assert.assertTrue(save);
    }

    @Test
    public void testDel(){
        boolean b = communityService.deleteCommunity(1);
        Assert.assertTrue(b);
    }

    @Test
    public void testUndo(){
        boolean undo = communityService.undo(1);
        Assert.assertTrue(undo);
    }

    @Test
    public void testUpdate(){
        Community community = new Community();
        community.setId(1);
        community.setName("SA");
        boolean b = communityService.updateCommunity(community);
        Assert.assertTrue(b);
    }

    @Test
    public void testList(){
        List<Community> allCommunity = communityService.getAllCommunity();
        System.out.println(allCommunity);
        Assert.assertNotNull(allCommunity);
    }
    //解决同名问题
    @Test
    public void testFind(){
        Community sast = communityService.getCommunityByName("SAST");
        System.out.println(sast);
        Assert.assertNotNull(sast);
    }




}
