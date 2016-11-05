package com.si.tihe.tiheapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.si.tihe.tiheapp.R;

/**
 * Created on 5.11.2016.
 *
 * Simple text fragment
 *
 * @author Mišel Mojzeš
 */
public class SimpleTextFragment extends Fragment {

    private static final String ARGUMENT_STRING = "SimpleTextFragment.Extra.String";

    public static SimpleTextFragment createInstance(String text) {
        SimpleTextFragment instance = new SimpleTextFragment();
        Bundle b = new Bundle();
        b.putString(ARGUMENT_STRING, text);
        instance.setArguments(b);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text = (TextView) view.findViewById(R.id.text);
        Bundle args = getArguments();
        text.setText(args.getString(ARGUMENT_STRING, "null"));
    }
}
