package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    @Test
    public void test001() {

        int limit = response.extract().path("limit");
        System.out.println("-------StartingTest---------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("-------End of Test---------");
    }

    ////2. Extract the total
    @Test
    public void test0002() {
        int total = response.extract().path("limit");
        System.out.println("-------StartingTest---------");
        System.out.println("The value of total is : " + total);
        System.out.println("-------End of Test---------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test0003() {

        String name = response.extract().path("data[4].name");
        System.out.println("-------StartingTest---------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("-------End of Test---------");

    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listOfName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of name of the stores are : " + listOfName);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the storeId of all the store
    @Test
    public void test0005() {
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of id's are : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test0006() {
        List<Integer> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the size of the list are : " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test0007() {
        List<HashMap<String, ?>> storeName = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the value of the store where store name is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test0008() {
        List<HashMap<String, ?>> addressOfTheStoreName = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store where store name is Rochester and address is: " + addressOfTheStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test0009() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  all the services of 8th store: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services=='Windows Store'}.storeServices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  Get storeservices of the store where service name = Windows Store " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> listOfAllStoreId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store : " + listOfAllStoreId);
        System.out.println("------------------End of Test---------------------------");

    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> listStoreIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store: " + listStoreIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        //List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        List<String> storeName = response.extract().path("data.findAll{it.state='ND'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state 'ND' are : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        List<Integer> totalNoOfServices = response.extract().path("data.findAll{it.name='Rochester'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester : " + totalNoOfServices.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<?> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = Windows Store " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<HashMap<String,?>>listOfServices=response.extract().path("data.findAll{it.name=='Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  Find the name of all services Where store name = Fargo "+listOfServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //            17. Find the zip of all the store
    @Test
    public void test017(){
        List<Integer>listOfZip=response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store = Fargo "+listOfZip);
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test018(){
        List<Integer>zipOfStore=response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville "+zipOfStore);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        //List<String>listOfStoreService=response.extract().path("data.findAll{it.name==Magnolia Home Theater}.storeservices");
        List<?> storeservices = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
        //List<?>storeservices=response.extract().path("data.find{it.services}.services.findAll{it.name=='Magnolia Home Theater'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of service name = Magnolia Home Theater: "+storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020(){
        List<Integer>listOflat=response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores"+listOflat);
        System.out.println("------------------End of Test---------------------------");
    }


}