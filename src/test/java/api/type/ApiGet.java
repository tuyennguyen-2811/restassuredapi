package api.type;

import controller.GlobalConfig;
import controller.JsonHandle;
import controller.JsonHandler;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

public class ApiGet extends GlobalConfig {

    GlobalConfig apiConf = new GlobalConfig();
    JsonHandle json = new JsonHandle();

    public Response getUser() {
        apiConf.authenticationWithPrivateToken(apiConf.BASE_URL, apiConf.token);
        response = apiConf.request.get("/user");
        return response;
    }


    public String getUserNew(){
        Response response=
                RestAssured
                        .given()
//                            .baseUri("https://reqres.in")
                        .when()
                            .get("https://reqres.in/api/users")
                        .then()
                            .extract()
                            .response();
        Assert.assertEquals(response.getStatusCode(),200);
        String bodyData = response.getBody().asString();
        return bodyData;

    }



    public void getALlHeadersFromResponse(){
        Response response=
                RestAssured
                        .given()
                        .when()
                            .get("https://reqres.in/api/users")
                        .then()
                            .extract()
                            .response();

        System.out.println("All Headers of response are :- ");
        Headers allHeaders = response.getHeaders();
        for(Header header : allHeaders)
        {
            System.out.print(header.getName() +" : ");
            System.out.println(header.getValue());
        }

        System.out.println("Value of Header Content-Type : "+response.getHeader("Content-Type"));

    }
    public Response getRepos(String owner) {
        apiConf.authenticationWithPrivateToken(apiConf.BASE_URL, apiConf.token);
        response = apiConf.request.get("/" + owner +"/repos");
        return response;
    }
 //https://www.javadoc.io/doc/io.rest-assured/rest-assured/latest/io/restassured/response/ResponseOptions.html
//https://www.javadoc.io/doc/io.rest-assured/rest-assured/latest/io/restassured/http/Headers.html
    public static void main(String[] args) throws ParseException {
        ApiGet apiget= new ApiGet();
        JsonHandler json = new JsonHandler();
        String body= apiget.getUserNew();
       String data= json.parseStringToJsonObject(body).get("data").toString();
       System.out.println(data);
      //apiget.getALlHeadersFromResponse();

    }
}