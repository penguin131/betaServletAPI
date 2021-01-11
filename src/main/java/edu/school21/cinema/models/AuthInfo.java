package edu.school21.cinema.models;

import edu.school21.cinema.services.DateConvertUtil;

import java.io.Serializable;

public class AuthInfo implements Serializable {
    private long time;
    private User user;
    private String ip;

    public long getTime() {
        return time;
    }

    public String getDateString() {
        return DateConvertUtil.getDate(this.time);
    }

    public String getTimeString() {
        return DateConvertUtil.getTime(this.time);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
