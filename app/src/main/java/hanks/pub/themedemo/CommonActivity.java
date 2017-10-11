package hanks.pub.themedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * CommonActivity
 * Created by hanks on 2017/8/9.
 */

public class CommonActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CommonActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }
}
