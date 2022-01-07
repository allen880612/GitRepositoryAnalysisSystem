package dto;

import java.util.ArrayList;
import java.util.List;

public class SonarIssueListDTO {
    private boolean isSuccessful;
    private int count;
    private double effortTotal;
    private List<SonarIssueInfoDTO> bugs;

    public SonarIssueListDTO(int count, double effortTotal) {
        this.count = count;
        this.effortTotal = effortTotal;
        this.bugs = new ArrayList<>();
        isSuccessful = true;
    }

    public SonarIssueListDTO() {
        this.count = 0;
        this.effortTotal = 0.0;
        this.bugs = new ArrayList<>();
        isSuccessful = true;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getEffortTotal() {
        return effortTotal;
    }

    public void setEffortTotal(double effortTotal) {
        this.effortTotal = effortTotal;
    }

    public List<SonarIssueInfoDTO> getBugs() {
        return bugs;
    }

    public void addBugs(SonarIssueInfoDTO bugs) {
        this.bugs.add(bugs);
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
