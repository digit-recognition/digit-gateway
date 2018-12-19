package com.kugmax.learn.digitgateway.digitgateway.api;

import com.kugmax.learn.digitgateway.digitgateway.clients.DigitRecognizerClient;
import com.kugmax.learn.digitgateway.digitgateway.services.ImgProcessorService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GatewayAPIServer extends DigitAPIGrpc.DigitAPIImplBase {
    @Autowired
    private ImgProcessorService imgProcessorService;

    @Autowired
    private DigitRecognizerClient client;

    @Override
    public void saveAndRecognize(SaveAndRecognizeRequest request, StreamObserver<SaveAndRecognizeResponse> responseObserver) {

        System.out.println("in saveAndRecognize");

        try {
            String grayImg = imgProcessorService.makeImgGray(request.getBytes());

            int result = client.recognizeByBytes(grayImg);

            SaveAndRecognizeResponse response = SaveAndRecognizeResponse.newBuilder().setValue(result).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }
}
