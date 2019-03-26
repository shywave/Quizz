package com.example.quizz.data;

public class Question {
    //private int index;
    private int entry;
    private boolean answer;

    public Question(int entry, boolean answer) {
        //this.index = index;
        this.entry = entry;
        this.answer = answer;
    }

    /*
    public int getIndex(){ return index; }
    public void setIndex(int index){ this.index = index; }
    */

    public int getEntry() {
        return entry;
    }
    public void setEntry(int entry) {
        this.entry = entry;
    }

    public boolean getAnswer() {
        return answer;
    }
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
