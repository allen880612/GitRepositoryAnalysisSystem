package usecase;

import dto.GithubUserDTO;

public interface GithubAccessor {
    String getGitHubToken(String code);
    GithubUserDTO getRequester(String token);
}
