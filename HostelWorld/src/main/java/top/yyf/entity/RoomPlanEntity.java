package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "room_plan", schema = "hotel_manage_system")
public class RoomPlanEntity {
    private Integer rpId;
    private String roomType;
    private String fromdate;
    private String todate;
    private Double price;
    private byte accommodation;
    private String description;
    private List<ActualRoomEntity> rooms;
    private List<ReservationEntity> reservations;
    private HotelEntity hotel;

    @Id
    @GeneratedValue
    @Column(name = "rp_id", nullable = false)
    public Integer getRpId() {
        return rpId;
    }

    public void setRpId(Integer rpId) {
        this.rpId = rpId;
    }

    @Column(name = "accommodation")
    public byte getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(byte accomondations) {
        this.accommodation = accomondations;
    }

    @Basic
    @Column(name = "room_type", nullable = true)
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
    @Column(name = "price", nullable = true, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomPlanEntity that = (RoomPlanEntity) o;

        if (rpId != that.rpId) return false;
        if (accommodation != that.accommodation) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null)
            return false;
        if (fromdate != null ? !fromdate.equals(that.fromdate) : that.fromdate != null)
            return false;
        if (todate != null ? !todate.equals(that.todate) : that.todate != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (rooms != null ? !rooms.equals(that.rooms) : that.rooms != null) return false;
        if (reservations != null ? !reservations.equals(that.reservations) : that.reservations != null)
            return false;
        return hotel != null ? hotel.equals(that.hotel) : that.hotel == null;

    }

    @Override
    public int hashCode() {
        int result = rpId;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (fromdate != null ? fromdate.hashCode() : 0);
        result = 31 * result + (todate != null ? todate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) accommodation;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (reservations != null ? reservations.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rpId")
    public List<ActualRoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<ActualRoomEntity> rooms) {
        this.rooms = rooms;
    }

    @OneToMany(mappedBy = "roomPlan")
    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }
}
