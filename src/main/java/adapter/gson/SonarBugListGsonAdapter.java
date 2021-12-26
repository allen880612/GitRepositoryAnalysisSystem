package adapter.gson;

import java.util.List;

public class SonarBugListGsonAdapter {
    protected int total;
    protected int effortTotal;
    protected List<BugInfo> issues;

    class BugInfo {
//        private  boolean isSuccessful = true;
        protected String key;
        protected String severity;
        protected String component;
        protected String message;
        protected String effort;
        protected String updateDate;
    }
}
