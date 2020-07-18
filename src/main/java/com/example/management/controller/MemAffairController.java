package com.example.management.controller;

import com.example.management.entity.Affair;
import com.example.management.entity.MemAffair;
import com.example.management.repository.MemAffairMapper;
import com.example.management.sevice.AffairSevice;
import com.example.management.sevice.MemAffairSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemAffairController {
    @Autowired
    MemAffairSevice memAffairSevice;

    @RequestMapping("/memaffair/delete")
    boolean UndeleteAffairById(@RequestParam("id") Integer id){
        return memAffairSevice.deleteMemAffairById(id);
    };

    @RequestMapping("/memaffair/undelete")
    boolean deleteAffairById(@RequestParam("id") Integer id){
        return memAffairSevice.UndeleteByPrimaryKey(id);
    };

    @RequestMapping("/memaffair/add")
    boolean addAffairById(@RequestBody MemAffair memAffair){ return memAffairSevice.addMemAffair(memAffair);};

    @RequestMapping("/memaffair/update")
    boolean UpdateAffair(@RequestBody MemAffair memAffair){
        return memAffairSevice.updateMemAffair(memAffair);
    };

    @RequestMapping("/memaffair/get")
    MemAffair getAffairById(@RequestParam("id") Integer id){
        return  memAffairSevice.getAffairById(id);
    }

    @RequestMapping("/memaffair/getall")
    List<MemAffair> getAll(){
        List<MemAffair> list;
        list=memAffairSevice.getAll();
        return list;
    };
}
