package com.vvavy.visiondemo.app.object;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by qingdi on 3/8/16.
 */
public class Config {

    public static final String SHARED_PREF_NAME = "VISION_DEMO_CONFIG";

    public static final String  PROP_NAME_CENTER_LEFTX = "CENTER_LEFT_X";
    public static final String  PROP_NAME_CENTER_RIGHTX = "CENTER_RIGHT_X";
    public static final String  PROP_NAME_CENTERY = "CENTER_Y";
    public static final String  PROP_NAME_GREY = "GREY";
    public static final String  PROP_NAME_ALPHA = "ALPHA";
    public static final String  PROP_NAME_RADIUS = "RADIUS";
    public static final String  PROP_NAME_GAP = "GAP";

    public static final int     DEFAULT_GREY = 0xFF;
    public static final int     DEFAULT_ALPHA = 0xFF;
    public static final int     DEFAULT_RADIUS = 5;
    public static final int     DEFAULT_GAP = 20;

    public static final int     MAX_GREY = 0xFF;
    public static final int     MAX_ALPHA = 0xFF;
    public static final int     MAX_RADIUS = 30;
    public static final int     MAX_GAP = 100;

    public static final int     CENTER_RADIUS = 6;

    private int centerLeftX, centerRightX, centerY;
    private int grey, alpha, radius, gap;


    private Config (int centerLeftX, int centerRightX, int centerY, int grey, int alpha, int radius, int gap) {
        this.centerLeftX = centerLeftX;
        this.centerRightX = centerRightX;
        this.centerY = centerY;
        this.grey = grey;
        this.alpha = alpha;
        this.radius = radius;
        this.gap = gap;

    }

    public int getCenterLeftX() {
        return centerLeftX;
    }

    public void setCenterLeftX(int centerLeftX) {
        this.centerLeftX = centerLeftX;
    }

    public int getCenterRightX() {
        return centerRightX;
    }

    public void setCenterRightX(int centerRightX) {
        this.centerRightX = centerRightX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getIntensity() {
        return grey;
    }

    public void setIntensity(int grey) {
        this.grey = grey;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public static Config loadConfig(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        Point size = getDisplaySize(activity);
        Config config = new Config(sharedPref.getInt(PROP_NAME_CENTER_LEFTX, (int)(size.x*0.25)),
                                   sharedPref.getInt(PROP_NAME_CENTER_RIGHTX, (int)(size.x*0.75)),
                                   sharedPref.getInt(PROP_NAME_CENTERY, size.y/2),
                                   sharedPref.getInt(PROP_NAME_GREY, DEFAULT_GREY),
                                   sharedPref.getInt(PROP_NAME_ALPHA, DEFAULT_ALPHA),
                                   sharedPref.getInt(PROP_NAME_RADIUS, DEFAULT_RADIUS),
                                   sharedPref.getInt(PROP_NAME_GAP, DEFAULT_GAP));
        return config;
    }

    public static Point getDisplaySize(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        return size;
    }

    public void saveConfig(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(PROP_NAME_CENTER_LEFTX, centerLeftX);
        editor.putInt(PROP_NAME_CENTER_RIGHTX, centerRightX);
        editor.putInt(PROP_NAME_CENTERY, centerY);
        editor.putInt(PROP_NAME_GREY, grey);
        editor.putInt(PROP_NAME_ALPHA, alpha);
        editor.putInt(PROP_NAME_GAP, gap);
        editor.putInt(PROP_NAME_RADIUS, radius);

        editor.commit();
    }


    public void reset(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        Point size = getDisplaySize(activity);
        centerLeftX = (int)(size.x*0.25);
        centerRightX = (int)(size.x*0.75);
        centerY = size.y/2;
        grey = DEFAULT_GREY;
        alpha = DEFAULT_ALPHA;
        radius = DEFAULT_RADIUS;
        gap = DEFAULT_GAP;

    }
}
