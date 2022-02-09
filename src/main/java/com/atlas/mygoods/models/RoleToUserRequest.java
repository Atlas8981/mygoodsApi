package com.atlas.mygoods.models;

import lombok.Data;

@Data
public class RoleToUserRequest {
    private Long userId;
    private String roleName;
}
