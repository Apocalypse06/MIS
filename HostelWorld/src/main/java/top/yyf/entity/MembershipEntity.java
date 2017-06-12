package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "membership", schema = "hotel_manage_system")
public class MembershipEntity {
    private String merbershipId;
    private String state;
    private Integer totalscore;
    private Integer availablescore;
    private String lastPayTime;
    private String createTime;
    private List<CheckInEntity> checkins;
    private LevelInfoEntity level;
    private UserEntity user;

    @Id
    @Column(name = "merbership_id", nullable = false, length = 7)
    public String getMerbershipId() {
        return merbershipId;
    }

    public void setMerbershipId(String merbershipId) {
        this.merbershipId = merbershipId;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "createTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "totalscore", nullable = true)
    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    @Basic
    @Column(name = "availablescore", nullable = true)
    public Integer getAvailablescore() {
        return availablescore;
    }

    public void setAvailablescore(Integer availablescore) {
        this.availablescore = availablescore;
    }

    @Basic
    @Column(name = "last_pay_time", nullable = true, length = 255)
    public String getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(String lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembershipEntity that = (MembershipEntity) o;

        if (merbershipId != null ? !merbershipId.equals(that.merbershipId) : that.merbershipId != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (totalscore != null ? !totalscore.equals(that.totalscore) : that.totalscore != null)
            return false;
        if (availablescore != null ? !availablescore.equals(that.availablescore) : that.availablescore != null)
            return false;
        if (lastPayTime != null ? !lastPayTime.equals(that.lastPayTime) : that.lastPayTime != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
            return false;
        if (checkins != null ? !checkins.equals(that.checkins) : that.checkins != null)
            return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = merbershipId != null ? merbershipId.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (totalscore != null ? totalscore.hashCode() : 0);
        result = 31 * result + (availablescore != null ? availablescore.hashCode() : 0);
        result = 31 * result + (lastPayTime != null ? lastPayTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (checkins != null ? checkins.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "membership")
    public List<CheckInEntity> getCheckins() {
        return checkins;
    }

    public void setCheckins(List<CheckInEntity> checkins) {
        this.checkins = checkins;
    }

    @ManyToOne
    @JoinColumn(name = "level", referencedColumnName = "level")
    public LevelInfoEntity getLevel() {
        return level;
    }

    public void setLevel(LevelInfoEntity level) {
        this.level = level;
    }

    @OneToOne(mappedBy = "merbership")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
