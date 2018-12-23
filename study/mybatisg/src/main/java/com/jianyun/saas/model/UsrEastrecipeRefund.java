package com.jianyun.saas.model;

import java.math.BigDecimal;

public class UsrEastrecipeRefund extends UsrEastrecipeRefundKey {
    private String reciperelId;

    private String recipeId;

    private String medId;

    private String medNm;

    private BigDecimal medPrice;

    private BigDecimal medNum;

    private Integer refundNum;

    private Integer recoveryNum;

    private BigDecimal totalPrice;

    private String medMethod;

    private Short dosage;

    private String medForm;

    private String productPlace;

    private String medSpec;

    private String medUnit;

    private String tmSmp;

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

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getMedNm() {
        return medNm;
    }

    public void setMedNm(String medNm) {
        this.medNm = medNm;
    }

    public BigDecimal getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(BigDecimal medPrice) {
        this.medPrice = medPrice;
    }

    public BigDecimal getMedNum() {
        return medNum;
    }

    public void setMedNum(BigDecimal medNum) {
        this.medNum = medNum;
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

    public String getMedMethod() {
        return medMethod;
    }

    public void setMedMethod(String medMethod) {
        this.medMethod = medMethod;
    }

    public Short getDosage() {
        return dosage;
    }

    public void setDosage(Short dosage) {
        this.dosage = dosage;
    }

    public String getMedForm() {
        return medForm;
    }

    public void setMedForm(String medForm) {
        this.medForm = medForm;
    }

    public String getProductPlace() {
        return productPlace;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace;
    }

    public String getMedSpec() {
        return medSpec;
    }

    public void setMedSpec(String medSpec) {
        this.medSpec = medSpec;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public String getTmSmp() {
        return tmSmp;
    }

    public void setTmSmp(String tmSmp) {
        this.tmSmp = tmSmp;
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