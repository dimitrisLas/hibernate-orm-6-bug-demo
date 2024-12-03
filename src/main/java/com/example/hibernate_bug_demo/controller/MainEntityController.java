package com.example.hibernate_bug_demo.controller;

import com.example.hibernate_bug_demo.dto.ViewDTO;
import com.example.hibernate_bug_demo.service.MainEntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MainEntityController {

    private final MainEntityService mainEntityService;

    public MainEntityController(MainEntityService mainEntityService){
        this.mainEntityService = mainEntityService;
    }

    @GetMapping("api/parents")
    public List<ViewDTO> getMainEntityDataSortedByValue1(){

        return mainEntityService.fetchMainEntityDataSortedBy("value1");
    }
}
