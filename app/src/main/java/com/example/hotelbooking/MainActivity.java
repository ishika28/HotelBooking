package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner spinLocation;
    private Spinner spinRoomType;
    private EditText etAdults, etChildren, etRoom;
    private Button btnCalculate;
    TextView datepickerCheckInDate,  datepickerCheckOutDate;
    ProgressBar progressBarCircle;

    TextView tvDays, tvError, tvLocation, tvRoomType, tvInDate, tvOutDate, tvAdults, tvChildren, tvRoom, tvSErvice, tvTax, tvTotal;
    int Year2, Year3;
    int Month2, Month3;
    int Day2, Day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View handler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pbCircle.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                pbCircle.setVisibility(View.GONE);
            }

        }, 5000);
    }


    datepickerCheckInDate = findViewById(R.id.datepickerCheckInDate);
        datepickerCheckOutDate = findViewById(R.id.datepickerCheckOutDate);
        etAdults = findViewById(R.id.etAdults);
        etChildren = findViewById(R.id.etChildren);
        etRoom = findViewById(R.id.etRoom);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvError = (TextView) findViewById(R.id.tverror);

        //scroll view outputs data binding
        tvDays = findViewById(R.id.tvDays);
        tvLocation = findViewById(R.id.tvLocation);
        tvRoomType = findViewById(R.id.tvRoomType);
        tvInDate = findViewById(R.id.tvInDate);
        tvOutDate = findViewById(R.id.tvOutDate);
        tvAdults = findViewById(R.id.tvAdults);
        tvChildren = findViewById(R.id.tvChildren);
        tvRoom = findViewById(R.id.tvRoom);
        tvSErvice = findViewById(R.id.tvService);
        tvTax = findViewById(R.id.tvTax);
        tvTotal = findViewById(R.id.tvTotal);



        final SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        //spinner for location
        spinLocation = findViewById(R.id.spinLocation);
        String countries[] = {"Kathmandu", "Patan", "Bhaktapur"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        spinLocation.setAdapter(adapter);

        //spinner for room type
        spinRoomType = findViewById(R.id.spinRoomType);
        String roomType[] = {"AC", "Deluxe", "Normal"};
        ArrayAdapter adapters = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomType);
        spinRoomType.setAdapter(adapters);

        //setting OnClick Listener on check in date
        datepickerCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadcheckIndate();
            }
        });

        //setting Onclick listener on check out date
        datepickerCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadchechkOutdate();
            }
        });
        //button mode

        //validation should be done at first

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(tvInDate.getText())) {
                    tvError.setText("Please enter check in Date ");
                    return;
                } else if (TextUtils.isEmpty(tvOutDate.getText())) {
                    tvError.setText("Please enter checked out date ");
                    return;
                }


                tvLocation.setText("Location : " + spinLocation.getSelectedItem().toString());
                tvRoomType.setText("Room Type : " + spinRoomType.getSelectedItem().toString());

                tvAdults.setText("Number of Adults : " + etAdults.getText().toString());
                tvChildren.setText("Number of Children : " + etChildren.getText().toString());
                tvRoom.setText("Number of Rooms : " + etRoom.getText().toString());

                //Dates conversion for number of days
                Calendar calendar1 = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                calendar1.set(Year2, Month2, Day2);
                calendar2.set(Year3, Month3, Day3);
                long date1 = calendar1.getTimeInMillis();
                long date2 = calendar2.getTimeInMillis();
                long diff = date2 - date1;
                long diffDays = (diff / (86400000));

                //Calculation part
                int numRoom = Integer.parseInt(etRoom.getText().toString());
                double price;
                double TotalPrice;
                double ServiceCharge;
                double GrandTotal;


                String roomType = spinRoomType.getSelectedItem().toString();


                if (roomType == "Deluxe") {
                    //Room Price Per Night:"+"2000
                    price = 2500;
                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) + TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : " + diffDays);
                    tvRoom.setText(("Rooms booked : " + numRoom));
                    tvTotal.setText("Total : " + TotalPrice);
                    tvSErvice.setText("Price after service charge : " + ServiceCharge);
                    tvTax.setText("Price after tax inclusion : " + GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();


                } else if (roomType == "AC") {

                    price = 3500;
                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) + TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : " + diffDays);
                    tvRoom.setText(("Rooms booked : " + numRoom));
                    tvTotal.setText("Total : " + TotalPrice);
                    tvSErvice.setText("Price after service charge : " + ServiceCharge);
                    tvTax.setText("Price after tax inclusion : " + GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();

                } else if (roomType == "Normal") {
                    price = 1500;
                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) + TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : " + diffDays);
                    tvRoom.setText(("Rooms booked : " + numRoom));
                    tvTotal.setText("Total : " + TotalPrice);
                    tvSErvice.setText("Price after service charge : " + ServiceCharge);
                    tvTax.setText("Price after tax inclusion : " + GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();

                }


            }
        });

    }


    private void loadcheckIndate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked In At::" + month + "/" + dayOfMonth + "/" + year;
                Month2 = month;
                Day2 = dayOfMonth;
                Year2 = year;
                tvInDate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();
    }

    private void loadchechkOutdate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked Out At::" + month + "/" + dayOfMonth + "/" + year;
                Month3 = month;
                Day3 = dayOfMonth;
                Year3 = year;
                tvOutDate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();
    }
}
