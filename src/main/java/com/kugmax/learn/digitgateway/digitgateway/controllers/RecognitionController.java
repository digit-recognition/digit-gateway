package com.kugmax.learn.digitgateway.digitgateway.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecognitionController {

    @ResponseBody
    @RequestMapping("/recognize")
    public String recognize() {
        return "recognized";
    }
}
