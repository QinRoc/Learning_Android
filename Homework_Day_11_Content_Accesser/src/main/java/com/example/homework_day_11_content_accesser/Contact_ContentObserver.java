package com.example.homework_day_11_content_accesser;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Roc on 2017/3/26.
 */

public class Contact_ContentObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public Contact_ContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        Log.i("Contact_ContentObserver", "contacts changed!");
    }
}
