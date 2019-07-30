package com.yuesheng.cf.controller;

import com.yuesheng.cf.entity.Soundbook;
import com.yuesheng.cf.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class RecController {
    @Autowired
    private RecService recService;

    private static long last_update = 0;
    private static final int interval = 20;

    @RequestMapping(value = "/updateRecMap", method = RequestMethod.GET)
    @ResponseBody
    public String updateRecMapController() {
        recService.updateRecMap();
        last_update = System.currentTimeMillis();
        return "updated";
    }

    @RequestMapping(value = "/getRecList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Soundbook> getRecListController(@RequestParam("username") String username) {
        long current_update = System.currentTimeMillis();
        if (current_update - last_update > interval) {
            recService.updateRecMap();
            last_update = current_update;
        }
        return recService.getRecList(username);
    }
}
