package com.example.agecalculator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agecalculator.Age.AgeElement;
import com.example.agecalculator.Database.AgeDB;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AgeDapter.itemClick {

    private TextView tvBDFrom, tvBDTo;
    private Button btnCalculate;

    DatePickerDialog.OnDateSetListener listener;
    DatePickerDialog.OnDateSetListener listener_;

    //for recycler view
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList <AgeElement> ageElements;

    final String[] dateFrom = new String[3];
    final String[] dateTo = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBDFrom = findViewById(R.id.tvBDFrom);
        tvBDTo = findViewById(R.id.tvBDTo);
        btnCalculate = findViewById(R.id.btnCalculateAge);

        final String[] dFrom = new String[1];
        final String[] dTo = new String[1];

        final Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day   = calendar.get(Calendar.DAY_OF_MONTH);
        int year  = calendar.get(Calendar.YEAR);

        ageElements = new ArrayList<>();

//        adapter.notifyDataSetChanged();


        //From Datepicker Calendar
        tvBDFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light, listener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dFrom[0] = dayOfMonth + "/" + month + "/" + year;
                dateFrom[0] = dayOfMonth + "";
                dateFrom[1] = month + "";
                dateFrom[2] = year + "";
                tvBDFrom.setText(dFrom[0]);
            }
        };



//        To Datepicker Calendar
        tvBDTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light, listener_, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        listener_ = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dTo[0] = dayOfMonth + "/" + month + "/" + year;
                dateTo[0] = dayOfMonth + "";
                dateTo[1] = month + "";
                dateTo[2] = year + "";
                tvBDTo.setText(dTo[0]);
            }
        };


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvBDTo.getText().toString() != "" && tvBDFrom.getText().toString() != ""){
                    Intent intent = new Intent(MainActivity.this, CalculateAge.class);

                    //put from date into intent
                    intent.putExtra("dFrom", dateFrom[0]);
                    intent.putExtra("mFrom", dateFrom[1]);
                    intent.putExtra("yFrom", dateFrom[2]);

                    //put to date into intent
                    intent.putExtra("dTo", dateTo[0]);
                    intent.putExtra("mTo", dateTo[1]);
                    intent.putExtra("yTo", dateTo[2]);

                    intent.putExtra("dateFrom", dFrom[0]);
                    intent.putExtra("dateTo", dTo[0]);


                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please Fill Up Date", Toast.LENGTH_LONG).show();
                }
            }
        });

        setElementIntoArrayList();
        addAdapter();

    }

    private void setElementIntoArrayList() {
        AgeDB db = new AgeDB(this);
        Cursor cursor = db.getData();

        while (cursor.moveToNext()){
            ageElements.add(new AgeElement(cursor.getInt(3), cursor.getInt(2),
                    cursor.getInt(1), cursor.getString(4), cursor.getString(5)));
        }

    }


    private void addAdapter() {
        recyclerView = findViewById(R.id.recycleList);

        adapter = new AgeDapter(this, ageElements);

        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int index) {
        //here we access every save element accordin to their index
        Toast.makeText(MainActivity.this, ageElements.get(index).getTitle(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, demo.class));
    }
}