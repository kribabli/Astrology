package com.example.astrology;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.astrology.Common.HelperData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NameAndDOBActivity extends AppCompatActivity {
    EditText userName, date, time, birthPlace;
    TextView NEXT1, NEXT2, NEXT3, NEXT4, submit;
    TextView male, female, other;
    LinearLayout LinearLayout1, LinearLayout2, LinearLayout3, LinearLayout4, LinearLayout5;
    HelperData helperData;
    private Boolean isButtonClick = true;
    String year = "", monthName = "", dateString = "";
    DatePickerDialog datePickerDialog;
    TextView backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_dobactivity);
        initMethod();
        setAction();
        LinearLayout1.setVisibility(View.VISIBLE);
    }

    private void initMethod() {
        userName = findViewById(R.id.userName);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        birthPlace = findViewById(R.id.birthPlace);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        backPress = findViewById(R.id.backPress);

        NEXT1 = findViewById(R.id.NEXT1);
        NEXT2 = findViewById(R.id.NEXT2);
        NEXT3 = findViewById(R.id.NEXT3);
        NEXT4 = findViewById(R.id.NEXT4);
        submit = findViewById(R.id.submit);
        LinearLayout1 = findViewById(R.id.LinearLayout1);
        LinearLayout2 = findViewById(R.id.LinearLayout2);
        LinearLayout3 = findViewById(R.id.LinearLayout3);
        LinearLayout4 = findViewById(R.id.LinearLayout4);
        LinearLayout5 = findViewById(R.id.LinearLayout5);
        helperData = new HelperData(getApplicationContext());
    }

    private void setAction() {
        NEXT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

        NEXT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation2();
            }
        });

        NEXT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation3();
            }
        });

        NEXT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation4();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation5();
            }
        });

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private boolean validation() {
        boolean isValid = true;
        if (userName.getText().toString().trim().length() == 0) {
            userName.setError("Please enter user name");
            userName.requestFocus();
            isValid = false;
        } else {
            loginUserDeatil();
        }
        return isValid;
    }

    private void loginUserDeatil() {
        LinearLayout1.setVisibility(View.GONE);
        LinearLayout2.setVisibility(View.VISIBLE);
    }

    private boolean validation2() {
        boolean isValid = true;
        if (userName.getText().toString().trim().length() == 0) {
            userName.setError("Please enter user name");
            userName.requestFocus();
            isValid = false;
        } else {
            loginUserDeatil2();
        }
        return isValid;
    }

    private void loginUserDeatil2() {
        LinearLayout1.setVisibility(View.GONE);
        LinearLayout2.setVisibility(View.GONE);
        LinearLayout3.setVisibility(View.VISIBLE);
    }

    private boolean validation3() {
        boolean isValid = true;
        if (date.getText().toString().trim().length() == 0) {
            date.setOnClickListener(v -> {
                if (isButtonClick) {
                    isButtonClick = false;
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonths = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(NameAndDOBActivity.this, (view, years, monthsOfYear, dayOfMonths) -> {

                        year = "" + years;
                        dateString = dayOfMonths + "-" + (monthsOfYear + 1) + "-" + years;

                        try {
                            monthName = new SimpleDateFormat("MMMM").format(new SimpleDateFormat("dd-MM-yyyy").parse(dateString));
                        } catch (Exception e) {

                        }

                        try {
                            dateString = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(dateString));
                        } catch (Exception e) {

                        }
                        date.setText(dateString);
                        isButtonClick = true;
                    }, mYear, mMonths, mDay);
                    datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", ((dialog, which) -> {
                        datePickerDialog.dismiss();
                        isButtonClick = true;
                    }));
                    datePickerDialog.show();
                }
            });
            isValid = false;
        } else {
            loginUserDeatil3();
        }
        return isValid;
    }

    private void loginUserDeatil3() {
        LinearLayout1.setVisibility(View.GONE);
        LinearLayout2.setVisibility(View.GONE);
        LinearLayout3.setVisibility(View.GONE);
        LinearLayout4.setVisibility(View.VISIBLE);
    }

    private boolean validation4() {
        boolean isValid = true;
        if (time.getText().toString().trim().length() == 0) {
            time.setError("Please enter Birth Time");
            time.requestFocus();
            isValid = false;
        } else {
            loginUserDeatil4();
        }
        return isValid;
    }

    private void loginUserDeatil4() {
        LinearLayout1.setVisibility(View.GONE);
        LinearLayout2.setVisibility(View.GONE);
        LinearLayout3.setVisibility(View.GONE);
        LinearLayout4.setVisibility(View.GONE);
        LinearLayout5.setVisibility(View.VISIBLE);
    }

    private boolean validation5() {
        boolean isValid = true;
        if (birthPlace.getText().toString().trim().length() == 0) {
            birthPlace.setError("Please enter your Birth Place");
            birthPlace.requestFocus();
            isValid = false;
        } else {
            loginUserDeatil5();
        }
        return isValid;
    }

    private void loginUserDeatil5() {
        helperData.saveLogin(getIntent().getStringExtra("mobile"), userName.getText().toString(), "", date.getText().toString(), time.getText().toString(), birthPlace.getText().toString());
        LinearLayout1.setVisibility(View.GONE);
        LinearLayout2.setVisibility(View.GONE);
        LinearLayout3.setVisibility(View.GONE);
        LinearLayout4.setVisibility(View.GONE);
        LinearLayout5.setVisibility(View.GONE);
        Intent i = new Intent(NameAndDOBActivity.this, MainActivity.class);
        helperData.saveIsLogin(true);
        startActivity(i);
        finish();
    }

    protected void onResume() {
        super.onResume();
        isButtonClick = true;
    }
}