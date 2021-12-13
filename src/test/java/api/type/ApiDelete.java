package api.type;

import controller.GlobalConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiDelete extends GlobalConfig {

    GlobalConfig apiConf = new GlobalConfig();
    //https://www.javadoc.io/doc/io.rest-assured/rest-assured/latest/io/restassured/response/ResponseOptions.html
     public void deleteUser(int userId){
         Response response=
                 RestAssured
                         .given()
                            .header("Content-Type", "application/json")
                         .when()
                            .delete("https://reqres.in/api/users" + userId)
                         .then()
                            .extract().response();
         System.out.println(response.getBody().asString()  +" ..." + response.getStatusCode());

     }
    public Response deleteRepo(String owner, String repo) {
        apiConf.authenticationWithPrivateToken(apiConf.BASE_URL, apiConf.token);
        response = apiConf.request.delete("/repos/" + owner + repo);
        return response;
    }

    public static void main(String[] args) {
        ApiDelete delete = new ApiDelete();

        delete.deleteUser(969);
    }
}