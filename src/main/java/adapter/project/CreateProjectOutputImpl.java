package adapter.project;

import usecase.project.CreateProjectOutput;

import java.util.List;

public class CreateProjectOutputImpl implements CreateProjectOutput {
    private String id;
    private String name;
    private boolean isSuccessful;
    @Override
    public boolean getIsSuccessful() {
        return this.isSuccessful;
    }
    @Override
    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
