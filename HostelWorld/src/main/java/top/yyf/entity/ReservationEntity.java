package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
//@Where(clause = "is_delete = 0")
//@Loader(namedQuery = "findById")
//@NamedQuery(name = "findById", query = "" +
//        "select r from ReservationEntity r where r.resId = ? AND r.isDelete = 0")
@Table(name = "reservation", schema = "hotel_manage_system")
public class ReservationEntity {
    private int resId;
    private String fromdate;
    private String todate;
    private Byte isDelete;
    private String revervationTime;
    private UserEntity user;
    private HotelEntity hotel;
    private RoomPlanEntity roomPlan;
    private ActualRoomEntity actualRoom;
    private String state;
    private String name;
    private String phoneNum;

    @Id
    @GeneratedValue
    @Column(name = "resId", nullable = false, length = 255)
    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "fromdate", nullable = true)
    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    @Basic
    @Column(name = "todate", nullable = true)
    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    @Basic
    @Column(name = "is_delete", nullable = true)
    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column(name = "revervation_time", nullable = true, length = 255)
    public String getRevervationTime() {
        return revervationTime;
    }

    public void setRevervationTime(String revervationTime) {
        this.revervationTime = revervationTime;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_num")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (user != null ? !user.equals(that.user) : that.user != null)
            return false;
        if (fromdate != null ? !fromdate.equals(that.fromdate) : that.fromdate != null)
            return false;
        if (todate != null ? !todate.equals(that.todate) : that.todate != null) return false;
        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null)
            return false;
        if (revervationTime != null ? !revervationTime.equals(that.revervationTime) : that.revervationTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (fromdate != null ? fromdate.hashCode() : 0);
        result = 31 * result + (todate != null ? todate.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (revervationTime != null ? revervationTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    @ManyToOne
    @JoinColumn(name = "rp_id", referencedColumnName = "rp_id")
    public RoomPlanEntity getRoomPlan() {
        return roomPlan;
    }

    public void setRoomPlan(RoomPlanEntity roomPlan) {
        this.roomPlan = roomPlan;
    }


    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity username) {
        this.user = username;
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    public ActualRoomEntity getActualRoom() {
        return actualRoom;
    }

    public void setActualRoom(ActualRoomEntity actualRoom) {
        this.actualRoom = actualRoom;
    }
}
