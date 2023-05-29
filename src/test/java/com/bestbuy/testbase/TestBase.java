package com.bestbuy.testbase;

import io.restassured.RestAssured;

public class TestBase {

    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

}
