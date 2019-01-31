package com.jung.hmis.common.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimlly
 * @createtime: 2018/3/27上午11:19
 */
@RestController
public class BaseController {

    @RequestMapping("/test")
    String test(){

        return "ok";
    }
}
