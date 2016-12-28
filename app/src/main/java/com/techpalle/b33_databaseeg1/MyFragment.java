package com.techpalle.b33_databaseeg1;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    EditText et1, et2;
    Button b1, b2;
    //TextView tv;
    ListView lv;
    Cursor c;
    SimpleCursorAdapter s;

    //DECLARE VARIABLE FOR DATABASE
    MyDatabase m;

    public MyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        et1 = (EditText) v.findViewById(R.id.editText1);
        et2 = (EditText) v.findViewById(R.id.editText2);
        b1 = (Button) v.findViewById(R.id.button1);
        b2 = (Button) v.findViewById(R.id.button2);
        //tv = (TextView) v.findViewById(R.id.textView1);
        lv = (ListView) v.findViewById(R.id.listView1);


        //CREATE DATABASE OBJECT - potential crash possibility
        m = new MyDatabase(getActivity());
        //OPEN DATABASE
        m.open();
        //WRITE CODE HERE
        c = m.getEmp(); //intialize or get all employees into cursor
        s = new SimpleCursorAdapter(getActivity(),
                                    R.layout.row,
                                    c,
                                    new String[]{"_id","ename","esal"},
                                    new int[]{R.id.textView, R.id.textView2,R.id.textView3});
        lv.setAdapter(s);
/*
        StringBuilder sb = new StringBuilder();
        while (c.moveToNext() == true){
            //THAT MEANS NEXT VALID ROW IS AVAILABLE
            int id = c.getInt(0); //READS ENO _ID
            String name = c.getString(1); //READS EMP NAME
            int sal = c.getInt(2); //READS EMP SALARY
            sb.append(id+":"+name+":"+sal+"\n");
        }
        //Display emp details on textview
        tv.setText(sb);

*/
        //button1 click - for inserting into employee table
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString();
                int sal = Integer.parseInt(et2.getText().toString());
                m.insertEmp(name, sal);
                c.requery(); //this refreshes cursor with latest rows, and ref listview
/*
                //WRITE CODE HERE
                Cursor c = m.getEmp();
                StringBuilder sb = new StringBuilder();
                while (c.moveToNext() == true){
                    //THAT MEANS NEXT VALID ROW IS AVAILABLE
                    int id = c.getInt(0); //READS ENO _ID
                    String nm = c.getString(1); //READS EMP NAME
                    int sl = c.getInt(2); //READS EMP SALARY
                    sb.append(id+":"+nm+":"+sl+"\n");
                }
                //Display emp details on textview
                tv.setText(sb);
*/

            }
        });

        return v;
    }

}
