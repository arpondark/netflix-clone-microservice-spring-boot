package com.arpon007.netflixclone.auth.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupEvent {
    private String email;
    private String fullName;
}
