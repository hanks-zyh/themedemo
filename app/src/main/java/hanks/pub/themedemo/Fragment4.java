package hanks.pub.themedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

/**
 * Fragment1
 * Created by hanks on 2017/8/9.
 */

public class Fragment4 extends BaseFragment {

    private SwitchCompat switch_theme;
    private SharedPreferences sp;

    public static Fragment4 newInstance() {

        Bundle args = new Bundle();

        Fragment4 fragment = new Fragment4();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences("theme", Context.MODE_PRIVATE);

        switch_theme = (SwitchCompat) view.findViewById(R.id.switch_theme);
        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = switch_theme.isChecked();
                if (checked) {
                    switchNightTheme();
                } else {
                    switchDayTheme();
                }
            }
        });
        switch_theme.setChecked(sp.getBoolean("isNight", false));

        view.findViewById(R.id.tv_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonActivity.start(getActivity());
            }
        });

    }

    private void switchNightTheme() {
        getActivity().setTheme(R.style.CThemeNight);
        sp.edit().putBoolean("isNight", true).apply();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setStatusBarColor(0xff202020);
            ((MainActivity) getActivity()).initFragment();
        }
    }

    private void switchDayTheme() {
        getActivity().setTheme(R.style.CTheme);
        sp.edit().putBoolean("isNight", false).apply();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setStatusBarColor(0xff303F9F);
            ((MainActivity) getActivity()).initFragment();
        }
    }


}
