package com.fenfei.myapplicationdemo.beans;

import java.io.Serializable;

/**
 * Created by shefenfei on 2017/7/21.
 */

public class Student implements Serializable {

    /*
    "student": null,
            "baseBodyConstitutionItem": {
        "itemName": "仰卧起坐",
                "icon": "http://oq6t96i6t.bkt.clouddn.com/image/body/SITUP.png"
    },
            "point": 82.2,
            "score": 79,
            "year": "2017",
            "level": 2
            */

    private double point;
    private double score;
    private String year;
    private int level;
    private String isSuccess;
    private String student;
    private BaseItem baseBodyConstitutionItem;


    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public BaseItem getBaseBodyConstitutionItem() {
        return baseBodyConstitutionItem;
    }

    public void setBaseBodyConstitutionItem(BaseItem baseBodyConstitutionItem) {
        this.baseBodyConstitutionItem = baseBodyConstitutionItem;
    }

    @Override
    public String toString() {
        return "Student{" +
                "point=" + point +
                ", score=" + score +
                ", year='" + year + '\'' +
                ", level=" + level +
                ", isSuccess=" + isSuccess +
                ", student='" + student + '\'' +
                ", baseBodyConstitutionItem=" + baseBodyConstitutionItem +
                '}';
    }

    class BaseItem implements Serializable {
        private String itemName;
        private String icon;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "BaseItem{" +
                    "itemName='" + itemName + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }
}
