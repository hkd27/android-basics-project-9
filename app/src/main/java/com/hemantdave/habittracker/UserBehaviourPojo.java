package com.hemantdave.habittracker;

/**
 * Created by INDIA on 6/28/2016.
 */
public class UserBehaviourPojo {
    int id;
    String timeOfAction;
    String Action;
    public UserBehaviourPojo(){

    }
    public UserBehaviourPojo( String timeOfAction, String action) {

        this.timeOfAction = timeOfAction;
        Action = action;
    }
    public UserBehaviourPojo(int id,String timeOfAction, String Action){
        this.id=id;
        this.timeOfAction=timeOfAction;
        this.Action=Action;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeOfAction() {
        return timeOfAction;
    }

    public void setTimeOfAction(String timeOfAction) {
        this.timeOfAction = timeOfAction;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}


