package usecase;

import adapter.SonarQubeAccessorImpl;
import adapter.gson.SonarBugListGsonAdapter;
import com.google.gson.Gson;
import domain.SonarProject;
import dto.SonarBugListDTO;
import dto.SonarQubeInfoDTO;
import adapter.gson.SonarInfoGsonAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SonarAccessorTest {

    private String token;
    private String hostUrl;
    private String projectKey;

    @Before
    public void setUp(){
        token = "tojen";
        hostUrl = "IP";
        projectKey = "GSAS";
    }

    @Test
    public void GetSonarInfoTest() {
        SonarProject sonarProject = new SonarProject(hostUrl, projectKey, token);
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        SonarQubeInfoDTO sonarQubeInfoDto = sonarQubeAccessor.getSonarInfo();

        Assert.assertTrue(sonarQubeInfoDto.isSuccessful());
        Assert.assertEquals(16, sonarQubeInfoDto.getBugs());
    }

    @Test
    public void GetBugListTest() {
        SonarProject sonarProject = new SonarProject(hostUrl, projectKey, token);
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        SonarBugListDTO sonarBugListDto = sonarQubeAccessor.getSonarBugs();

        Assert.assertTrue(sonarBugListDto.isSuccessful());
    }

    @Test
    public void ValidTokenShouldBeValidTest() {
        SonarProject sonarProject = new SonarProject(hostUrl, projectKey, token);
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        Assert.assertTrue(sonarQubeAccessor.isSonarProjectValid());
    }

    @Test
    public void InvalidHostShouldBeInvalidTest() {
        SonarProject sonarProject = new SonarProject("invalidUrl", projectKey, token);
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        Assert.assertFalse(sonarQubeAccessor.isSonarProjectValid());
    }

    @Test
    public void InvalidProjectKeyShouldBeInvalidTest() {
        SonarProject sonarProject = new SonarProject(hostUrl, "notExist", token);
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        Assert.assertFalse(sonarQubeAccessor.isSonarProjectValid());
    }

    @Test
    public void InvalidTokenShouldBeInvalidTest() {
        SonarProject sonarProject = new SonarProject(hostUrl, projectKey, "invalidToken");
        SonarQubeAccessor sonarQubeAccessor = new SonarQubeAccessorImpl(sonarProject);

        Assert.assertFalse(sonarQubeAccessor.isSonarProjectValid());
    }

    @Test
    public void MappingDtoByGsonTest() {
        Gson gson = new Gson();
        SonarInfoGsonAdapter sonarInfoAdapter;
        String content = "{\n" +
                "    \"measures\": [\n" +
                "        {\n" +
                "            \"metric\": \"bugs\",\n" +
                "            \"value\": \"16\",\n" +
                "            \"component\": \"GSAS\",\n" +
                "            \"bestValue\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"metric\": \"code_smells\",\n" +
                "            \"value\": \"92\",\n" +
                "            \"component\": \"GSAS\",\n" +
                "            \"bestValue\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"metric\": \"coverage\",\n" +
                "            \"value\": \"0.0\",\n" +
                "            \"component\": \"GSAS\",\n" +
                "            \"bestValue\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"metric\": \"duplicated_lines_density\",\n" +
                "            \"value\": \"4.9\",\n" +
                "            \"component\": \"GSAS\",\n" +
                "            \"bestValue\": false\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        sonarInfoAdapter = gson.fromJson(content, SonarInfoGsonAdapter.class);
//        System.out.println(gson.toJson(sonarInfoAdapter));
    }

    @Test
    public void MappingBugListTest () {
        String content = "{\n" +
                "    \"total\": 16,\n" +
                "    \"p\": 1,\n" +
                "    \"ps\": 10,\n" +
                "    \"paging\": {\n" +
                "        \"pageIndex\": 1,\n" +
                "        \"pageSize\": 10,\n" +
                "        \"total\": 16\n" +
                "    },\n" +
                "    \"effortTotal\": 80,\n" +
                "    \"issues\": [\n" +
                "        {\n" +
                "            \"key\": \"AX3OQLB4sNsGAYBEYhkF\",\n" +
                "            \"rule\": \"java:S2095\",\n" +
                "            \"severity\": \"BLOCKER\",\n" +
                "            \"component\": \"GSAS:src/main/java/adapter/account/AccountRepositoryImpl.java\",\n" +
                "            \"project\": \"GSAS\",\n" +
                "            \"line\": 144,\n" +
                "            \"hash\": \"8f0cf662cb951a7f279cf7bc1619adac\",\n" +
                "            \"textRange\": {\n" +
                "                \"startLine\": 144,\n" +
                "                \"endLine\": 144,\n" +
                "                \"startOffset\": 17,\n" +
                "                \"endOffset\": 45\n" +
                "            },\n" +
                "            \"flows\": [],\n" +
                "            \"status\": \"OPEN\",\n" +
                "            \"message\": \"Use try-with-resources or close this \\\"PreparedStatement\\\" in a \\\"finally\\\" clause.\",\n" +
                "            \"effort\": \"5min\",\n" +
                "            \"debt\": \"5min\",\n" +
                "            \"author\": \"allen880612@gmail.com\",\n" +
                "            \"tags\": [\n" +
                "                \"cert\",\n" +
                "                \"cwe\",\n" +
                "                \"denial-of-service\",\n" +
                "                \"leak\"\n" +
                "            ],\n" +
                "            \"creationDate\": \"2021-12-17T14:58:50+0000\",\n" +
                "            \"updateDate\": \"2021-12-18T15:54:30+0000\",\n" +
                "            \"type\": \"BUG\",\n" +
                "            \"scope\": \"MAIN\",\n" +
                "            \"quickFixAvailable\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"AX3OQLB4sNsGAYBEYhkH\",\n" +
                "            \"rule\": \"java:S2095\",\n" +
                "            \"severity\": \"BLOCKER\",\n" +
                "            \"component\": \"GSAS:src/main/java/adapter/account/AccountRepositoryImpl.java\",\n" +
                "            \"project\": \"GSAS\",\n" +
                "            \"line\": 25,\n" +
                "            \"hash\": \"2520c0f62e0584a7ad81cc9fd2f3c21a\",\n" +
                "            \"textRange\": {\n" +
                "                \"startLine\": 25,\n" +
                "                \"endLine\": 25,\n" +
                "                \"startOffset\": 46,\n" +
                "                \"endOffset\": 75\n" +
                "            },\n" +
                "            \"flows\": [],\n" +
                "            \"status\": \"OPEN\",\n" +
                "            \"message\": \"Use try-with-resources or close this \\\"PreparedStatement\\\" in a \\\"finally\\\" clause.\",\n" +
                "            \"effort\": \"5min\",\n" +
                "            \"debt\": \"5min\",\n" +
                "            \"author\": \"allen880612@gmail.com\",\n" +
                "            \"tags\": [\n" +
                "                \"cert\",\n" +
                "                \"cwe\",\n" +
                "                \"denial-of-service\",\n" +
                "                \"leak\"\n" +
                "            ],\n" +
                "            \"creationDate\": \"2021-10-25T10:09:06+0000\",\n" +
                "            \"updateDate\": \"2021-12-18T15:54:30+0000\",\n" +
                "            \"type\": \"BUG\",\n" +
                "            \"scope\": \"MAIN\",\n" +
                "            \"quickFixAvailable\": false\n" +
                "        }" +
                "   ]" +
                "}";

        Gson gson = new Gson();
        SonarBugListGsonAdapter adapter = gson.fromJson(content,SonarBugListGsonAdapter.class);
//        System.out.println(gson.toJson(adapter));
    }


}
