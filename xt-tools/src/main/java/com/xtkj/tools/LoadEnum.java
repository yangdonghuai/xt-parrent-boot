package com.xtkj.tools;

public enum LoadEnum {

    HOME("com.xtkj.pojo.UserInfo");

    private final String clazz;

    LoadEnum(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz(){
        return clazz;
    }

}
