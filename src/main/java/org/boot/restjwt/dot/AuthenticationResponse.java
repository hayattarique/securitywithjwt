package org.boot.restjwt.dot;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String username;
    private String token;

}
