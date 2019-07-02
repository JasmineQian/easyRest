package com.example.demo.Listener;

import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResponseValidationFailureListenerimp implements ResponseValidationFailureListener {
    @Override
    public void onFailure(RequestSpecification requestSpecification, ResponseSpecification responseSpecification, Response response) {

        System.err.println(response.toString());
    }
}
