package hanks.pub.themedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment1
 * Created by hanks on 2017/8/9.
 */

public class ChildFragment3 extends BaseFragment {
    public static ChildFragment3 newInstance() {

        Bundle args = new Bundle();

        ChildFragment3 fragment = new ChildFragment3();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_3, container, false);
        return view;
    }
}
