package com.helpsheet;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    HelpSheetAdapter HSAdapter;
    String[] helpSheetSings;
    int[] imageIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helpSheetSings = getResources().getStringArray(R.array.helpSheetTitles);
        imageIds = getResources().getIntArray(R.array.helpSheetTitlesIcons);

        HSAdapter = new HelpSheetAdapter(this);
        setListAdapter(HSAdapter);

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


