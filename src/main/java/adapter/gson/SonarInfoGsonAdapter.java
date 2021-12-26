package adapter.gson;

import java.util.List;

public class SonarInfoGsonAdapter {
    protected List<SonarInfo> measures;

    class SonarInfo {
//        private  boolean isSuccessful = true;
        protected String metric;
        protected double value;
    }
}
