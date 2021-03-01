package com.jenkins.pipelinedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/greeting")
    public String gretting() {
        return "Pipe-line example success - feature PR";
    }
}
