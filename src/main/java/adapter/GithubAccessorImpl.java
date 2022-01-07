package adapter;

import dto.GithubUserDTO;
import org.json.JSONObject;
import usecase.GithubAccessor;
import usecase.PostJSONWithHttpURLConnection;

import java.io.IOException;

public class GithubAccessorImpl implements GithubAccessor {
    private PostJSONWithHttpURLConnection postRequester;
    private HttpsRequester getRequester;

    private String clientId;
    private String clientSecret;

    public GithubAccessorImpl() {
//        clientId = System.getenv("Github_Client_Id");
//        client_secret = System.getenv("Github_Client_Secret");
        clientId = "27ea0af1aeabd39a7d5d";
        clientSecret = "73982553f4ca36536646a033e1595dc126694b2e";


    }

    @Override
    public String getGitHubToken(String code) {
        postRequester = new PostJSONWithHttpURLConnection();

        String post_url = "https://github.com/login/oauth/access_token";


        JSONObject body = new JSONObject();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("code", code);

        JSONObject response = new JSONObject();
        try {
            response = postRequester.makeHttpRequest(post_url, body.toString());
            String token = response.get("access_token").toString();
            System.out.println(response.toString());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GithubUserDTO getUserInfo(String token) {
        getRequester = new HttpsRequester();
        getRequester.addHTTPSGetProperty("Content-Type", "application/json");
        getRequester.addHTTPSGetProperty("Authorization", "Bearer " + token);
        GithubUserDTO githubUserDTO = new GithubUserDTO();
        JSONObject response = null;

        try {
            response = getRequester.httpsGet("https://api.github.com/user").getJSONObject(0);
        } catch (IOException e) {
            githubUserDTO.setSuccessful(false);
            return githubUserDTO;
        }

        githubUserDTO.setId(response.get("id").toString());
        githubUserDTO.setName(response.get("login").toString());
        githubUserDTO.setAccount(response.get("email").toString());
        githubUserDTO.setAvatarUrl(response.get("avatar_url").toString());
        return githubUserDTO;
    }
}
