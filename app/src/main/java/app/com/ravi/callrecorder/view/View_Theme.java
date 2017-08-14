package app.com.ravi.callrecorder.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.util.Utils;

/**
 * A login screen that offers login via email/password.
 */
public class View_Theme extends AppCompatActivity implements View.OnClickListener {

    RadioGroup selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__theme);


        selectedTheme = (RadioGroup) findViewById(R.id.radioGroup);

        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.btn_submit);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                int selectedId = selectedTheme.getCheckedRadioButtonId();
                RadioButton radioSexButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(View_Theme.this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
                if (radioSexButton.getText().equals("Dark Blue")) {
                    Utils.changeToTheme(this, Utils.THEME_DEFAULT);
                    startActivity(new Intent(this, View_home.class));
                    finish();
                } else if (radioSexButton.getText().equals("Light Blue")) {
                    Utils.changeToTheme(this, Utils.THEME_WHITE);
                    startActivity(new Intent(this, View_home.class));
                    finish();
                } else {
                    Utils.changeToTheme(this, Utils.THEME_BLUE);
                    startActivity(new Intent(this, View_home.class));
                    finish();
                }


                break;
        }
    }
}

