package com.atlas.mygoods.models.User;

import lombok.Data;

@Data
public class RoleToUserRequest {
    private Long userId;
    private String roleName;
}
