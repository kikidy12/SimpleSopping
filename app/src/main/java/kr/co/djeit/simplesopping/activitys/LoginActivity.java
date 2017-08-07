package kr.co.djeit.simplesopping.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.co.djeit.simplesopping.R;

public class LoginActivity extends BaseActivity {

    private android.widget.ImageView tempBtn;
    private android.widget.Button loginbtn;
    private android.widget.Button cancelbtn;
    private android.widget.LinearLayout loginMiniLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BindViews();
        SetValues();
        SetUpEvents();

    }


    @Override
    public void SetUpEvents() {
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempBtn.setVisibility(View.GONE);
                loginMiniLayout.setVisibility(View.VISIBLE);
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempBtn.setVisibility(View.VISIBLE);
                loginMiniLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void SetValues() {
    }

    @Override
    public void BindViews() {
        this.loginMiniLayout = (LinearLayout) findViewById(R.id.loginMiniLayout);
        this.cancelbtn = (Button) findViewById(R.id.cancel_btn);
        this.loginbtn = (Button) findViewById(R.id.login_btn);
        this.tempBtn = (ImageView) findViewById(R.id.tempBtn);
    }

}
