package com.brianmarete.largetransactiondemo;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.livefront.bridge.Bridge;
import com.livefront.bridge.SavedStateHandler;

import icepick.Icepick;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Bridge.initialize(this, new SavedStateHandler() {
            @Override
            public void saveInstanceState(@NonNull Object target,
                                          @NonNull Bundle state) {
                Icepick.saveInstanceState(this, state);
            }

            @Override
            public void restoreInstanceState(@NonNull Object target,
                                             @Nullable Bundle state) {
                Icepick.restoreInstanceState(this, state);
            }
        });
    }
}
