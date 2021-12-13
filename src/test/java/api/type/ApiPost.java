package api.type;

import controller.GlobalConfig;
import controller.JsonHandle;
import controller.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class ApiPost extends GlobalConfig {
    GlobalConfig apiConf = new GlobalConfig();
    JsonHandle json = new JsonHandle();
    Utilities ultil = new Utilities();

    String[] keyUpdate = {"name"};
    String[] valueUpdate = {"tuyenrepo9"};

    public Response createRepo() {
        apiConf.authenticationWithPrivateToken(apiConf.BASE_URL, apiConf.token);
        response = apiConf.request
                .body(json.readOrUpdateJsonBodyFromFile("createRepo",true, keyUpdate,valueUpdate))
                .post("/user/repos");
        return response;

    }

    public void creatUser(){
        JSONObject object = new JSONObject();
        object.put("name", "tuyen");
        object.put("job", "tester");
        Response response=
                RestAssured
                        .given()
                                .header("Content-Type", "application/json")
                                .body(object.toJSONString())
                        .when()
                                .post("https://reqres.in/api/users")
                        .then()
                                .extract().response();

            System.out.println("Response body data: " + response.getBody().asString()  + "\n "+ " Response  code is: " + response.getStatusCode());

    }

    public static void main(String[] args) {
        ApiPost test= new ApiPost();
        test.creatUser();
    }



}