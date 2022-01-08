package FrontEnd;

public class Variables {

    public static  final Integer TIME_OUT_SECONDS = 10;

    public static final String DRIVER_PATH = "/webDriver/chromedriver.exe";

    private static final String url_base = "http://localhost:8080/GitRepositoryAnalysisSystem/frontEnd/";
    public static final String ADD_PROJECT_URL = url_base + "createproject";
    public static final String LOGIN_URL = url_base + "userLogin";
    public static final String REGISTERED_LOGIN_URL = url_base + "LoginPage";
    public static final String Choose_project_URL = url_base + "choose-project";
    public static final String REGISTER_URL = url_base + "signup";
}
