package com.example.codeutsava;

public class RatingHelper {
  public String quality;
    public String sanitation;

    public RatingHelper(String quality ,String sanitation) {
        this.quality = quality;
        this.sanitation = sanitation;
    }

    public String getSanitation() {
        return sanitation;
    }

    public void setSanitation(String sanitation) {
        this.sanitation = sanitation;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
