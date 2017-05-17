package ys.dungeonstories.database;

import java.util.ArrayList;
import java.util.List;

import ys.dungeonstories.domain.Story;

/**
 * Created by Yannick on 17/05/2017.
 */

public class InitialStories {

    List<Story> stories;
    Story s;
    public InitialStories() {
        this.stories = new ArrayList<>();
        fillListStories();
    }

    public List<Story> getStories() {
        return stories;
    }

    public void fillListStories()
    {
        stories.add(new Story("Alvar", "Heilige God Alvar", "Alv", false));
        stories.add(new Story("Start", "You start in a little town called murgandoy"));

    }
}
