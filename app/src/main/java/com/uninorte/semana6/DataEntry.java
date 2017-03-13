package com.uninorte.semana6;

import java.io.Serializable;

/**
 * Created by LauryV on 05/03/2017.
 */

//modelo estandar de base de datos


public class DataEntry implements Serializable {

    int id, field1 , field2;

    //constructor sin parametro
    public DataEntry(){}

    //constructor con los parametros
    public DataEntry(int id,int field1, int field2){
        this.id = id;
        this.field1 = field1;
        this.field2 = field2;
    }
    //constructor con los dos campos de datos
    public DataEntry(int field1, int field2){
        this.field1 = field1;
        this.field2 = field2;
    }


}
