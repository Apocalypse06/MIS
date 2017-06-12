package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "financial_order_report", schema = "hotel_manage_system", catalog = "")
public class FinancialOrderReportEntity {
    private int frfid;
    private String time;
    private String type;
    private Integer checkins;
    private Integer checkouts;
    private Double totalIncome;

    @Id
    @GeneratedValue
    @Column(name = "frfid", nullable = false)
    public int getFrfid() {
        return frfid;
    }

    public void setFrfid(int frfid) {
        this.frfid = frfid;
    }

    @Basic
    @Column(name = "time", nullable = true, length = 255)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "checkins", nullable = true)
    public Integer getCheckins() {
        return checkins;
    }

    public void setCheckins(Integer checkins) {
        this.checkins = checkins;
    }

    @Basic
    @Column(name = "checkouts", nullable = true)
    public Integer getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(Integer checkouts) {
        this.checkouts = checkouts;
    }

    @Basic
    @Column(name = "total_income", nullable = true, precision = 2)
    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialOrderReportEntity that = (FinancialOrderReportEntity) o;

        if (frfid != that.frfid) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (checkins != null ? !checkins.equals(that.checkins) : that.checkins != null)
            return false;
        if (checkouts != null ? !checkouts.equals(that.checkouts) : that.checkouts != null)
            return false;
        if (totalIncome != null ? !totalIncome.equals(that.totalIncome) : that.totalIncome != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = frfid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (checkins != null ? checkins.hashCode() : 0);
        result = 31 * result + (checkouts != null ? checkouts.hashCode() : 0);
        result = 31 * result + (totalIncome != null ? totalIncome.hashCode() : 0);
        return result;
    }
}
