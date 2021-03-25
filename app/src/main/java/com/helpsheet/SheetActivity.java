package com.helpsheet;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SheetActivity extends AppCompatActivity implements StringKeys {

    WebView sheetView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        sheetView = findViewById(R.id.sheetWebView);
        sheetView.setHorizontalScrollBarEnabled(false);

        Intent intent = getIntent();
        if (intent != null){
            String sheet = intent.getStringExtra(SHEET);
            showSheet(sheet);
        }
    }

    private void showSheet(String sheet) {
        try{
            sheetView.loadUrl(SHEETS_DIRECTORY + sheet);
        }catch (Exception e){
            sheetView.loadUrl(SHEETS_DIRECTORY + "service_pages/not_page");

        }
    }
}
