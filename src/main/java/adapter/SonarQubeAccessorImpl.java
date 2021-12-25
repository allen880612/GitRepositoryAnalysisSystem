package adapter;

import dto.SonarQubeInfoDTO;
import usecase.HttpsRequester;
import usecase.PostJSONWithHttpURLConnection;
import usecase.SonarQubeAccessor;

import java.util.List;

public class SonarQubeAccessorImpl implements SonarQubeAccessor {
    private PostJSONWithHttpURLConnection postRequester;
    private HttpsRequester getRequester;

    private String clientId;
    private String clientSecret;

    public SonarQubeAccessorImpl(){

    }

    @Override
    public SonarQubeInfoDTO getSonarInfo(String id) {
        return null;
    }

    @Override
    public List<SonarQubeInfoDTO> getSonarBugs(String id) {
        return null;
    }
}
