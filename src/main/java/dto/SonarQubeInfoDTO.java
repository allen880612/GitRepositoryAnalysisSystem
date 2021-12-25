package dto;

public class SonarQubeInfoDTO {
    private double coverage;
    private int bugs;
    private int codeSmell;
    private double duplication;

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public int getCodeSmell() {
        return codeSmell;
    }

    public void setCodeSmell(int codeSmell) {
        this.codeSmell = codeSmell;
    }

    public double getDuplication() {
        return duplication;
    }

    public void setDuplication(double duplication) {
        this.duplication = duplication;
    }

}
