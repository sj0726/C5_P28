package com.example.w7_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private EditText editText_wrongWord, editText_correctWord;
    private Button button_correct, button_update,button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_correctWord = findViewById(R.id.editText_correctWord);
        editText_wrongWord = findViewById(R.id.editText_wrongWord);
        button_correct = findViewById(R.id.button_correct);
        button_delete = findViewById(R.id.button_delete);
        button_update = findViewById(R.id.button_update);

        dbManager = new DatabaseManager( this );
        dbManager.dropTable();
        dbManager.insert("teh", "the");
        dbManager.insert("saturaday", "saturday");
        dbManager.insert("kindergarden", "kindergarten");
        dbManager.deleteByWrongWord("teh");
        dbManager.updateByWrongWord("saturaday", "SATURDAY");


        button_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wrongWord = editText_wrongWord.getText().toString();
                String[] pairWords = dbManager.selectByWrongWord(wrongWord);
                String outputwrongword = pairWords[0];
                String opcword = pairWords[1];
                editText_wrongWord.setText(outputwrongword);
                editText_correctWord.setText(opcword);
                editText_wrongWord.setHint("no word fund");

            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wrongWord = editText_wrongWord.getText().toString();
                String correctWord = editText_correctWord.getText().toString();
                dbManager.insert(wrongWord,correctWord);
                editText_wrongWord.setText("");
                editText_wrongWord.setHint("update success");
                editText_correctWord.setText("");
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wrongWord = editText_wrongWord.getText().toString();
                dbManager.deleteByWrongWord(wrongWord);
                editText_wrongWord.setText("");
                editText_wrongWord.setHint("delete success");
                editText_correctWord.setText("");
            }
        });
    }
}