package com.example.scottsmith.anactuallygoodhangboardrepeater;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by scott.smith on 8/12/17.
 */

public class Workout implements Parcelable{
    private String workoutName;

    private int numSets;
    private int numReps;
    private int numSetsCopy;
    private int numRepsCopy;


    private int onTime;
    private int offTime;
    private int restTime;
    private int warnTime;

    private int state; //0 = onTime, 1 = offTime, 2 = restTime

    private final int ON = 0;
    private final int OFF = 1;
    private final int REST = 2;


    public Workout (int numSets, int numReps, int onTime, int offTime,
                    int restTime, int warnTime, String workoutName) {
        this.numSets = numSets;
        this.numSetsCopy = numSets;
        this.numReps = numReps;
        this.numRepsCopy = numReps;

        this.onTime = onTime;
        this.offTime = offTime;
        this.restTime = restTime;
        this.warnTime = warnTime;

        this.workoutName = new String();
        this.workoutName = workoutName;

        this.state = ON;

    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutName() {
        return this.workoutName;
    }

    public int getOnTime() {
        return onTime;
    }

    public int getOffTime() {
        return offTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public int getWarnTime() {
        return warnTime;
    }

    public int getNumSets() {
        return numSets;
    }

    public int getNumReps() {
        return this.numReps;
    }

    public void decNumSets() {
        if (this.numSets != 0) {
            this.numSets --;
        }
    }

    public void decNumReps() {
        if (this.numReps != 0) {
            this.numReps --;
        }
    }

    public int getTime() {
        if (state == ON) {
            return onTime;
        } else if (state == OFF) {
            return offTime;
        } else { //state == REST
            return restTime;
        }
    }

    public int getState () {
        return state;
    }


    public int updateState() { // also updates state
        if (state == REST) {
            state = ON;
            numReps = numRepsCopy;
        } else if (state == OFF) {
            state = ON;
            numReps --;
        } else if (state == ON) {
            if (numReps == 0) {
                numSets --;
                state = REST;
            } else {
                state = OFF;
            }
        }
        return getTime();
    }

    public boolean isFinished(){
        if (this.numReps == 0 && this.numSets == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void resetWorkout(){
        this.numReps = numRepsCopy;
        this.numSets = numSetsCopy;
    }

    public void printWorkout(){
        Log.d("PRINT", "numSets = "+numSets);
        Log.d("PRINT", "numReps = "+numReps);
        Log.d("PRINT", "numRepsCopy = "+numRepsCopy);
        Log.d("PRINT", "onTime = "+onTime);
        Log.d("PRINT", "offTime = "+offTime);
        Log.d("PRINT", "restTime = "+restTime);
        Log.d("PRINT", "warnTime = "+ warnTime);
        Log.d("PRINT", "workoutName = "+workoutName);

    }

    //Constructor of object when we have a parcel
    public Workout(Parcel in){
        String[] contents = new String[7];
        in.readStringArray(contents);
        this.numSets = Integer.parseInt(contents[0]);
        this.numSetsCopy = this.numSets;
        this.numReps = Integer.parseInt(contents[1]);
        this.numRepsCopy = this.numReps;
        this.onTime = Integer.parseInt(contents[2]);
        this.offTime = Integer.parseInt(contents[3]);
        this.restTime = Integer.parseInt(contents[4]);
        this.warnTime = Integer.parseInt(contents[5]);
        this.workoutName = contents[6];

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] contents = new String[7];
        contents[0] = String.valueOf(this.numSets);
        contents[1] = String.valueOf(this.numReps);
        contents[2] = String.valueOf(this.onTime);
        contents[3] = String.valueOf(this.offTime);
        contents[4] = String.valueOf(this.restTime);
        contents[5] = String.valueOf(this.warnTime);
        contents[6] = this.workoutName;

        dest.writeStringArray(contents);

    }

    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>() {

        @Override
        public Workout createFromParcel(Parcel source) {
            return new Workout(source);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

}
