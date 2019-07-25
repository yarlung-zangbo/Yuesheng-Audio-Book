package com.yuesheng.cf.controller;

import com.yuesheng.cf.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class RecController {
    @Autowired
    private RecService recService;

    @RequestMapping(value = "/updateRecMap", method = RequestMethod.GET)
    @ResponseBody
    public String updateRecMapController() {
        recService.updateRecMap();
        return "updated";
    }

    @RequestMapping(value = "/getRecList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Integer> getRecListController(@RequestParam("username") String username) {
        if (!username.isEmpty()) {
            return recService.getRecList(username);
        } else {
            return null;
        }
    }
}
