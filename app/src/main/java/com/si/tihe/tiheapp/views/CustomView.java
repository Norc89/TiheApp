package com.si.tihe.tiheapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.si.tihe.tiheapp.R;


/**
 * Created on 5.11.2016.
 *
 * Custom view with enter value
 *
 * @author Mišel Mojzeš
 */
public class CustomView extends LinearLayout {

    private TextView description;
    private EditText editText;
    private Switch switchButton;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        String descriptionTextParam = a.getString(R.styleable.CustomView_descriptionText);
        String editTextHint = a.getString(R.styleable.CustomView_editTextHint);
        boolean switchEnabled = a.getBoolean(R.styleable.CustomView_switchEnabled, false);
        a.recycle();

        inflate(context, R.layout.custom_view, this);
        description = (TextView) findViewById(R.id.custom_description);
        editText = (EditText) findViewById(R.id.custom_edit_text);
        switchButton = (Switch) findViewById(R.id.custom_switch_custom);

        description.setText(descriptionTextParam);
        editText.setHint(editTextHint);
        switchButton.setChecked(switchEnabled);
    }

    private void setData(String descriptionText, String editTextHint, boolean switchEnabled) {
        description.setText(descriptionText);
        editText.setHint(editTextHint);
        switchButton.setChecked(switchEnabled);
    }

    public String getText() {
        return editText.getText().toString();
    }
}
