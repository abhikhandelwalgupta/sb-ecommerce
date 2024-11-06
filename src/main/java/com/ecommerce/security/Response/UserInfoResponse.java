package com.ecommerce.security.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private  Integer id;

    private String username;
    private List<String> roles;
    @JsonIgnore
    private String jwtToken;

    public UserInfoResponse(Integer id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}




