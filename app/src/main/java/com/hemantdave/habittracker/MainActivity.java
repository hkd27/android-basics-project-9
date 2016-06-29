package com.hemantdave.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHandler db =new DbHandler(MainActivity.this);

        // Inserting User Activites..
        Log.d("Insert: ", "Inserting ..");
        String time=getTime();
        db.addActivity(new UserBehaviourPojo(time,"uday calling home"));
        db.addActivity(new UserBehaviourPojo(time,"Jhon is eating"));
        db.addActivity(new UserBehaviourPojo(time,"Tommy is cheking Fb"));
        db.addActivity(new UserBehaviourPojo(time,"Karthik is Cycling"));
       //Updating  User Activites @ id=2 ..
        UserBehaviourPojo updateobj = new UserBehaviourPojo(2, time,"Jhon is done cooking");
        db.updateUserActivity(updateobj);


       // Reading all User Activites..
        Log.d("Reading: ", "Reading all User Activites..");
        List<UserBehaviourPojo> contacts = db.getUserActivites();

        for (UserBehaviourPojo cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getAction() + " ,Phone: " + cn.getAction();
            // Writing User Activites.. to log
            Log.d("Name: ", log);
        }
        db.deleteAllEntries();
        db.deleteDatabase();
    }
    public String getTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
       return formattedDate;
    }

}
