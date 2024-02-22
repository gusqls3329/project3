package com.team5.projrental.entities.enums;

import lombok.Getter;

@Getter
public enum Auth {
    ADMIN(3), USER(1), COMP(-1);

    private int iauth;

    Auth(int iauth) {
        this.iauth = iauth;
    }


    public static Auth getAuth(String role) {
        return role.equalsIgnoreCase("ROLE_COMP") ? Auth.COMP :
                role.equalsIgnoreCase("ROLE_USER") ? Auth.USER : null;

    }
}
