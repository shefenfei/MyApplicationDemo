package com.fenfei.myapplicationdemo.studemo.day07;


import java.util.Collections;
import java.util.List;

/**
 * Created by shefenfei on 2018/1/9.
 */

public class Moive {

    static Builder newBuilder() {
        return new Builder();
    }

    private Moive(String title, String date) {
        List<Moive> arrayList = Collections.emptyList();
    }

    static class Builder {
        String title;
        String date;

        Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        Builder withDate(String date) {
            this.date = date;
            return this;
        }

        Moive build() {
            return new Moive(title , date);
        }
    }


}
