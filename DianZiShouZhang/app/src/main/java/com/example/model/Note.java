package com.example.model;

import android.text.TextUtils;

public class Note {
    public int _id;
    public String flag;
    public String songName;
    public String songSinger;
    public String songPath;
    
    public boolean hasSong() {
        return !TextUtils.isEmpty(songPath);
    }
    
    public Note() {
    }
    
    public Note(int _id, String flag, String songName, String songSinger, String songPath) {
        this._id = _id;
        this.flag = flag;
        this.songName = songName;
        this.songSinger = songSinger;
        this.songPath = songPath;
    }
    
    public Note(int _id, String flag) {
        this._id = _id;
        this.flag = flag;
    }
    
}


