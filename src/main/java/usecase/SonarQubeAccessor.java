package usecase;

import dto.SonarIssueListDTO;
import dto.SonarQubeInfoDTO;

public interface SonarQubeAccessor {
    boolean isSonarProjectValid();
    SonarQubeInfoDTO getSonarInfo();
    SonarIssueListDTO getSonarIssueList();
}
