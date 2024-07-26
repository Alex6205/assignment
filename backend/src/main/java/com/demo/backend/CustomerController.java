package com.demo.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	
//	private List<GenericEntity> entityList = new ArrayList<>();
//
//    @RequestMapping("/entity/all")
//    public List<GenericEntity> findAll() {
//        return entityList;
//    }
//
//    @RequestMapping(value = "/entity", method = RequestMethod.POST)
//    public GenericEntity addEntity(GenericEntity entity) {
//        entityList.add(entity);
//        return entity;
//    }
//
//    @RequestMapping("/entity/findby/{id}")
//    public GenericEntity findById(@PathVariable Long id) {
//        return entityList.stream().
//                 filter(entity -> entity.getId().equals(id)).
//                   findFirst().get();
//    }

}
