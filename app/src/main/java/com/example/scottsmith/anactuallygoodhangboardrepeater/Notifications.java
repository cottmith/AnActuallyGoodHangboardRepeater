package com.example.scottsmith.anactuallygoodhangboardrepeater;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;

/**
 * Created by scott.smith on 15/12/17.
 */

public class Notifications {

    private static int on;
    private static int off;
    private static int warn;
    private static int rest;

    private static SoundPool soundPool;
    private static HashMap soundPoolMap;

    public Notifications(Context context) {
        initSound(context);
    }

    public static void initSound(Context context) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        on = soundPool.load(context, R.raw.on, 1);
        off = soundPool.load(context, R.raw.off, 2);
        warn = soundPool.load(context, R.raw.warning, 3);
        rest = soundPool.load(context, R.raw.rest, 4);

    }

    public void playOn() {
        soundPool.play(on, 1, 1, 1, 0, 1f);
    }

    public void playOff() {
        soundPool.play(off, 1, 1, 1, 0, 1f);
    }

    public void playWarn() {
        soundPool.play(warn, 1, 1, 1, 0, 1f);
    }

    public void playRest() {
        soundPool.play(rest, 1, 1, 1, 0, 1f);
    }
}
