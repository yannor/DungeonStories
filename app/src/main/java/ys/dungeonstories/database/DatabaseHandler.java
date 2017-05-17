package ys.dungeonstories.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import ys.dungeonstories.database.tables.TableStories;
import ys.dungeonstories.domain.Story;

/**
 * Created by Yannick on 16/05/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    InitialStories initStories;

    // DatabaseHandler Version
    public static final int DATABASE_VERSION = 1;

    // DatabaseHandler Name
    public static final String DATABASE_NAME = "DungeonStories";


    private TableStories tableStories;


    public DatabaseHandler(Context context) {
        super(context, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);
        initStories = new InitialStories();
        tableStories = new TableStories(this);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        tableStories.onCreate(db, initStories.getStories());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        tableStories.onUpgrade(db, oldVersion, newVersion, initStories.getStories());

    }

    public void empty() {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 0, 0);
    }


//region Stories handling

    public List<Story> getAllStories() {
        return tableStories.getAllStories();
    }

    public List<Story> getAllStoriesPassword(String password) {
        return tableStories.getAllStoriesPassword(password);
    }

    public Story getStory(int id) {
        return tableStories.getStory(id);
    }


    public void addStory(Story story) {
        tableStories.addStory(story);
    }


    public void deleteStory(Story story) {
        tableStories.deleteStory(story);
    }
    //endregion


}
