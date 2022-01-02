package adapter.gson;

import java.util.List;

public class SonarBugListGsonAdapter {
    protected int total;
    protected int effortTotal;
    protected List<BugInfo> issues;

    public class BugInfo {
        protected String type;
        protected String key;
        protected String severity;
        protected String component;
        protected String message;
        protected String effort;
        protected String updateDate;

        public String getType() { return type; }
        public String getKey() { return key; }
        public String getSeverity() { return severity; }
        public String getComponent() { return component; }
        public String getMessage() { return message; }
        public String getEffort() { return effort; }
        public String getUpdateDate() { return updateDate; }
    }

    public int getTotal() {
        return total;
    }

    public int getEffortTotal() {
        return effortTotal;
    }

    public List<BugInfo> getIssues() {
        return issues;
    }
}
