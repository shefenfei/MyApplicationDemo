package com.fenfei.myapplicationdemo.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shefenfei on 2017/8/28.
 */

public class ResultResp {

    List<String> teachingTeacher;

    public List<String> getTeachingTeacher() {
        return teachingTeacher == null ? new ArrayList<>() : teachingTeacher;
    }

    public void setTeachingTeacher(List<String> teachingTeacher) {
        this.teachingTeacher = teachingTeacher;
    }
}
