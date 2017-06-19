package top.yyf.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "hotel", schema = "hotel_manage_system", catalog = "")
public class HotelEntity {
    private String hotelId;
    private String password;
    private String name;
    private String address;
    private String description;
    private Byte isPassed;
    private List<ActualRoomEntity> rooms;
    private List<FinancialOrderEntity> financialOrders;
    private List<ReservationEntity> reservations;
    private List<RoomPlanEntity> roomPlans;

    private String address_city;

    @Id
    @Column(name = "hotel_id", nullable = false, length = 7)
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "is_passed", nullable = true)
    public Byte getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Byte isPassed) {
        this.isPassed = isPassed;
    }

    @Basic
    @Column(name = "address_city", nullable = true, length = 255)
    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelEntity that = (HotelEntity) o;

        if (hotelId != null ? !hotelId.equals(that.hotelId) : that.hotelId != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (isPassed != null ? !isPassed.equals(that.isPassed) : that.isPassed != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelId != null ? hotelId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isPassed != null ? isPassed.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hotelId")
    public List<ActualRoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<ActualRoomEntity> rooms) {
        this.rooms = rooms;
    }

    @OneToMany(mappedBy = "hotel")
    public List<FinancialOrderEntity> getFinancialOrders() {
        return financialOrders;
    }

    public void setFinancialOrders(List<FinancialOrderEntity> financialOrders) {
        this.financialOrders = financialOrders;
    }

    @OneToMany(mappedBy = "hotel")
    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    @OneToMany(mappedBy = "hotel")
    public List<RoomPlanEntity> getRoomPlans() {
        return roomPlans;
    }

    public void setRoomPlans(List<RoomPlanEntity> roomPlans) {
        this.roomPlans = roomPlans;
    }
}
