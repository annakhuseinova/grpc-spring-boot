package org.annakhuseinova.aggregator.dto;

public class UserGenre {

    private String loginId;
    private String genre;

    public UserGenre(){

    }

    public UserGenre(String loginId, String genre) {
        this.loginId = loginId;
        this.genre = genre;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
