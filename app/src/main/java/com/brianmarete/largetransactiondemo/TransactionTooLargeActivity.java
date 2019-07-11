package com.brianmarete.largetransactiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class TransactionTooLargeActivity extends AppCompatActivity {
    public static final String KEY = "KeyIndex";

    private int index = 0;

    Button addStateFragButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_too_large);

        if (savedInstanceState != null) {
            this.index = savedInstanceState.getInt("KeyIndex");
        }

        addStateFragButton = findViewById(R.id.add_state_frag_button);
        addStateFragButton.setOnClickListener(v -> {
            index++;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, TransactionTooLargeFragment.newInstance(index))
                    .addToBackStack(TransactionTooLargeFragment.TAG)
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
