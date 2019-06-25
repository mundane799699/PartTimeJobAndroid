package com.example.model;

import java.io.Serializable;

/**
 * Habit
 *
 * @author fangyuan
 * @date 2019-06-24
 */
public class Habit implements Serializable {
    public int _id;
    public String name;
    public String words;
    public int status;
    public int cardtimes;
    // todayStatus: 0表示今天未打卡, 1表示今天已打卡
    public int todayStatus;
}
