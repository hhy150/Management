package com.example.management.controller;

import com.example.management.entity.Affair;
import com.example.management.sevice.AffairSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AffairController {
    @Autowired
    AffairSevice affairSevice;

    @RequestMapping("/affair/delete")
    boolean UndeleteAffairById(@RequestParam("id") Integer id){
        return affairSevice.deleteAffairById(id);
    };

    @RequestMapping("/affair/undelete")
     boolean deleteAffairById(@RequestParam("id") Integer id){
        return affairSevice.UndeleteByPrimaryKey(id);
    };

    @RequestMapping("/affair/add")
    boolean addAffairById( @RequestBody Affair affair ){ return affairSevice.addAffair(affair); };

    @RequestMapping("/affair/update")
    boolean UpdateAffair(@RequestBody Affair affair ){
        return affairSevice.updateAffair(affair);
    };

    @RequestMapping("/affair/get")
    Affair getAffairById(@RequestParam("id") Integer id){
        return  affairSevice.getAffairById(id);
    }

    @RequestMapping("/affair/getall")
    List <Affair> getAll(){
      List<Affair> list;
      list=affairSevice.getAll();
      return list;
    };
}
