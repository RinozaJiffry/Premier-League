package sportsLeague;

import java.io.Serializable;

public abstract class SportsClub implements Serializable
{

    private String nameOfClub;
    private String locationOfClub;

    public SportsClub (String nameOfClub, String locationOfClub)
    {
        this.nameOfClub = nameOfClub;
        this.locationOfClub = locationOfClub;
    }

    public String getNameOfClub() {
        return nameOfClub;
    }

    public void setNameOfClub(String nameOfClub) {
        this.nameOfClub = nameOfClub;
    }

    public String getLocationOfClub() {
        return locationOfClub;
    }

    public void setLocationOfClub(String locationOfClub) {
        this.locationOfClub = this.locationOfClub;
    }

}
