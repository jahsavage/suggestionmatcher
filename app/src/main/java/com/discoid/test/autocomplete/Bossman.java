package com.discoid.test.autocomplete;

/**
 * Created by jahsavage on 20/06/2016.
 */
public class Bossman extends BaseApplication {

    private static Bossman sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Bossman getInstance() {
        return sInstance;
    }
}
