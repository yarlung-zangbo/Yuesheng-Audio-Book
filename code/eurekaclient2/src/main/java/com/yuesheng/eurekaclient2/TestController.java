package com.yuesheng.eurekaclient2;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id) {
        return "the request2 id is " + id;
    }

    @PostMapping("/post")
    public String post(@RequestBody TestParam testParam) {
        return "testParam 1" + testParam.toString();
    }
}
