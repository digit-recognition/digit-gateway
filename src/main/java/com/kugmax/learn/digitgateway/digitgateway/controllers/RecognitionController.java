package com.kugmax.learn.digitgateway.digitgateway.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@RestController
public class RecognitionController {

//    @ResponseBody
//    @RequestMapping("/recognize")
//    public String recognize() {
//        return "recognized";
//    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/recognize")
    public String recognize(HttpEntity<byte[]> requestEntity) throws Exception {
//        process(requestEntity.getBody());

//        BufferedImage bi = new BufferedImage();
//        File outputfile = new File("saved.png");
//        ImageIO.write(bi, "png", outputfile);

        System.out.println("in recognize");

        OutputStream out = new FileOutputStream("a.jpg");
        out.write(requestEntity.getBody());
        out.flush();
        out.close();

        return "OK";

    }
}
