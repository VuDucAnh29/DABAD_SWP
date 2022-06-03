/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author Utech
 */
public class UserDTO {

    private String userID;
    private String fullName;
    private String roleID;
    private String password;
    private String Email;
    private String Avatar;
    private String CreateDate;
    private String UpdateDate;
    private double Balance;

    public UserDTO(String userID, String fullName, String roleID, String password, String Email, String Avatar, String CreateDate, String UpdateDate, double Balance) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.Email = Email;
        this.Avatar = Avatar;
        this.CreateDate = CreateDate;
        this.UpdateDate = UpdateDate;
        this.Balance = Balance;
    }

    public UserDTO(String fullName, String roleID, String password, String Email, String Avatar, String CreateDate, String UpdateDate, double Balance) {
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.Email = Email;
        this.Avatar = Avatar;
        this.CreateDate = CreateDate;
        this.UpdateDate = UpdateDate;
        this.Balance = Balance;
    }

    public UserDTO() {
        this.userID = "";
        this.fullName = "";
        this.roleID = "";
        this.password = "";
        this.Email = "";
        this.Avatar = "";
        this.CreateDate = "";
        this.UpdateDate = "";
        this.Balance = 0;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }

}
