package com.arpon007.netflixclone.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "email-service")
public interface EmailFeignClient {

    @PostMapping("/api/email/send")
    void sendEmail(@RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body);

}
