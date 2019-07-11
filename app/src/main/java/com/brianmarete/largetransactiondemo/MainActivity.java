package com.brianmarete.largetransactiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button liveBridgeButton;
    Button largeTransactionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveBridgeButton = findViewById(R.id.live_bridge_button);
        liveBridgeButton.setOnClickListener(v ->
                startActivity(new Intent(this, LiveBridgeActivity.class)));

        largeTransactionButton = findViewById(R.id.transaction_too_large_button);
        largeTransactionButton.setOnClickListener(v ->
                startActivity(new Intent(this, TransactionTooLargeActivity.class)));

    }
}
