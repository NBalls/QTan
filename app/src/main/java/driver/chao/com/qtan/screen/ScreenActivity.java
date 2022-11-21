package driver.chao.com.qtan.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import driver.chao.com.qtan.R;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

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
