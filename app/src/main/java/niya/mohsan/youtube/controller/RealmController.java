package niya.mohsan.youtube.controller;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import niya.mohsan.youtube.model.Video;

/**
 * Created by mohsan on 27/07/16.
 */
public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Context context) {
        realm = Realm.getInstance(
                new RealmConfiguration.Builder(context)
                        .name("youtube.realm")
                        .build()
        );
    }

    public static RealmController with(Context context) {

        if (instance == null) {
            instance = new RealmController(context);
        }
        return instance;
    }


    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }



    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.delete(Video.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<Video> getVideoHistory() {

        return realm.where(Video.class).findAll();
    }

    //query a single item with the given id
    public Video getVideo(String id) {

        return realm.where(Video.class).equalTo("id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean hasVideo() {

        return !realm.where(Video.class).findAll().isEmpty();
    }

    //query example
    public RealmResults<Video> queryVideo() {

        return realm.where(Video.class)
                .contains("author", "Author")
                .or()
                .contains("title", "Realm")
                .findAll();

    }
}
