package com.team5.projrental.common.aop.category;

public enum CountCategory {

    PRODUCT(Inner.P);

    private Inner inner;
    CountCategory(Inner inner) {
        this.inner = inner;
    }

    public Inner getInner() {
        return inner;
    }

    public enum Inner {
        P
    }

}
