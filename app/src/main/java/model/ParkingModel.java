package model;

/**
 * Created by kimhillzhang on 2018-03-07.
 */

public class ParkingModel {

    private String id;
    private String sjh;
    private String dwmc;
    private String dwdz;
    private String xm;
    private double dis;
    private double NoIsShare;
    private double IsShare;
    private int cwCount;
    private int cwOver;
    private int parkorcw;//1表示停车场，0表示停车位
    private String RentState;//0：表示未停车，1表示已有停车
    private String spxh;
    private String cwdz;
    private String tcsjh;
    private String spdj;
    private String spdw;
    private double mapx;
    private double mapy;
    private String parkprice;
    private String dw;

    public String getDwdz() {
        return dwdz;
    }
    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }
    public String getXm() {
        return xm;
    }
    public void setXm(String xm) {
        this.xm = xm;
    }
    public double getNoIsShare() {
        return NoIsShare;
    }
    public void setNoIsShare(double noIsShare) {
        NoIsShare = noIsShare;
    }
    public double getIsShare() {
        return IsShare;
    }
    public void setIsShare(double isShare) {
        IsShare = isShare;
    }
    public String getParkprice() {
        return parkprice;
    }
    public void setParkprice(String parkprice) {
        this.parkprice = parkprice;
    }
    public String getDw() {
        return dw;
    }
    public void setDw(String dw) {
        this.dw = dw;
    }
    public double getMapx() {
        return mapx;
    }
    public void setMapx(double mapx) {
        this.mapx = mapx;
    }
    public double getMapy() {
        return mapy;
    }
    public void setMapy(double mapy) {
        this.mapy = mapy;
    }
    public String getSpdj() {
        return spdj;
    }
    public void setSpdj(String spdj) {
        this.spdj = spdj;
    }
    public String getSpdw() {
        return spdw;
    }
    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }
    public int getCwOver() {
        return cwOver;
    }
    public void setCwOver(int cwOver) {
        this.cwOver = cwOver;
    }
    public String getTcsjh() {
        return tcsjh;
    }
    public void setTcsjh(String tcsjh) {
        this.tcsjh = tcsjh;
    }
    public String getCwdz() {
        return cwdz;
    }
    public void setCwdz(String cwdz) {
        this.cwdz = cwdz;
    }
    public String getSpxh() {
        return spxh;
    }
    public void setSpxh(String spxh) {
        this.spxh = spxh;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getParkorcw() {
        return parkorcw;
    }
    public void setParkorcw(int parkorcw) {
        this.parkorcw = parkorcw;
    }
    public int getCwCount() {
        return cwCount;
    }
    public void setCwCount(int cwCount) {
        this.cwCount = cwCount;
    }
    public String getSjh() {
        return sjh;
    }
    public void setSjh(String sjh) {
        this.sjh = sjh;
    }
    public String getDwmc() {
        return dwmc;
    }
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
    public double getDis() {
        return dis;
    }
    public void setDis(double dis) {
        this.dis = dis;
    }
    public String getRentState() {
        return RentState;
    }
    public void setRentState(String rentState) {
        RentState = rentState;
    }


}
