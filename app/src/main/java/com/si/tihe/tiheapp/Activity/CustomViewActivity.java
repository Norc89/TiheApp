package com.si.tihe.tiheapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;
import com.si.tihe.tiheapp.R;
import com.si.tihe.tiheapp.activity.base.ToolbarActivity;
import com.si.tihe.tiheapp.db.entity.PersonData;
import com.si.tihe.tiheapp.db.entity.service.SQLitePersonDataService;
import com.si.tihe.tiheapp.views.CustomView;

import java.util.List;


/**
 * Created on 5.11.2016.
 *
 * Custom view with db use
 *
 * @author Mišel Mojzeš
 */
public class CustomViewActivity extends ToolbarActivity implements View.OnClickListener {

    private CustomView nameCV;
    private CustomView surnameCV;
    private Button insertButton;
    private Button readButton;
    private TextView textView;
    @Inject
    private SQLitePersonDataService sqLitePersonDataService;


    @Override
    protected void onCreateEx(Bundle savedInstanceState) {
        setTitle("CustomView");
        nameCV = (CustomView) findViewById(R.id.name);
        surnameCV = (CustomView) findViewById(R.id.surname);
        insertButton = (Button) findViewById(R.id.insert);
        readButton = (Button) findViewById(R.id.read);
        textView = (TextView) findViewById(R.id.resultsText);
        insertButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.custom_view_activitiy;
    }


    private void insertDb(String name, String surname) {
        sqLitePersonDataService.insertPersonItem(new PersonData(name, surname));
    }

    private void readDb() {
        List<PersonData> resultList = sqLitePersonDataService.getPersonRecords();
        String text = "";
        for(PersonData item : resultList) {
            text += "id:" + item.getId() + "name:" + item.getName() + "surname:" + item.getSurname()+ System.getProperty("line.separator");
        }
        textView.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert: insertDb(nameCV.getText(), surnameCV.getText()); break;
            case R.id.read: readDb(); break;
        }
    }
}
