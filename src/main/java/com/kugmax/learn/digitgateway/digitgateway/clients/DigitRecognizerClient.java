package com.kugmax.learn.digitgateway.digitgateway.clients;

import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionRequest;
import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionGrpc;
import org.springframework.stereotype.Service;

@Service
public class DigitRecognizerClient {

    public int recognize(String filePath) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext(true)
                .build();

        DigitRecognitionGrpc.DigitRecognitionBlockingStub stub = DigitRecognitionGrpc.newBlockingStub(channel);


        DigitRecognitionRequest request = DigitRecognitionRequest
                .newBuilder()
                .setValue(filePath)
                .build();

        DigitRecognitionResponse response = stub.recognize(request);

        return response.getValue();
    }
}
