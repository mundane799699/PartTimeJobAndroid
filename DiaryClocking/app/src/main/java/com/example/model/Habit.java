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
    public String lastCardDate;
}
