package edu.school21.cinema.models;

import java.io.Serializable;

public class Image implements Serializable {
    private long id;
    private long size;
    private String mime;
    private String name;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public String getSizeString() {
        if (size < 1024) {
            return String.format("%d Bytes", size);
        } else if (size < 1024 * 1024) {
            return String.format("%d KB", size / 1024);
        } else {
            return String.format("%d MB", size / (1024 * 1024));
        }
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
