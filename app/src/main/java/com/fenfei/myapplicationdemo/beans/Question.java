package com.fenfei.myapplicationdemo.beans;

import java.io.Serializable;

/**
 * Created by shefenfei on 2017/4/5.
 * 问题描述的bean
 */
public class Question implements Serializable {

    /*
    问题类型
    * */
    private int questionType;
    //问题描述
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int answer;
    private String explaination;
    private int ID;
    private int selectedAnser;

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSelectedAnser() {
        return selectedAnser;
    }

    public void setSelectedAnser(int selectedAnser) {
        this.selectedAnser = selectedAnser;
    }
}
