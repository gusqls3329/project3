package com.team5.projrental.entities.enums;

public enum Auth {
    ADMIN, USER, COMP;

    public static Auth getAuth(String role) {
        return role.equalsIgnoreCase("ROLE_COMP") ? Auth.COMP :
                role.equalsIgnoreCase("ROLE_USER") ? Auth.USER : null;

    }
}
