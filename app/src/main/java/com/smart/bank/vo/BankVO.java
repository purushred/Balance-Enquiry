package com.smart.bank.vo;

import java.io.Serializable;

/**
 * Created by purushoy on 11/20/2016.
 */

public class BankVO implements Serializable {
    private String name;
    private String shortName;
    private String ifsc;
    private String url;
    private String phoneNo;
    private String bankLogo;
    private String mobileCode;
    private boolean isFavourite;

    public BankVO(String mobileCode, String name, String bankLogo, String shortName, String ifsc, String url, String phoneNo, boolean isFavourite) {
        this.name = name;
        this.shortName = shortName;
        this.ifsc = ifsc;
        this.url = url;
        this.phoneNo = phoneNo;
        this.bankLogo = bankLogo;
        this.mobileCode = mobileCode;
        this.isFavourite = isFavourite;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
