package com.techpalle.b33_databaseeg1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//STEP 3 : CREATE A SEPARATE JAVA FILE FOR DATABASE LOGIC
public class MyDatabase {
    //STEP 5 : DECLARE ALL REQUIRED VARIABLES
    MyHelper m;
    SQLiteDatabase s;
    //STEP 6 : CREATE HELPER OBJECT - IN CONSTRUCTOR
    public MyDatabase(Context c){
        m = new MyHelper(c, "techpalle.db", null, 1);
    }
    //STEP 7 : OPEN DATABASE - IN OPEN METHOD
    public void open(){
        s = m.getWritableDatabase(); //load database into memory
    }
    //STEP 8 : DML OPERATIONS - INSERT, UPDATE, DELETE, QUERY
    //THIS METHOD WILL BE CALLED FROM FRAGMENT
    public void insertEmp(String name, int sal){
        ContentValues c = new ContentValues();
        c.put("ename", name); //1ST PARA - COLUMN NAME,   2ND PARA - VALUE
        c.put("esal", sal);
        s.insert("employee", null, c); //1ST - TABLE NAME, 2ND - NULL, 3RD - CONTENT VALS
    }
    public Cursor getEmp(){
        Cursor c = null;
        //q1 : read all employee rows from employee table
        c = s.query("employee", null, null, null, null, null, null);
/*
        //q2 : read employee whose no is 2
        // SELECT * FROM EMPLOYEE WHERE _ID = 2;
        c = s.query("employee", null, "_id = ?", new String[]{"2"}, null, null, null);
        //Q3 : READ EMPLOYEE DETAILS WHOSE NAME IS "steve"
        c = s.query("employee", null, "ename = ?", new String[]{"steve"}, null, null,null);
        //q4 : read all employees whose salary is > 15000
        c = s.query("employee", null, "esal > ?", new String[]{"15000"}, null,null,null);
        //q5 : read all employees whose id > 2 and salary < 40000
        c = s.query("employee", null, "_id > ? AND esal < ?", new String[]{"2","40000"},
                    null, null, null);
        //Q6 : READ ALL EMPLOYEES WHOSE NAME STARTS WITH s
        c = s.query("employee", null, "ename LIKE ?", new String[]{"s%"},
                    null, null, null);
*/
        return c;
    }
    //STEP 9 : CLOSE DATABASE
    public void close(){
        s.close(); //THIS CLEANS MEMORY, (SO THAT THERE IS NO MEMORY LEAK)
    }
    //STEP 4 : CREATE INNER HELPER CLASS WITH DDL STATMENTS
    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //here we have to create all tables
            sqLiteDatabase.execSQL("create table employee(_id integer primary key, ename text, esal integer);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
}
