package in.stack.springboot.demoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String homepage(){
        return "welcome to Microservices world!";
    }

}
