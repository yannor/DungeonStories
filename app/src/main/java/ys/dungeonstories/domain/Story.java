package ys.dungeonstories.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yannick on 16/05/2017.
 */

public class Story {
    private AtomicInteger count = new AtomicInteger(0);

    private int id;
    private String sTitle;
    private String sStory;
    private String sPassword;
    private Boolean bUnlocked;

    public Story(String title, String story) {
        id = count.incrementAndGet();
        this.sTitle = title;
        this.sStory = story;
        sPassword = "";
        bUnlocked = true;
    }

    public Story(String sTitle, String sStory, String sPassword, Boolean bUnlocked) {
        id = count.incrementAndGet();
        this.sTitle = sTitle;
        this.sStory = sStory;
        this.sPassword = sPassword;
        this.bUnlocked = bUnlocked;
    }

    public int getId() {
        return id;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsStory() {
        return sStory;
    }

    public void setsStory(String sStory) {
        this.sStory = sStory;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public Boolean getbUnlocked() {
        return bUnlocked;
    }

    public void setbUnlocked(Boolean bUnlocked) {
        this.bUnlocked = bUnlocked;
    }
}
