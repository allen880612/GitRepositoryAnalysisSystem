package dto;

import java.util.ArrayList;
import java.util.List;

public class UserProjectListDTO {
    private List<UserProjectDTO> userProjectDTOList;

    public UserProjectListDTO(){userProjectDTOList = new ArrayList<>();}
    public UserProjectListDTO(List<UserProjectDTO> userProjectDTOList){this.userProjectDTOList=userProjectDTOList;};

    public void setUserProjectDTOList(List<UserProjectDTO> userProjectDTOList) {this.userProjectDTOList = userProjectDTOList;}
    public List<UserProjectDTO> getUserProjectDTOList() {return userProjectDTOList;}
    public void addUserProjectDTO(UserProjectDTO userProjectDTO){
        this.userProjectDTOList.add(userProjectDTO);
    }
}
