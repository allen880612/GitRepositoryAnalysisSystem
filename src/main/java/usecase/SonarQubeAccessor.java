package usecase;

import dto.SonarQubeInfoDTO;

import java.util.List;

public interface SonarQubeAccessor {

    SonarQubeInfoDTO getSonarInfo();
    List<SonarQubeInfoDTO> getSonarBugs();

}
