package usecase.project;

import domain.GitRepository;

import java.util.List;

public interface CreateProjectOutput {
    void setId(String id);
    String getId();

    void setName(String name);
    String getName();

    boolean getIsSuccessful();
    void setIsSuccessful(boolean isSuccessful);
}
