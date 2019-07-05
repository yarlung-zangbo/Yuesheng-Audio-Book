package com.yuesheng.eurekaclient1;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id) {
        return "the request1 id is " + id;
    }

    @PostMapping("/post")
    public String post(@RequestBody TestParam testParam) {
        return "testParam 1" + testParam.toString();
    }
}
