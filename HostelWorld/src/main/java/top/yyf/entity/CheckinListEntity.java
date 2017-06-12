package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "checkin_list", schema = "hotel_manage_system", catalog = "")
public class CheckinListEntity {
    private int checkinId;
    private String name;
    private String customId;
    private CheckInEntity checkin;

    @Id
    @Column(name = "checkin_list_id")
    @GeneratedValue
    public int getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(int checkinId) {
        this.checkinId = checkinId;
    }

    @Column(name = "custom_id")
    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckinListEntity that = (CheckinListEntity) o;

        if (checkinId != that.checkinId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = checkinId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "checkin_id", referencedColumnName = "checkin_id", nullable = false)
    public CheckInEntity getCheckin() {
        return checkin;
    }

    public void setCheckin(CheckInEntity checkin) {
        this.checkin = checkin;
    }


}
