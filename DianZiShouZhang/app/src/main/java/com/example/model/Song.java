package com.example.model;

import java.io.Serializable;

/**
 * Song
 *
 * @author fangyuan
 * @date 2019-06-23
 */
public class Song implements Serializable {
    
    public String song;//歌曲名
    public String singer;//歌手
    public long size;//歌曲所占空间大小
    public int duration;//歌曲时间长度
    public String path;//歌曲地址
    
    
}
