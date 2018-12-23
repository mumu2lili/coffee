package com.jianyun.saas.model;

import java.math.BigDecimal;

public class UsrCurerecipeRefund extends UsrCurerecipeRefundKey {
    private String reciperelId;

    private String recipeId;

    private String cureNm;

    private BigDecimal curePrice;

    private Integer cureNum;

    private Integer refundNum;

    private Integer recoveryNum;

    private BigDecimal totalPrice;

    private String tmSmp;

    private String cureTyp;

    private Integer serpkgNum;

    private Integer residueNum;

    private BigDecimal discount;

    private BigDecimal discountPrice;

    public String getReciperelId() {
        return reciperelId;
    }

    public void setReciperelId(String reciperelId) {
        this.reciperelId = reciperelId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getCureNm() {
        return cureNm;
    }

    public void setCureNm(String cureNm) {
        this.cureNm = cureNm;
    }

    public BigDecimal getCurePrice() {
        return curePrice;
    }

    public void setCurePrice(BigDecimal curePrice) {
        this.curePrice = curePrice;
    }

    public Integer getCureNum() {
        return cureNum;
    }

    public void setCureNum(Integer cureNum) {
        this.cureNum = cureNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getRecoveryNum() {
        return recoveryNum;
    }

    public void setRecoveryNum(Integer recoveryNum) {
        this.recoveryNum = recoveryNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp;
    }

    public String getCureTyp() {
        return cureTyp;
    }

    public void setCureTyp(String cureTyp) {
        this.cureTyp = cureTyp;
    }

    public Integer getSerpkgNum() {
        return serpkgNum;
    }

    public void setSerpkgNum(Integer serpkgNum) {
        this.serpkgNum = serpkgNum;
    }

    public Integer getResidueNum() {
        return residueNum;
    }

    public void setResidueNum(Integer residueNum) {
        this.residueNum = residueNum;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}