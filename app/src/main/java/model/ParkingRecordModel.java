package model;

/**
 * Created by YJW on 2018/3/9.
 */

public class ParkingRecordModel {
    private String sjh;  //车牌号
    private String state;//已支付
    private int money;   //停车费
    private String strattime;  //进场时间
    private String stoptime;   //出场时间

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStrattime() {
        return strattime;
    }

    public void setStrattime(String strattime) {
        this.strattime = strattime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }
}
