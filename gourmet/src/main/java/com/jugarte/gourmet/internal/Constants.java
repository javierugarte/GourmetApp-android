package com.jugarte.gourmet.internal;

import android.content.Context;

import com.jugarte.gourmet.lib.R;

public class Constants {

    public static String getUrlGitHubProject() {
        return "https://www.github.com/gourmetapp/GourmetApp-android";
    }

    public static String getUrlHomePage() {
        return "https://gourmetapp.github.io/android/";
    }

    public static String getShareText(Context context) {
        return String.format(context.getResources().getString(R.string.share_text), getUrlHomePage());
    }

}
