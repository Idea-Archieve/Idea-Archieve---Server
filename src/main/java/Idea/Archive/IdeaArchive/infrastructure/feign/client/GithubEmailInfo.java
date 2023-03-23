package Idea.Archive.IdeaArchive.infrastructure.feign.client;

import Idea.Archive.IdeaArchive.infrastructure.feign.dto.response.GithubEmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GithubInfoClient", url = "https://api.github.com/user/emails")
@Component
public interface GithubEmailInfo {

    @GetMapping("?access_token={ACCESS_TOKEN}")
    GithubEmailResponse googleInfo(@PathVariable("ACCESS_TOKEN") String accessToken);
}
