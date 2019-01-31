package com.jung.hmis.common.enums;

/**
 * @author jimlly
 * @createtime: 2017/11/27上午10:33
 */
public enum UserType {

    /**
     * 员工
     */
    User("员工","0"),
    /**
     * 客户
     */
    Cust("客户","1");


    public String getValue() {
        return value;
    }
    public String getName(){return  name;}

    private final String value;
    private final String name;

    UserType(String name,String value) {
        this.value = value;
        this.name =name;
    }
}
