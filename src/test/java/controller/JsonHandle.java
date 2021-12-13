package controller;

import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonHandle {

    public String readOrUpdateJsonBodyFromFile(String key, boolean isArray, String[] keyUpdate, String[] valueUpdate) {
        JSONParser parser = new JSONParser();
        String apiValue = null;

        try {
            Object obj = parser.parse(new FileReader("src/test/resources/Json/JsonBody.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Object tempValue = jsonObject.get(key);
            if(isArray = false){
                apiValue = tempValue.toString();
            } else if(keyUpdate == null || valueUpdate == null){
                apiValue = tempValue.toString();
            } else {
                JSONObject json = (JSONObject) parser.parse(tempValue.toString());
                for(int i = 0; i < keyUpdate.length; i++){
                    json.put(keyUpdate[i],valueUpdate[i]);
                }
                apiValue = json.toString();
            }
//            System.out.println(apiValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiValue;
    }
/*
*
* keyUpdate,keyValue is  null ->  read data from response
 * keyUpdate,keyValue is not null -> update
*
*
* */
    public String readOrUpdateJsonBody(ResponseBody response, String key, boolean isArray, String[] keyUpdate, String[] valueUpdate) {
        JSONParser parser = new JSONParser();
        String apiValue = null;

        try {
            Object obj = parser.parse(response.asString());
            JSONObject jsonObject = (JSONObject) obj;
            Object tempValue = jsonObject.get(key);
            if(isArray = false){
                apiValue = tempValue.toString();
            } else if(keyUpdate == null || valueUpdate == null){
                apiValue = tempValue.toString();
            } else {
                JSONObject json = (JSONObject) parser.parse(tempValue.toString());
                for(int i = 0; i < keyUpdate.length; i++){
                    json.put(keyUpdate[i],valueUpdate[i]);
                }
                apiValue = json.toString();
            }
//            System.out.println(apiValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiValue;
    }

    public String readDataFromJsonString(String stringJson, String key) {
        JSONParser parser = new JSONParser();
        String apiValue = null;

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(stringJson);
            Object tempValue = jsonObject.get(key);
            apiValue = tempValue.toString();

//            System.out.println(apiValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiValue;
    }

    public static void main(String[] args) {
        JsonHandle json = new JsonHandle();
        String a ="{owner: {\"gists_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/gists{\\/gist_id}\",\"repos_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/repos\",\"following_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/following{\\/other_user}\",\"starred_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/starred{\\/owner}{\\/repo}\",\"login\":\"apiTesting-fw\",\"followers_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/followers\",\"type\":\"User\",\"url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\",\"subscriptions_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/subscriptions\",\"received_events_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/received_events\",\"avatar_url\":\"https:\\/\\/avatars.githubusercontent.com\\/u\\/85815002?v=4\",\"events_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/events{\\/privacy}\",\"html_url\":\"https:\\/\\/github.com\\/apiTesting-fw\",\"site_admin\":false,\"id\":85815002,\"gravatar_id\":\"\",\"node_id\":\"MDQ6VXNlcjg1ODE1MDAy\",\"organizations_url\":\"https:\\/\\/api.github.com\\/users\\/apiTesting-fw\\/orgs\"}}";
       String  result = json.readDataFromJsonString(a, "login");
       System.out.println(result);
    }
}
