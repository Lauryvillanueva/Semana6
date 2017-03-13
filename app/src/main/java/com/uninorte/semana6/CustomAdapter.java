package com.uninorte.semana6;

/**
 * Created by LauryV on 13/03/2017.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


//semana 5- listview con custom row CUARTO VIDEO----------------------------(row.xml hace parte de este video-customAdapter)

public class CustomAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<DataEntry> data;
    private View view;

    //constructor
    public CustomAdapter(Context context, ArrayList<DataEntry> values){
        this.context=context;
        this.data=values;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    //metodo para llenar cada uno de los elementos de las filas
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        DataEntry entry= data.get(i);
        int value1,value2;
        value1 = entry.field1;
        value2 = entry.field2;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row,null);
        }

        TextView TextField1= (TextView) view.findViewById(R.id.tvField1);
        TextView TextField2= (TextView) view.findViewById(R.id.tvField2);
        Button button =(Button) view.findViewById(R.id.buttonrow);



       // Log.d(CustomAdapter.class.getSimpleName(), "Id"+String.valueOf(entry.id));
       // Log.d(CustomAdapter.class.getSimpleName(), "Field1"+String.valueOf(entry.field1));
       // Log.d(CustomAdapter.class.getSimpleName(), "Field2"+String.valueOf(entry.field2));
        button.setFocusable(false);
        button.setFocusableInTouchMode(false);
        button.setTag(entry);
        TextField1.setText(String.valueOf(value1));
        TextField2.setText(String.valueOf(value2));
        return view;
    }

    public void setData(ArrayList<DataEntry> values){
        this.data=values;
    }
}





