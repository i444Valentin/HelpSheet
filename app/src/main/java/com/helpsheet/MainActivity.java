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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,StringKeys {
    HelpSheetAdapter HSAdapter; //адаптер для списка
    String[] sheetTitles; //заголоки тем для шпаргалки
    String[] sheets;
    ListView sheetsListView;
    int[] sheetIconIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sheetTitles = getResources().getStringArray(R.array.helpSheetTitles);
        sheets = getResources().getStringArray(R.array.files);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.helpSheetTitlesIcons);
        sheetIconIds = new int[typedArray.length()];
        for (int i = 0; i< sheetIconIds.length; i++){
            sheetIconIds[i] = typedArray.getResourceId(i,0);
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
        intent.putExtra(TITLE, sheetTitles[position]);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sheetsListView.setOnItemClickListener(null); //закрываем слушатель при закрытий приложения
    }

    /**
     * Класс, реализующий свой адаптер для ListView
     *
     * Основывается на классе BaseAdapter
     * переопределяет его методы
     *
     * Примечание - BaseAdapter наследуется на ListAdapter, который также имплементирует методы Adapter
     */
    public class HelpSheetAdapter extends BaseAdapter {
        private final LayoutInflater mLayoutInflater;

        HelpSheetAdapter (Context context){
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return sheetTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * Возвращает вид для элемента списка, заполняя его значениями
         *
         * @param position - текущая позиция в списке
         * @param convertView - конвертируемый вид
         * @param parent - родитель
         * @return - заполненный вид
         */
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

            holder.titleTextView.setText(sheetTitles[position]);
            holder.iconImageView.setImageResource(sheetIconIds[position]);
            return convertView;
        }
    }

}


