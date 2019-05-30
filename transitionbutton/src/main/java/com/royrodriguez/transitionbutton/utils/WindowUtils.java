package com.royrodriguez.transitionbutton.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class WindowUtils {

    private static DisplayMetrics displayMetrics;

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public static void makeStatusbarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            WindowUtils.setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            WindowUtils.setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static int getWidth(Activity activity) {
        setupDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }

    public static int getHeight(Activity activity) {
        setupDisplayMetrics(activity);
        return displayMetrics.heightPixels;
    }

    public static int getWidth(Context context) {
        setupDisplayMetrics(context);
        return displayMetrics.widthPixels;
    }

    public static int getHeight(Context context) {
        setupDisplayMetrics(context);
        return displayMetrics.heightPixels;
    }

    private static void setupDisplayMetrics(Activity activity) {
        if (displayMetrics == null)
            displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    private static void setupDisplayMetrics(Context context) {
        if (displayMetrics == null)
            displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);
    }

}
