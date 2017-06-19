package top.yyf.mess.retmess;

/**
 * 会员月消费
 * Created by Crystal on 2017/6/17.
 */
public class UserMonthlyCostRetMess {
    /**
     * 会员id
     */
    private String userId;
    /**
     * 开销
     */
    private String cost;
    /**
     * 月份
     */
    private String date;

    public UserMonthlyCostRetMess(){

    }

    public UserMonthlyCostRetMess(String userId, String cost, String date) {
        this.userId = userId;
        this.cost = cost;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
