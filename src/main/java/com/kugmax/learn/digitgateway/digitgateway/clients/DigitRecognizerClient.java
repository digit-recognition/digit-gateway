package com.kugmax.learn.digitgateway.digitgateway.clients;

import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionByBytesRequest;
import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionByPathRequest;
import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.kugmax.learn.digitgateway.digitgateway.services.DigitRecognitionGrpc;
import org.springframework.stereotype.Service;

@Service
public class DigitRecognizerClient {

    public int recognizeByPath(String filePath) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        DigitRecognitionGrpc.DigitRecognitionBlockingStub stub = DigitRecognitionGrpc.newBlockingStub(channel);


        DigitRecognitionByPathRequest request = DigitRecognitionByPathRequest
                .newBuilder()
                .setPath(filePath)
                .build();

        DigitRecognitionResponse response = stub.recognizeByPath(request);

        return response.getValue();
    }


    public int recognizeByBytes(String bytes) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        DigitRecognitionGrpc.DigitRecognitionBlockingStub stub = DigitRecognitionGrpc.newBlockingStub(channel);


        DigitRecognitionByBytesRequest request = DigitRecognitionByBytesRequest
                .newBuilder()
                .setBytes(bytes)
                .build();

        DigitRecognitionResponse response = stub.recognizeByBytes(request);

        return response.getValue();
    }
}
