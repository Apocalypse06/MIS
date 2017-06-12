package top.yyf.mess.input;

/**
 * Created by Daniel on 2017/3/7.
 */
public class RoomPlan {
    private String type;
    private int accommodations;
    private String des;
    private String startDate;
    private String endDate;
    private double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(int accommodations) {
        this.accommodations = accommodations;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
