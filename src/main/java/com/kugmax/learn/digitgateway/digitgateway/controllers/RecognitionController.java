package com.kugmax.learn.digitgateway.digitgateway.controllers;

import com.kugmax.learn.digitgateway.digitgateway.clients.DigitRecognizerClient;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;

@RestController
public class RecognitionController {

    @Autowired
    private DigitRecognizerClient client;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/recognize")
    public String recognize(HttpEntity<byte[]> requestEntity) throws Exception {

        System.out.println("in recognize");

        InputStream in = null;
        FileOutputStream fos = null;
        try {
            String imageString = IOUtils.toString(requestEntity.getBody(), "UTF-8");
            imageString = imageString.substring("data:image/png;base64,".length());
            byte[] contentData = imageString.getBytes();
            byte[] decodedData = Base64.decodeBase64(contentData);
            String imgName = "a.png";
            fos = new FileOutputStream(imgName);
            fos.write(decodedData);


            String path = saveToMNIST(decodedData);

            int digit = client.recognize(path);
            System.out.println(digit);

            return String.valueOf(digit);
        } finally {
            if (in != null) {
                in.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    private String saveToMNIST(byte[] decodedData) throws Exception {
        FileOutputStream fos = new FileOutputStream("gray.png");
        fos.write(decodedData);
        fos.close();

        BufferedImage image = ImageIO.read(new File("gray.png"));

        File toRecognize =  new File("gray2.png");

        ImageIO.write(RecognitionController.criaImagemCinza(image), "png", toRecognize);

        return toRecognize.getAbsolutePath();
    }

    public static BufferedImage criaImagemCinza(BufferedImage imgJPEG) {
        // Create a new buffer to BYTE_GRAY
        BufferedImage img = new BufferedImage(imgJPEG.getWidth(), imgJPEG.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = img.getRaster();
        WritableRaster rasterJPEG = imgJPEG.getRaster();
        // Foreach pixel we transofrm it to Gray Scale and put it on the same image
        for (int h = 0; h < 256; h++) {
            for (int w = 0; w < 256; w++) {
                int[] p = new int[4];
                rasterJPEG.getPixel(w, h, p);
                p[0] = (int) (0.3 * p[0]);
                p[1] = (int) (0.59 * p[1]);
                p[2] = (int) (0.11 * p[2]);
                int y = p[0] + p[1] + p[2];
                raster.setSample(w, h, 0, y);
            }
        }
        return img;
    }
}
