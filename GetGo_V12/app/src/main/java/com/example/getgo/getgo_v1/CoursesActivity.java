package com.example.getgo.getgo_v1;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Siham on 2018-02-20.
 *
 * This Class will contain references to the list of courses
 * and can also place other lists of data information we need for logic.
 */

//dont open too many screens

public class CoursesActivity extends AppCompatActivity implements OnClickListener {

    /* Items entered by the user is stored in this ArrayList variable */
    //ArrayList items_list = new ArrayList();
    /*These values are being referenced from the xml files*/
    private Button btnAdd;
    private Button btnDelete;
    private Button btnModify;
    private ListView lv;

    private AutoCompleteTextView course_box;
    private EditText mark_box;
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<>();

    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapter_classes;
    ArrayList<String> Classes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initializing the product list
        super.onCreate(savedInstanceState);
        /** Setting a custom layout for the list activity */
        setContentView(R.layout.activity_courses);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        //Calls Singleton to fill dropdown menu
        adapter_classes = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item);
        new Singleton(adapter_classes).execute();

        autoCompleteTextView.setAdapter(adapter_classes);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);

        //Assigning variables to the list_box, the marks text box and the add button
        course_box = findViewById(R.id.autoCompleteTextView);


        mark_box = findViewById(R.id.editText);
        /** Reference to the add button of the layout */
        btnAdd = findViewById(R.id.add_button);

        btnAdd.setOnClickListener(this);


        btnDelete = findViewById(R.id.delete_button);
        btnDelete.setOnClickListener(this);

        //btnModify = findViewById(R.id.modify_button);
        //btnModify.setOnClickListener(this);

        lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    //When buttton is clicked, joins the strings and places in tex box
    public void onClick(View v) {

        String course_name = course_box.getText().toString();
        String course_mark = mark_box.getText().toString();

        switch (v.getId()) {
            case R.id.add_button:

                if (course_mark.length() > 0 && course_mark.length() > 0) {
                    String join = course_name + "    " + course_mark + "%";
                    adapter.add(join);
                    mark_box.setText("");
                    course_box.setText("");
                    adapter.notifyDataSetChanged();
                    Log.d("list_tag", "In List:  " + adapter.getItem(0));
                  }
                  break;
            case R.id.delete_button:
                SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();
                int itemCount = adapter.getCount();

                for (int i = itemCount - 1; i >= 0 ; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
                adapter.clear();
                mark_box.setText("");
                course_box.setText("");
                break;
            //case R.id.modify_button:
                //still need more work on Modify button: how to traverse the adapater to find the position of the current click
                //break;
        }
    }
}