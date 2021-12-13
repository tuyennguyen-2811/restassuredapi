package steps;

import api.type.ApiDelete;
import api.type.ApiGet;
import api.type.ApiPost;
import api.type.ApiPut;
import controller.JsonHandle;
import controller.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;


public class StepsDefinition {
    ApiGet getMethod = new ApiGet();
    ApiPost postMethod = new ApiPost();
    ApiDelete deleteMethod = new ApiDelete();
    ApiPut putMethod= new ApiPut();
    JsonHandle json = new JsonHandle();
    JSONParser parser = new JSONParser();
    private ScenarioContext scenarioContext;

    public StepsDefinition(ScenarioContext context) {

        scenarioContext = context;
    }

    @Given("I get Github owner ID")
    public void getGitOwner() {
        Response response = getMethod.getUser();
        Assert.assertEquals(response.getStatusCode(), 200);
        ResponseBody bodyResponse = response.body();
        String ownerGithub = json.readOrUpdateJsonBody(bodyResponse,"login",false,null,null);
        scenarioContext.setContext("owner", ownerGithub);

         System.out.println(ownerGithub);

    }

    @When("I create a Github repository")
    public void createRepo() {
        Response response = postMethod.createRepo();
        Assert.assertEquals(response.getStatusCode(), 201);
        ResponseBody bodyResponse = response.body();
        String gitRepoName = json.readOrUpdateJsonBody(bodyResponse,"name",false,null,null);
        scenarioContext.setContext("repoName", gitRepoName);
    }
    @And("I update a Github repository {string}")
    public void updateRepo(String oldName) throws ParseException {
        Response response = putMethod.updateRepo(oldName);
        Assert.assertEquals(response.getStatusCode(), 200);
        ResponseBody bodyResponse = response.body();
        String gitRepoName = json.readOrUpdateJsonBody(bodyResponse,"name",false,null,null);
        scenarioContext.setContext("repoName", gitRepoName);
        System.out.println("owner: " + gitRepoName);




    }

    @Then("I delete repository")
    public void deleteGitRepo() {
        String owner = scenarioContext.getContext("owner").toString();
        String repoName = scenarioContext.getContext("repoName").toString();
        Response response = deleteMethod.deleteRepo(owner,"/"+ repoName);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
    @When("I get all users")
    public void getUser() {
        String userlist= getMethod.getUserNew();
    }

}