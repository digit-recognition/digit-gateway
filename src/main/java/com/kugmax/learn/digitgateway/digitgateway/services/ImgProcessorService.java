package com.kugmax.learn.digitgateway.digitgateway.services;

import org.springframework.stereotype.Service;

public interface ImgProcessorService {
    String makeImgGray(String bytes) throws Exception;
}
