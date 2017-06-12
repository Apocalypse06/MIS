package top.yyf.mess.input;

import java.util.List;

/**
 * Created by Daniel on 2017/3/16.
 */
public class CheckIn {
    /**
     * 支付类型
     */
    private String payType;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 预约id
     */
    private Integer reservationId;
    /**
     * 房间id
     */
    private Integer roomId;
    /**
     * 入住人
     */
    private List<Person> persons;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
