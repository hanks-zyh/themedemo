package hanks.pub.themedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * BaseActivity
 * Created by hanks on 2017/8/9.
 */

public class BaseActivity extends AppCompatActivity {

    private boolean isNight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        SharedPreferences sp = getSharedPreferences("theme", Context.MODE_PRIVATE);
        isNight = sp.getBoolean("isNight", false);

        if (isNight) {
            setTheme(R.style.CThemeNight);
        } else {
            setTheme(R.style.CTheme);
        }

        super.onCreate(savedInstanceState);
    }
}
