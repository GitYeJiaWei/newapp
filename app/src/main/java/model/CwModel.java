package model;

/**
 * Created by YJW on 2018/3/23.
 */

public class CwModel {
    private String id;
    private String sjh;
    private String dwmc;
    private String dwdz; //地址
    private String cw_details; //详情
    private double dis; //距离
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
    private String cwprice; //价格
    private String dw;      //费用详情
    private String order;   //预约
    private String parking;  //停车
    private String navi;     //导航

    public String getCw_details() {
        return cw_details;
    }

    public void setCw_details(String cw_details) {
        this.cw_details = cw_details;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getNavi() {
        return navi;
    }

    public void setNavi(String navi) {
        this.navi = navi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
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

    public int getCwCount() {
        return cwCount;
    }

    public void setCwCount(int cwCount) {
        this.cwCount = cwCount;
    }

    public int getCwOver() {
        return cwOver;
    }

    public void setCwOver(int cwOver) {
        this.cwOver = cwOver;
    }

    public int getParkorcw() {
        return parkorcw;
    }

    public void setParkorcw(int parkorcw) {
        this.parkorcw = parkorcw;
    }

    public String getRentState() {
        return RentState;
    }

    public void setRentState(String rentState) {
        RentState = rentState;
    }

    public String getSpxh() {
        return spxh;
    }

    public void setSpxh(String spxh) {
        this.spxh = spxh;
    }

    public String getCwdz() {
        return cwdz;
    }

    public void setCwdz(String cwdz) {
        this.cwdz = cwdz;
    }

    public String getTcsjh() {
        return tcsjh;
    }

    public void setTcsjh(String tcsjh) {
        this.tcsjh = tcsjh;
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

    public String getCwprice() {
        return cwprice;
    }

    public void setCwprice(String cwprice) {
        this.cwprice = cwprice;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }
}
