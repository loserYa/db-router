package io.github.loserya.entity;


/**
 * 数据库信息
 *
 * @author loser
 */
public class DbInfo {

    /**
     * url
     */
    private String url;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
