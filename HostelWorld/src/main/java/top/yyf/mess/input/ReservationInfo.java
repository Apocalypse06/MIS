package top.yyf.mess.input;

/**
 * Created by Daniel on 2017/3/13.
 * 预约信息
 */
public class ReservationInfo {
    /**
     * 开始日期
     */
    private String fromDate;
    /**
     * 结束日期
     */
    private String toDate;
    /**
     * 房间类型
     */
    private String roomType;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 酒店id
     */
    private String hotelId;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
