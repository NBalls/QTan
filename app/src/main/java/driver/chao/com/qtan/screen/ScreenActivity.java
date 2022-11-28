package driver.chao.com.qtan.screen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import driver.chao.com.qtan.R;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ((EditText) findViewById(R.id.screen_time_edt_content)).setText(simpleDateFormat.format(new Date()));

        ((EditText) findViewById(R.id.screen_content_edt_content)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                String[] conArray = content.split(" ");
                if (conArray != null && conArray.length >= 2) {
                    ((EditText) findViewById(R.id.screen_title_edt_content)).setText("" + conArray[1] + " " + conArray[0]);
                }
            }
        });

        findViewById(R.id.screen_button_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) findViewById(R.id.screen_title_edt_content)).getText().toString();
                String time = ((EditText) findViewById(R.id.screen_time_edt_content)).getText().toString();
                String content = ((EditText) findViewById(R.id.screen_content_edt_content)).getText().toString();

                ((TextView) findViewById(R.id.screen_name_tv)).setText(title);
                ((TextView) findViewById(R.id.screen_time_tv)).setText(time);
                ((TextView) findViewById(R.id.screen_time_tv2)).setText(time);
                ((TextView) findViewById(R.id.screen_content_tv)).setText(content);
            }
        });


    }
}
