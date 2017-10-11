package hanks.pub.themedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private Map<String, List> savedData = new HashMap<>();

    public Map<String, List> getSavedData() {
        return savedData;
    }


    public static Bitmap getBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(drawingCache);
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return bitmap;
    }


    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(color);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    public void runCircleAnimation() {
        int size = ViewGroup.LayoutParams.MATCH_PARENT;
        final ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        final Bitmap v1 = getBitmapFromView(decorView);
        final ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(new BitmapDrawable(getResources(), v1));
        decorView.addView(imageView, new ViewGroup.LayoutParams(size, size));
        ViewPropertyAnimator animator = imageView.animate().alpha(0).setDuration(300);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                decorView.removeView(imageView);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        animator.start();
    }

    public void initFragment() {
        Fragment mainFragment = MainFragment.newInstance();
        Fragment old = getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName());
        if (old != null) {
            mainFragment.setInitialSavedState(getSupportFragmentManager().saveFragmentInstanceState(old));
            runCircleAnimation();
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (old != null) {
            transaction.remove(old);
        }
        transaction.replace(R.id.main_container, mainFragment, MainFragment.class.getName());
        transaction.commitNowAllowingStateLoss();
        if (old != null) {
            System.gc();
            System.runFinalization();
        }
    }
}
