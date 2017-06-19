package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Crystal on 2017/6/17.
 */
@Entity
@Table(name = "user_monthly_cost", schema = "hotel_manage_system", catalog = "")
public class UserMonthlyCostEntity {
    /**
     * id
     */
    private int userMonthlyCostId;
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

    public UserMonthlyCostEntity(String userId, String cost, String date) {
        this.userId = userId;
        this.cost = cost;
        this.date = date;
    }

    @Id
    @GeneratedValue
    @Column(name = "userMonthlyCost_id", nullable = false)
    public int getUserMonthlyCostId() {
        return userMonthlyCostId;
    }

    public void setUserMonthlyCostId(int userMonthlyCostId) {
        this.userMonthlyCostId = userMonthlyCostId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cost", nullable = true, length = 255)
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
