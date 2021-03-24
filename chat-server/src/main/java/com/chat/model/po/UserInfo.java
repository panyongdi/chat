package com.chat.model.po;

import java.util.List;

public class UserInfo {

    private String userId;
    private String username;
    private String password;
    private String avatarUrl;
    private String token;
    private List<FriendInfo> friendList;
    private List<GroupInfo> groupList;

    public UserInfo() {
        super();
    }

    public UserInfo(String userId, String username, String password, String avatarUrl, String token) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<FriendInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendInfo> friendList) {
        this.friendList = friendList;
    }

    public List<GroupInfo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupInfo> groupList) {
        this.groupList = groupList;
    }


}
