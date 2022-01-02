package adapter.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SonarInfoGsonAdapter {
    protected List<SonarInfo> measures;

    class SonarInfo {
//        private  boolean isSuccessful = true;
        @SerializedName("metric")
        protected String key;
        protected double value;
    }
}
