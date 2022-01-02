package dto;

import java.util.ArrayList;
import java.util.List;

public class SonarBugListDTO {
    private boolean isSuccessful;
    private int count;
    private double effortTotal;
    private List<SonarBugInfoDTO> bugs;

    public SonarBugListDTO(int count, double effortTotal) {
        this.count = count;
        this.effortTotal = effortTotal;
        this.bugs = new ArrayList<>();
        isSuccessful = true;
    }

    public SonarBugListDTO() {
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

    public List<SonarBugInfoDTO> getBugs() {
        return bugs;
    }

    public void addBugs(SonarBugInfoDTO bugs) {
        this.bugs.add(bugs);
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
