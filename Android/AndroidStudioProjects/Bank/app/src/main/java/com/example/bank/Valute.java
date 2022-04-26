package com.example.bank;

import java.io.Serializable;

public class Valute implements Serializable {

    String CharCode;
    String Value;

    public Valute(String charc, String val){
        this.CharCode = charc;
        this.Value = val;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }
}
