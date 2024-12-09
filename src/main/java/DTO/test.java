package DTO;

public class test {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String renewPassword;

    public String getRenewPassword() {
      return renewPassword;
}

public void setRenewPassword(String renewPassword) {
      this.renewPassword = renewPassword;
}

public test(String username, String oldPassword, String newPassword, String renewPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.renewPassword = renewPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
