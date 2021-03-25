package com.helpsheet;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,StringKeys {
    HelpSheetAdapter HSAdapter;
    String[] helpSheetSings;
    String[] sheets;
    ListView sheetsListView;
    int[] imageIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helpSheetSings = getResources().getStringArray(R.array.helpSheetTitles);
        sheets = getResources().getStringArray(R.array.files);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.helpSheetTitlesIcons);
        imageIds = new int[typedArray.length()];
        for (int i =0; i<imageIds.length;i++){
            imageIds[i] = typedArray.getResourceId(i,0);
        }
        typedArray.recycle();

        HSAdapter = new HelpSheetAdapter(this);

        sheetsListView = findViewById(R.id.sheetsListView);
        sheetsListView.setAdapter(HSAdapter);
        sheetsListView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,SheetActivity.class);
        intent.putExtra(SHEET,sheets[position]);
        Toast.makeText(this, HSAdapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sheetsListView.setOnItemClickListener(null);
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
            ViewHolder holder;
            if (convertView == null){
                convertView = mLayoutInflater.inflate(R.layout.list_item, parent,false);
                holder = new ViewHolder();
                holder.iconImageView = (ImageView) convertView.findViewById(R.id.imageViewIcon);
                holder.titleTextView = (TextView) convertView.findViewById(R.id.textViewSign);
                convertView.setTag(holder);
            }else holder = (ViewHolder) convertView.getTag();

            holder.titleTextView.setText(helpSheetSings[position]);
            holder.iconImageView.setImageResource(imageIds[position]);
            return convertView;
        }
    }

}


