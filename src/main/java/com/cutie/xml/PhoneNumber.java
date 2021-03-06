package com.cutie.xml;

/**
 * Created by cutie on 2018/9/20.
 */
public class PhoneNumber {
    private int code;
    private String number;
    // ... constructors and methods

    public PhoneNumber(int code, String number) {
        this.code = code;
        this.number = number;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
