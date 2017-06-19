package top.yyf.mess.input;

/**
 * Created by Daniel on 2017/2/28.
 * 用户注册Bean信息
 */
public class UserRegister {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /*新指标*/
    private String age;

    private String city;

    /**
     * 确认密码
     */
    private String confirmPass;
    /**
     * 邮箱
     */
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
