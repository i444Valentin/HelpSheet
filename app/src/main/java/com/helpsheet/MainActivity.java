package com.helpsheet;


import android.app.ListActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    HelpSheetAdapter HSAdapter;
    String[] helpSheetSings;
    int[] imageIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helpSheetSings = getResources().getStringArray(R.array.helpSheetTitles);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.helpSheetTitlesIcons);
        imageIds = new int[typedArray.length()];
        for (int i =0; i<imageIds.length;i++){
            imageIds[i] = typedArray.getResourceId(i,0);
        }
        typedArray.recycle();

        HSAdapter = new HelpSheetAdapter(this);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(HSAdapter);

    }


    public class HelpSheetAdapter extends BaseAdapter {
        private final LayoutInflater mLayoutInflater;

        HelpSheetAdapter (Context context){
            mLayoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return helpSheetSings.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = mLayoutInflater.inflate(R.layout.list_item, parent,false);

            ImageView image = (ImageView) convertView.findViewById(R.id.imageViewIcon);
            image.setImageResource(imageIds[position]);

            TextView signTextView = (TextView) convertView.findViewById(R.id.textViewSign);
            signTextView.setText(helpSheetSings[position]);

            return convertView;
        }
    }

}


