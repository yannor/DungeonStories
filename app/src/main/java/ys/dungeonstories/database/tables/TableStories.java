package ys.dungeonstories.database.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ys.dungeonstories.database.DatabaseHandler;
import ys.dungeonstories.domain.Story;

/**
 * Created by Yannick on 16/05/2017.
 */

public class TableStories {

    private DatabaseHandler handler;

    //region Constants
    public static final String TABLE_STORIES= "STORIES";

    public static final String KEY_STORY_ID = "ID";
    public static final String KEY_STORY_NAAM = "NAAM";
    public static final String KEY_STORY_STORY = "STORY";
    public static final String KEY_STORY_UNLOCKED = "UNLOCKED";
    public static final String KEY_STORY_PASSWORD = "PASSWORD";
    //endregion


    public TableStories(DatabaseHandler handler) {
        this.handler = handler;
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STORIES = "CREATE TABLE " + TABLE_STORIES + "("
                + KEY_STORY_ID + " INTEGER PRIMARY KEY NOT NULL, " + KEY_STORY_NAAM + " TEXT NOT NULL, " +
                KEY_STORY_STORY + " TEXT, " + KEY_STORY_UNLOCKED  + " INTEGER, " + KEY_STORY_PASSWORD + " TEXT)";

        db.execSQL(CREATE_TABLE_STORIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORIES);
        onCreate(db);
    }

    public List<Story> getAllStories() {
        List<Story> listStories = new ArrayList<Story>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORIES + " WHERE " + KEY_STORY_UNLOCKED + " = 0";

        SQLiteDatabase db = this.handler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story(cursor.getString(1), cursor.getString(2));

                listStories.add(story);
            } while (cursor.moveToNext());
        }
        return listStories;
    }

    public List<Story> getAllStoriesPassword(String password) {
        List<Story> listStories = new ArrayList<Story>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STORIES + " WHERE " + KEY_STORY_PASSWORD + " = " + password;

        SQLiteDatabase db = this.handler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story(cursor.getString(1), cursor.getString(2));
                setKeyStoryUnlocked(story);

                listStories.add(story);
            } while (cursor.moveToNext());
        }
        return listStories;
    }

    public void setKeyStoryUnlocked(Story story)
    {
        String selectQuery = "UPDATE " + TABLE_STORIES + " SET " + KEY_STORY_UNLOCKED + " = 1 WHERE " + KEY_STORY_ID + " = " + story.getId();

        SQLiteDatabase db = this.handler.getWritableDatabase();
        db.execSQL(selectQuery);
        db.close();
    }

    public Story getStory(int id) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STORIES, new String[]{KEY_STORY_ID,
                        KEY_STORY_NAAM, KEY_STORY_STORY}, KEY_STORY_ID + "= " + id,
                new String[]{String.valueOf(id)}, null, null, null, null);
        Story story;
        if (cursor != null) {
            cursor.moveToFirst();
            story = new Story(cursor.getString(1), cursor.getString(2));
        } else {
            story = null;
        }
        return story;
    }



    public void addStory(Story story) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STORY_ID, story.getId());
        values.put(KEY_STORY_NAAM, story.getsTitle());
        values.put(KEY_STORY_STORY, story.getsStory());
        values.put(KEY_STORY_PASSWORD, story.getsPassword());
        values.put(KEY_STORY_UNLOCKED, story.getbUnlocked());


        // Inserting Row
        db.insert(TABLE_STORIES, null, values);

        db.close(); // Closing database connection
    }



    public void deleteStory(Story story) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        db.execSQL("delete from " + TABLE_STORIES + " WHERE " + KEY_STORY_ID + " = " + story.getId());

        db.close();
    }
}
