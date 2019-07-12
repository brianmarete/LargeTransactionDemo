package com.brianmarete.largetransactiondemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livefront.bridge.Bridge;

import icepick.State;

public class BridgeFragment extends Fragment {

    public static final String TAG = "BridgeFragment";
    public static final String KEY = "KeyIndex";

    /**
     * State annotation tells the Icepick library to manage the saved state of the data array.
     */
    @State
    int[] data;
    private int size = 0;

    public static Fragment newInstance(int index) {
        BridgeFragment fragment = new BridgeFragment();

        Bundle args = new Bundle();
        args.putInt(KEY, index);

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = this.getArguments();

        if (args != null) {
            size = args.getInt(KEY) * 100000;
        }

        if (savedInstanceState != null) {
            /*
             * We use Bridge to restore what was in the instance state
             */
            Bridge.restoreInstanceState(this, savedInstanceState);
        } else {
            data = new int[size];
        }

        return inflater.inflate(R.layout.fragment_basic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView indexText = view.findViewById(R.id.index_text);

        if (data != null) {
            indexText.setText(String.format(getResources().getString(R.string.num_of_elements), data.length));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /*
         * Let Bridge manage the saving of the instance state, allowing us to store larger
         * variables in the instance state
         */
        Bridge.saveInstanceState(this, outState);
    }
}
