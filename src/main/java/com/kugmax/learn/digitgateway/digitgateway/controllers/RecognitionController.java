package com.kugmax.learn.digitgateway.digitgateway.controllers;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
public class RecognitionController {

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/recognize")
    public String recognize(HttpEntity<byte[]> requestEntity) throws Exception {

        System.out.println("in recognize");

        InputStream in = null;
        FileOutputStream fos = null;
        try {
            String imageString = IOUtils.toString(requestEntity.getBody(), "UTF-8");
            System.out.println(imageString);


            imageString = imageString.substring("data:image/png;base64,".length());
            byte[] contentData = imageString.getBytes();
            byte[] decodedData = Base64.decodeBase64(contentData);
            String imgName = "a.png";
            fos = new FileOutputStream(imgName);
            fos.write(decodedData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        } finally {
            if (in != null) {
                in.close();
            }
            if (fos != null) {
                fos.close();
            }
        }

        return "OK";
    }
}
