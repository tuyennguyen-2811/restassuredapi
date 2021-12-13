package api.type;


import controller.GlobalConfig;
import controller.JsonHandle;
import controller.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiPut extends GlobalConfig {

    GlobalConfig apiConf = new GlobalConfig();
    JsonHandle json = new JsonHandle();
    Utilities ultil = new Utilities();

    String[] keyUpdate = {"name"};
    String[] valueUpdate = {"tuyenrepo1update2"};
    public static String  statusCode="dsdadad";


    public void  updateuser(String newuser, int userId) {
        Response response=  RestAssured
                             .given()
                                .header("Content-Type", "application/json")
                                .body("{\r\n    \"name\": \"tuyendethuong\",\r\n    \"job\": \"leader\"\r\n}")
                            .when()
                               .put("https://reqres.in/api/users/" + userId)
                            .then()
                                .extract().response();
        System.out.println(response.getBody().asString()  +" ..." + response.getStatusCode());

    }
    public Response  updateRepo(String oldRepoName) {
        apiConf.authenticationWithPrivateToken(apiConf.BASE_URL, apiConf.token);
        response = apiConf.request
                .body(json.readOrUpdateJsonBodyFromFile("createRepo",true, keyUpdate,valueUpdate))
                .patch("repos/"+ "apiTesting-fw/"+ oldRepoName);
        System.out.println(response.toString());
        return response;
    }

    public static void main(String[] args) {
        ApiPut api = new ApiPut();
        api.updateuser("tuyendethuong", 969);
    }

}
