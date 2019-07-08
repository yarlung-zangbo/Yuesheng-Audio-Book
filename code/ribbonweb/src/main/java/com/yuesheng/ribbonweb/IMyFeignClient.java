package com.yuesheng.ribbonweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="client1")
public interface IMyFeignClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String test(@PathVariable("id") Long id);
}
