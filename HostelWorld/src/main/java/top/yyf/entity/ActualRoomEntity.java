package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "actual_room", schema = "hotel_manage_system", catalog = "")
public class ActualRoomEntity {
    private Integer roomId;
    private String roomNum;
    private Double price;
    private Byte isempty;
    private HotelEntity hotelId;
    private RoomPlanEntity rpId;
    private List<CheckInEntity> checkIns;

    @Id
    @GeneratedValue
    @Column(name = "room_id", nullable = false)
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Column(name = "room_num", nullable = false, length = 255)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "isempty", nullable = true)
    public Byte getIsempty() {
        return isempty;
    }

    public void setIsempty(Byte isempty) {
        this.isempty = isempty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActualRoomEntity that = (ActualRoomEntity) o;

        if (roomNum != null ? !roomNum.equals(that.roomNum) : that.roomNum != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (isempty != null ? !isempty.equals(that.isempty) : that.isempty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomNum != null ? roomNum.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isempty != null ? isempty.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    public HotelEntity getHotelId() {
        return hotelId;
    }

    public void setHotelId(HotelEntity hotelId) {
        this.hotelId = hotelId;
    }

    @ManyToOne
    @JoinColumn(name = "rp_id", referencedColumnName = "rp_id")
    public RoomPlanEntity getRpId() {
        return rpId;
    }

    public void setRpId(RoomPlanEntity rpId) {
        this.rpId = rpId;
    }

    @OneToMany(mappedBy = "roomNum")
    public List<CheckInEntity> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckInEntity> checkIns) {
        this.checkIns = checkIns;
    }
}
