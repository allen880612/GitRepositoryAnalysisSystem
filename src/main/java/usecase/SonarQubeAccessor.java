package usecase;

import dto.SonarQubeInfoDTO;

import java.util.List;

public interface SonarQubeAccessor {

    SonarQubeInfoDTO getSonarInfo(String id);
    List<SonarQubeInfoDTO> getSonarBugs(String id);

}
