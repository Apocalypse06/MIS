package top.yyf.mess.input;

/**
 * Created by Daniel on 2017/3/7.
 * 房间查询
 */
public class RoomQuery {
    /**
     * 房间类型
     */
    private String roomType;
    /**
     * 房间状态
     */
    private Integer roomState;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomState() {
        return roomState;
    }

    public void setRoomState(Integer roomState) {
        this.roomState = roomState;
    }
}
