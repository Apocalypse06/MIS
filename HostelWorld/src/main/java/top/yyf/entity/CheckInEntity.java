package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "check_in", schema = "hotel_manage_system")
public class CheckInEntity {
    private int checkinId;
    private String time;
    private String payType;
    private Byte checkinNum;
    private Byte isCheckedOut;
    private ActualRoomEntity roomNum;
    private CheckOutEntity checkOut;
    private MembershipEntity membership;
    private List<CheckinListEntity> checkInLists;

    @Id
    @GeneratedValue
    @Column(name = "checkin_id", nullable = false)
    public int getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(int checkinId) {
        this.checkinId = checkinId;
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
    @Column(name = "pay_type", nullable = true)
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Column(name = "is_checked_out")
    public Byte getIsCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(Byte isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

    @Basic
    @Column(name = "checkin_num", nullable = true)
    public Byte getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(Byte checkinNum) {
        this.checkinNum = checkinNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckInEntity that = (CheckInEntity) o;

        if (checkinId != that.checkinId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (checkinNum != null ? !checkinNum.equals(that.checkinNum) : that.checkinNum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = checkinId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (checkinNum != null ? checkinNum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    public ActualRoomEntity getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(ActualRoomEntity roomNum) {
        this.roomNum = roomNum;
    }

    @OneToOne(mappedBy = "checkin")
    public CheckOutEntity getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(CheckOutEntity checkOut) {
        this.checkOut = checkOut;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checkin")
    public List<CheckinListEntity> getCheckInLists() {
        return checkInLists;
    }

    public void setCheckInLists(List<CheckinListEntity> checkInLists) {
        this.checkInLists = checkInLists;
    }

    @ManyToOne
    @JoinColumn(name = "membership_id", referencedColumnName = "merbership_id")
    public MembershipEntity getMembership() {
        return membership;
    }

    public void setMembership(MembershipEntity membership) {
        this.membership = membership;
    }
}
