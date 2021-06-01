package com.example.agecalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agecalculator.Database.AgeDB;

public class CalculateAge extends AppCompatActivity {

    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvShow;

    private TextView tvAge;
    private TextView tvyear, tvmonth, tvday;

    private Button btnSave;
    private Button btnShow;

    private long day, min, hour;
    private int final_date, final_mon, final_year;

    AgeDB db;


    private final String[] MONTH = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_age);

        tvFrom = findViewById(R.id.tvFrom);
        tvTo = findViewById(R.id.tvTo);
        tvAge = findViewById(R.id.age);
        tvyear = findViewById(R.id.tvYearId);
        tvmonth = findViewById(R.id.tvMonthId);
        tvday = findViewById(R.id.tvDayId);
        btnSave = findViewById(R.id.btnSaveId);

        //get data from MainActivity by intent.putExtra
        Bundle bundle = getIntent().getExtras();

        int dFrom = Integer.parseInt(bundle.getString("dFrom"));
        int mFrom = Integer.parseInt(bundle.getString("mFrom"));
        int yFrom = Integer.parseInt(bundle.getString("yFrom"));

        int dTo = Integer.parseInt(bundle.getString("dTo"));
        int mTo = Integer.parseInt(bundle.getString("mTo"));
        int yTo = Integer.parseInt(bundle.getString("yTo"));

        //age function call to calculate age
        age (dFrom, mFrom, yFrom, dTo, mTo, yTo);


        tvFrom.setText(dFrom+ " " + MONTH[mFrom] + " " + yFrom);
        tvTo.setText  (dTo+ " " + MONTH[mTo] + " " + yTo);




        db = new AgeDB(this);

        //button cclick and date and others save into ageSave arrayList here
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CalculateAge.this);
                builder.setTitle("Save the date \n");
                builder.setMessage("and see all date in 1 place");
                builder.setCancelable(false);

                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(50, 0, 20, 50);

                EditText etTitle = new EditText(getApplicationContext());
                etTitle.setHint("Title");

                TextView tvDate = new TextView(getApplicationContext());
                tvDate.setText(dFrom+ " " + MONTH[mFrom] + " " + yFrom);
                tvDate.setTextSize(20);

                linearLayout.addView(tvDate);
                linearLayout.addView(etTitle);

                builder.setView(linearLayout);


                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = String.valueOf(etTitle.getText());
                        String dateOfFrom = dFrom+ " " + MONTH[mFrom] + " " + yFrom;
                        //save data into database
                        boolean save = db.saveData(final_date, final_mon, final_year, title, dateOfFrom);

                        if (save){
                            Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "not save", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

//                builder.setView(view);
                builder.show();

            }
        });

    }

    private void age(int dFrom, int mFrom, int yFrom, int dTo, int mTo, int yTo) {

        int month[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (dFrom > dTo){
            dTo = dTo + month[mFrom - 1];
            mTo = mTo - 1;
        }

        if (mFrom > mTo){
            mTo = mTo + 12;
            yTo = yTo - 1;
        }

        //get day, month and year
        final_date = dTo - dFrom;
        final_mon = mTo - mFrom;
        final_year = yTo - yFrom;

        //set day, month, year and age into xml
        tvAge.setText(final_year + " year " + final_mon + " month " + final_date + " day");
        tvyear.setText(final_year + "");
        tvmonth.setText(final_mon + "");
        tvday.setText(final_date +"");

    }

}