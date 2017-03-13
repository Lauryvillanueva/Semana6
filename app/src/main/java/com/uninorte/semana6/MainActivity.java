package com.uninorte.semana6;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String TAG = "ElTagListView";
    private DataEntryDAO mDataEntryDao;
    private ArrayList<DataEntry> Entries;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //video 4- LisAll y borrado
        listView = (ListView) findViewById(R.id.ListView);
        //

        mDataEntryDao = new DataEntryDAO(this);

        Entries = mDataEntryDao.getAllEntrys();
        //  List<DataEntry> entryList = mDataEntryDao.getAllEntries();

        customAdapter = new CustomAdapter(this,Entries);

        listView.setAdapter(customAdapter);

        //PARA CUANDO SE DA CLICK- IDENTIFICAR CUAL SE LE DIO
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView,View v,int position, long l) {
                DataEntry dataEntry = (DataEntry) listView.getItemAtPosition(position);
                Log.d(TAG,"Click en Botton "+dataEntry.id);
                Intent iview= new Intent(MainActivity.this,ViewActivity.class);
                Bundle mBundle= new Bundle();
                mBundle.putSerializable("Entryview",dataEntry);
                iview.putExtras(mBundle);
                startActivity(iview);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this,ViewActivity.class);
                startActivityForResult(i,1);

                //mDataEntryDao.addDataEntry(new DataEntry(1,2));
            }
        });
    }

    protected void onDestroy(){
        mDataEntryDao.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK){
                DataEntry de= (DataEntry) data.getSerializableExtra("entry");
                mDataEntryDao.addDataEntry(de);
                Entries = mDataEntryDao.getAllEntrys();
                customAdapter.setData(Entries);
                ((CustomAdapter)listView.getAdapter()).notifyDataSetChanged();
            }
        }


    }

    public void onClickButtonRow(View view){
        DataEntry dataEntry= (DataEntry) view.getTag();
        Log.d(TAG,"Click en "+dataEntry.id);
        mDataEntryDao.deleteEntry(dataEntry);
        Entries = mDataEntryDao.getAllEntrys();
        customAdapter.setData(Entries);
        ((CustomAdapter)listView.getAdapter()).notifyDataSetChanged();

    }


}
