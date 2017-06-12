package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "financial_order", schema = "hotel_manage_system", catalog = "")
public class FinancialOrderEntity {
    private Integer fid;
    private String time;
    private Double money;
    private String payType;
    private CheckOutEntity checkout;
    private HotelEntity hotel;
    private ManagerPayOutEntity managerPayOut;

    @Id
    @GeneratedValue
    @Column(name = "fid", nullable = false)
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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
    @Column(name = "money", nullable = true, precision = 2)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "pay_type", nullable = true)
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinancialOrderEntity that = (FinancialOrderEntity) o;

        if (fid != that.fid) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "checkout_id", referencedColumnName = "checkout_id")
    public CheckOutEntity getCheckout() {
        return checkout;
    }

    public void setCheckout(CheckOutEntity checkout) {
        this.checkout = checkout;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    @OneToOne(mappedBy = "financialOrder")
    public ManagerPayOutEntity getManagerPayOut() {
        return managerPayOut;
    }

    public void setManagerPayOut(ManagerPayOutEntity managerPayOut) {
        this.managerPayOut = managerPayOut;
    }
}
