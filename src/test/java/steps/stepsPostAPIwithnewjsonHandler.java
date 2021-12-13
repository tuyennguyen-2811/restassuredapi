package steps;

import api.type.ApiPost;
import controller.JsonHandler;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

public class stepsPostAPIwithnewjsonHandler {

JsonHandler json = new JsonHandler();
    ApiPost postMethod = new ApiPost();

    @When("I create a Github repository with new json")
    public void createRepo() throws ParseException {
        Response response = postMethod.createRepo();
        Assert.assertEquals(response.getStatusCode(), 201);
        String bodyResponse = response.getBody().asString();
        String gitRepoName = json.parseStringToJsonObject(bodyResponse).get("name").toString();

    }

    public static void main(String[] args) throws ParseException {
        stepsPostAPIwithnewjsonHandler abc = new stepsPostAPIwithnewjsonHandler();
        abc.createRepo();

    }

}
