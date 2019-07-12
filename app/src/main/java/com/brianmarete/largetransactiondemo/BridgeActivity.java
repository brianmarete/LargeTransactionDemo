package com.brianmarete.largetransactiondemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.livefront.bridge.Bridge;

public class BridgeActivity extends AppCompatActivity {
    public static final String KEY = "KeyIndex";
    public static final String SHARED_TAG = "com.livefront.bridge.BridgeDelegate";

    private int index = 0;

    Button addStateFragButton;
    TextView shared_pref_size_text;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bridge);

        if (savedInstanceState == null) {
            Bridge.clearAll(getApplicationContext());
        } else {
            index = savedInstanceState.getInt(KEY);
        }

        /*
          This is just to show that Bridge is using sharedPreferences when saving instance state.
         */
        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_TAG, Context.MODE_PRIVATE);
        shared_pref_size_text = findViewById(R.id.shared_pref_size_text);
        shared_pref_size_text.setText(String.valueOf(sharedPreferences.getAll().size()));

        addStateFragButton = findViewById(R.id.add_state_frag_bridge_button);
        addStateFragButton.setOnClickListener(v -> {
            index++;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_bridge_container, BridgeFragment.newInstance(index))
                    .addToBackStack(BridgeFragment.TAG)
                    .commit();
        });
    }

    @Override
    public void onBackPressed() {
        index--;
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            outState.putInt(KEY, index);
        }
    }

}
