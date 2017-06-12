package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "manager_pay_out", schema = "hotel_manage_system", catalog = "")
public class ManagerPayOutEntity {
    private int mpoId;
    private Double money;
    private Byte isPaid;
    private FinancialOrderEntity financialOrder;

    @Id
    @GeneratedValue
    @Column(name = "mpo_id", nullable = false)
    public int getMpoId() {
        return mpoId;
    }

    public void setMpoId(int mpoId) {
        this.mpoId = mpoId;
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
    @Column(name = "is_paid", nullable = true)
    public Byte getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Byte isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerPayOutEntity that = (ManagerPayOutEntity) o;

        if (mpoId != that.mpoId) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        if (isPaid != null ? !isPaid.equals(that.isPaid) : that.isPaid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mpoId;
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    public FinancialOrderEntity getFinancialOrder() {
        return financialOrder;
    }

    public void setFinancialOrder(FinancialOrderEntity financialOrder) {
        this.financialOrder = financialOrder;
    }
}
