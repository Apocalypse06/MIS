package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "level_info", schema = "hotel_manage_system", catalog = "")
public class LevelInfoEntity {
    private byte level;
    private Integer demandScore;
    private Double discount;
    private List<MembershipEntity> members;

    @Id
    @Column(name = "level", nullable = false)
    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    @Basic
    @Column(name = "demand_score", nullable = true)
    public Integer getDemandScore() {
        return demandScore;
    }

    public void setDemandScore(Integer demandScore) {
        this.demandScore = demandScore;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 2)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LevelInfoEntity that = (LevelInfoEntity) o;

        if (level != that.level) return false;
        if (demandScore != null ? !demandScore.equals(that.demandScore) : that.demandScore != null)
            return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) level;
        result = 31 * result + (demandScore != null ? demandScore.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "level")
    public List<MembershipEntity> getMembers() {
        return members;
    }

    public void setMembers(List<MembershipEntity> members) {
        this.members = members;
    }
}
