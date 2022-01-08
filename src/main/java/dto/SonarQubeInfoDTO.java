package dto;

public class SonarQubeInfoDTO {
    private  boolean isSuccessful = true;
    private int bugs;
    private int codeSmell;
    private int vulnerabilities;
    private int securityHotspots;
    private double coverage;
    private double duplication;

    private String reliabilityRating;
    private String securityRating;
    private String securityReviewRating;
    private String maintainabilityRating;

    public boolean isSuccessful() {
        return isSuccessful;
    }
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

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

    public int getVulnerabilities() { return vulnerabilities; }

    public void setVulnerabilities(int vulnerabilities) { this.vulnerabilities = vulnerabilities; }

    public int getSecurityHotspots() { return securityHotspots; }
    public void setSecurityHotspots(int securityHotspots) { this.securityHotspots = securityHotspots; }

    public String getReliabilityRating() { return reliabilityRating; }
    public void setReliabilityRating(String reliabilityRating) { this.reliabilityRating = reliabilityRating; }

    public String getSecurityRating() { return securityRating; }
    public void setSecurityRating(String securityRating) { this.securityRating = securityRating; }

    public String getSecurityReviewRating() { return securityReviewRating; }
    public void setSecurityReviewRating(String securityReviewRating) { this.securityReviewRating = securityReviewRating; }

    public String getMaintainabilityRating() { return maintainabilityRating; }
    public void setMaintainabilityRating(String maintainabilityRating) { this.maintainabilityRating = maintainabilityRating; }
}
