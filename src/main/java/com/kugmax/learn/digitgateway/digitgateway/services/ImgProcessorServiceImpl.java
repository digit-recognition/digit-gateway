package com.kugmax.learn.digitgateway.digitgateway.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;

@Service
public class ImgProcessorServiceImpl implements ImgProcessorService {
    @Override
    public String makeImgGray(String image) throws Exception {
        image = image.substring("data:image/png;base64,".length());
        byte[] contentData = image.getBytes();
        byte[] decodedData = Base64.decodeBase64(contentData);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedData);

        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(criaImagemCinza(bufferedImage), "png", os);
        return Base64.encodeBase64String(os.toByteArray());
    }

    private BufferedImage criaImagemCinza(BufferedImage imgJPEG) {
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
