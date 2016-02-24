package greendao;

/**
 * Created by xiao on 2016/2/23.
 */
public class Person {
    private Long id;
    private Integer vipLevel = 1;//vip等级
    private String equipmentId;// 会员手机号码   "equipmentId": "18132165487",
    private String userName = "";// 姓名    "userName": "18132165487",
    private String sex = "";// 性别   "sex": "",
    private String personID = "";// 身份证    "personID": ""
    private String email = "";// 邮箱 "email": "",
    private String userIntegral = "0";// 用户积分 "userIntegral": "30",
    private String role = "";// 登陆账户角色   "role": "general",
    private String deposit = "0";// -- 押金
    private String balance = "0";// --余额
    private String remark = "";//备注
    private String psd;//密码
    private String vitality = "0";//活跃度
    private Integer isVIP = 0;//是否vip，是1，否0

    public Person() {
    }

    public Person(String equipmentId, String psd) {
        this.equipmentId = equipmentId;
        this.psd = psd;
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, Integer vipLevel, String equipmentId, String userName, String sex, String personID, String email, String userIntegral, String role, String deposit, String balance, String remark, String psd, String vitality, Integer isVIP) {
        this.id = id;
        this.vipLevel = vipLevel;
        this.equipmentId = equipmentId;
        this.userName = userName;
        this.sex = sex;
        this.personID = personID;
        this.email = email;
        this.userIntegral = userIntegral;
        this.role = role;
        this.deposit = deposit;
        this.balance = balance;
        this.remark = remark;
        this.psd = psd;
        this.vitality = vitality;
        this.isVIP = isVIP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(String userIntegral) {
        this.userIntegral = userIntegral;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getVitality() {
        return vitality;
    }

    public void setVitality(String vitality) {
        this.vitality = vitality;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getIsVIP() {
        return isVIP;
    }

    public void setIsVIP(Integer isVIP) {
        this.isVIP = isVIP;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", vipLevel=" + vipLevel +
                ", equipmentId='" + equipmentId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", personID='" + personID + '\'' +
                ", email='" + email + '\'' +
                ", userIntegral='" + userIntegral + '\'' +
                ", role='" + role + '\'' +
                ", deposit='" + deposit + '\'' +
                ", balance='" + balance + '\'' +
                ", remark='" + remark + '\'' +
                ", psd='" + psd + '\'' +
                ", vitality='" + vitality + '\'' +
                ", isVIP=" + isVIP +
                '}';
    }
}
