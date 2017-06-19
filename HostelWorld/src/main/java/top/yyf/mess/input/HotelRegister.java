package top.yyf.mess.input;

/**
 * Created by Daniel on 2017/3/2.
 */

/**
 * 酒店注册信息
 */
public class HotelRegister {
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String confirmPass;
    /**
     * 酒店描述
     */
    private String description;
    /**
     * 酒店名字
     */
    private String name;
    /**
     * 酒店地址
     */
    private String address;

    private String address_city;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }
}
