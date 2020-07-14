package com.example.management.entity;

public class Club {
    private Long id;
    private String clubName;
    private String logo;     //url
    private String intro;
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", logo='" + logo + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
