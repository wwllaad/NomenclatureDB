package com.tavrida.bve;

public class Nomenclature {

    private String productCode;
    private String id;
    private String partCode;
    private String name;
    private String qty;
    private String unit;
    private String catalog;
    private String ecName;
    private String codeInCatalog;
    private String limitation;

    public Nomenclature(String productCode, String id, String partCode, String name, String qty, String unit, String catalog, String ecName, String codeInCatalog, String limitation) {
        this.productCode = productCode;
        this.id = id;
        this.partCode = partCode;
        this.name = name;
        this.qty = qty;
        this.unit = unit;
        this.catalog = catalog;
        this.ecName = ecName;
        this.codeInCatalog = codeInCatalog;
        this.limitation = limitation;
    }

    public Nomenclature() {
        this.productCode = "";
        this.id = "";
        this.partCode = "";
        this.name = "";
        this.qty = "";
        this.unit = "";
        this.catalog = "";
        this.ecName = "";
        this.codeInCatalog = "";
        this.limitation = "";
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getCodeInCatalog() {
        return codeInCatalog;
    }

    public void setCodeInCatalog(String codeInCatalog) {
        this.codeInCatalog = codeInCatalog;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    @Override
    public String toString() {
        return "Nomenclature{" +
                "productCode='" + productCode + '\'' +
                ", id='" + id + '\'' +
                ", partCode='" + partCode + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", unit='" + unit + '\'' +
                ", catalog='" + catalog + '\'' +
                ", ecName='" + ecName + '\'' +
                ", codeInCatalog='" + codeInCatalog + '\'' +
                ", limitation='" + limitation + '\'' +
                '}';
    }
}
