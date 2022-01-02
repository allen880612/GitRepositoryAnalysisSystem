package usecase;

import dto.SonarBugInfoDTO;
import java.util.List;
import dto.SonarBugListDTO;
import dto.SonarQubeInfoDTO;

public interface SonarQubeAccessor {
    boolean isSonarProjectValid();
    SonarQubeInfoDTO getSonarInfo();
    SonarBugListDTO getSonarIssues();
}
