package kr.co.djeit.simplesopping.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.djeit.simplesopping.R;

public class SignUpActivity extends BaseActivity {

    private android.widget.EditText idEdt;
    private android.widget.Button idCheckBtn;
    private android.widget.EditText pwEdt;
    private android.widget.EditText pwCheckEdt;
    private android.widget.TextView messageTxt;
    private android.widget.ImageView alertImg;
    private android.widget.TextView birthDayTxt;
    private android.widget.Button signUpBtn;

    private Calendar tempCal;
    private boolean isIdOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        BindViews();
        SetUpEvents();
        SetValues();
    }

    @Override
    public void BindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.birthDayTxt = (TextView) findViewById(R.id.birthDayTxt);
        this.alertImg = (ImageView) findViewById(R.id.alertImg);
        this.messageTxt = (TextView) findViewById(R.id.messageTxt);
        this.pwCheckEdt = (EditText) findViewById(R.id.pwCheckEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idCheckBtn = (Button) findViewById(R.id.idCheckBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void SetUpEvents() {

        idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputId = idEdt.getText().toString();
                if (inputId.equals("user")) {
                    Toast.makeText(SignUpActivity.this, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
                else if (inputId.length() == 0) {
                    Toast.makeText(SignUpActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (inputId.length() < 8) {
                    Toast.makeText(SignUpActivity.this, "ID의 길이가 너무 짧습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "사용해도 좋습니다.", Toast.LENGTH_SHORT).show();
                    isIdOk = true;
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPw = pwEdt.getText().toString();
                String inputCheckPw = pwCheckEdt.getText().toString();

                if (inputPw.length() >= 8 && inputPw.equals(inputCheckPw) && isIdOk) {

                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignUpActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPwAndChangeMessage();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pwCheckEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPwAndChangeMessage();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tempCal = Calendar.getInstance();

        birthDayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDayTxt.setText("" + year+month+dayOfMonth);
                    }
                }, tempCal.get(Calendar.YEAR),
                        tempCal.get(Calendar.MONTH),
                        tempCal.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    @Override
    public void SetValues() {

    }

    public void checkPwAndChangeMessage() {
        String pwStr = pwEdt.getText().toString();
        String pwCheckStr = pwCheckEdt.getText().toString();

        if (pwStr.length() == 0) {
            messageTxt.setText("비밀번호를 입력하세요.");
            messageTxt.setTextColor(Color.parseColor("#FF0000"));
            alertImg.setImageResource(R.drawable.red_alert_icon);
        }
        else if (pwStr.length() < 8) {
            messageTxt.setText("비밀번호의 길이가 너무 짧습니다.");
            messageTxt.setTextColor(Color.parseColor("#FF0000"));
            alertImg.setImageResource(R.drawable.red_alert_icon);
        }
        else if (!pwStr.equals(pwCheckStr)){
            messageTxt.setText("비밀번호가 다릅니다.");
            messageTxt.setTextColor(Color.parseColor("#FF0000"));
            alertImg.setImageResource(R.drawable.red_alert_icon);
        }
        else {
            messageTxt.setText("회원가입이 가능합니다.");
            messageTxt.setTextColor(Color.parseColor("#00FF00"));
            alertImg.setImageResource(R.drawable.ok_icon);
        }
    }
}
