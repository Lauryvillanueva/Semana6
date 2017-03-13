package com.uninorte.semana6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class ViewActivity extends Activity {
    EditText editText1,editText2;
    public String TAG = Constants.TAG;
    private boolean customview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        Bundle bundle= getIntent().getExtras();

        if (bundle!=null){
            customview=true;
            DataEntry dataEntry= (DataEntry) getIntent().getSerializableExtra("Entryview");
            editText1.setText(dataEntry.field1+"");
            editText2.setText(dataEntry.field2+"");
        }else{
            customview=false;
        }

        editText1.setFocusable(!customview);
        editText1.setFocusableInTouchMode(!customview);
        editText2.setFocusable(!customview);
        editText2.setFocusableInTouchMode(!customview);



    }
    //termine hasta el video 3 - creacion de la primera entrada---------------------------
    public void onClickAceptar(View view) {
        if(customview){
            finish();
        }
        if (TextUtils.isEmpty(editText1.getText().toString())){
            editText1.setError("No puede estar vacio");
        }else {
            if (TextUtils.isEmpty(editText2.getText().toString())){
                editText1.setError("No puede estar vacio");
            }else {
               DataEntry de = new DataEntry(Integer.parseInt(editText1.getText().toString()) , Integer.parseInt(editText2.getText().toString()) );
                Intent i = getIntent();
                i.putExtra("entry", de);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        }
    }
}
