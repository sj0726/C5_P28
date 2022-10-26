package com.example.w7_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager( this );
        dbManager.dropTable();
        dbManager.insert("teh", "the");
        dbManager.insert("saturaday", "saturday");
        dbManager.insert("kindergarden", "kindergarten");
        dbManager.deleteByWrongWord("teh");
        dbManager.updateByWrongWord("saturaday", "SATURDAY");
        String[] selected = dbManager.selectByWrongWord("saturaday");
        Log.d("debugging::", selected[0] + " " + selected[1]);

        ArrayList<String[]> all = dbManager.selectAll();
        String result = "";
        for (String[] s : all) {
            result += "( " + s[0] + " " + s[1] + " ) ";
        }
        Log.d("debugging::", result);
    }
}