package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "check_out", schema = "hotel_manage_system", catalog = "")
public class CheckOutEntity {
    private Integer checkoutId;
    private String time;
    private Double realConsume;
    private CheckInEntity checkin;
    private FinancialOrderEntity financialOrder;

    @Id
    @GeneratedValue
    @Column(name = "checkout_id", nullable = false)
    public Integer getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Integer checkoutId) {
        this.checkoutId = checkoutId;
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
    @Column(name = "real_consume", nullable = true, precision = 2)
    public Double getRealConsume() {
        return realConsume;
    }

    public void setRealConsume(Double realConsume) {
        this.realConsume = realConsume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckOutEntity that = (CheckOutEntity) o;

        if (checkoutId != that.checkoutId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (realConsume != null ? !realConsume.equals(that.realConsume) : that.realConsume != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = checkoutId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (realConsume != null ? realConsume.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "checkin_id", referencedColumnName = "checkin_id")
    public CheckInEntity getCheckin() {
        return checkin;
    }

    public void setCheckin(CheckInEntity checkin) {
        this.checkin = checkin;
    }

    @OneToOne(mappedBy = "checkout")
    public FinancialOrderEntity getFinancialOrder() {
        return financialOrder;
    }

    public void setFinancialOrder(FinancialOrderEntity financialOrder) {
        this.financialOrder = financialOrder;
    }
}
