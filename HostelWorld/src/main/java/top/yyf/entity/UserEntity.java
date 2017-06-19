package top.yyf.entity;

import javax.persistence.*;

/**
 * Created by Daniel on 2017/2/27.
 */
@Entity
@Table(name = "user", schema = "hotel_manage_system", catalog = "")
public class UserEntity {
    private String email;
    private String password;
    private String userType;
    private String bankNum;
    private MembershipEntity merbership;
    private String name;
    /*新指标*/
    private String age;
    private String city;


    @Id
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userType", nullable = true)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "bankNum", nullable = true, length = 255)
    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null)
            return false;
        if (bankNum != null ? !bankNum.equals(that.bankNum) : that.bankNum != null) return false;
        if (merbership != null ? !merbership.equals(that.merbership) : that.merbership != null)
            return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (bankNum != null ? bankNum.hashCode() : 0);
        result = 31 * result + (merbership != null ? merbership.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    @OneToOne
    @JoinColumn(name = "merbership_id", referencedColumnName = "merbership_id")
    public MembershipEntity getMerbership() {
        return merbership;
    }

    public void setMerbership(MembershipEntity merbership) {
        this.merbership = merbership;
    }

    @Basic
    @Column(name = "age", nullable = false, length = 255)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
