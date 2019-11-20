package com.example.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner spinLocation;
    private Spinner spinRoomType;
    private TextView tvCheckInDate;
    private TextView tvCheckOutDate;
    private EditText etAdults, etChildren, etRoom;
    private Button btnCalculate;

    TextView tvLocation, tvRoomType, tvInDate, tvOutDate, tvAdults, tvChildren, tvRoom, tvSErvice, tvTax, tvTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCheckInDate = findViewById(R.id.tvCheckInDate);
        tvCheckOutDate =findViewById(R.id.tvCheckOutDate);
        etAdults = findViewById(R.id.etAdults);
        etChildren =  findViewById(R.id.etChildren);
        etRoom = findViewById(R.id.etRoom);
        btnCalculate =findViewById(R.id.btnCalculate);

        //scroll view outputs data binding
        tvLocation =  findViewById(R.id.tvLocation);
        tvRoomType = findViewById(R.id.tvRoomType);
        tvInDate =  findViewById(R.id.tvInDate);
        tvOutDate =  findViewById(R.id.tvOutDate);
        tvAdults =  findViewById(R.id.tvAdults);
        tvChildren = findViewById(R.id.tvChildren);
        tvRoom =  findViewById(R.id.tvRoom);
        tvSErvice = findViewById(R.id.tvService);
        tvTax =  findViewById(R.id.tvTax);
        tvTotal =  findViewById(R.id.tvTotal);

        final SimpleDateFormat formatdate = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        //spinner for location
        spinLocation =findViewById(R.id.spinLocation);
        String countries[] ={"Kathmandu","Patan","Bhaktapur"};
        ArrayAdapter adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);
        spinLocation.setAdapter(adapter);

        //spinner for room type
        spinRoomType=findViewById(R.id.spinRoomType);
        String roomType[] ={"AC","Delux","Normal"};
        ArrayAdapter adapters =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);
        spinRoomType.setAdapter(adapters);

        //setting OnClick Listener on check in date
        tvCheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadcheckIndate();
            }
        });

        //setting Onclick listener on check out date
        tvCheckOutDate.setOnClickListener(new View.OnClickListener() {
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

                tvLocation.setText("Location : "+spinLocation.getSelectedItem().toString());
                tvRoomType.setText("Room Type : "+spinRoomType.getSelectedItem().toString());
                tvInDate.setText(tvCheckInDate.getText().toString());
                tvOutDate.setText(tvCheckOutDate.getText().toString());
                tvAdults.setText("Number of Adults : "+etAdults.getText().toString());
                tvChildren.setText("NUmber of Children : "+etChildren.getText().toString());
                tvRoom.setText("NUmber of Rooms : "+etRoom.getText().toString());

                String dateIn = tvCheckInDate.getText().toString();
                String dateOUt = tvCheckOutDate.getText().toString();


            }
        });



    }


    private void loadcheckIndate()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year +"/"+month+"/"+dayOfMonth;
                tvCheckInDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private void loadchechkOutdate()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year +"/"+month+"/"+dayOfMonth;
                tvCheckOutDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
