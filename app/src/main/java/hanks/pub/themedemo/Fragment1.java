package hanks.pub.themedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment1
 * Created by hanks on 2017/8/9.
 */

public class Fragment1 extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabs;
    private PagerAdapter adapter;
    private SparseArray<Fragment> fragments = new SparseArray<>();

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragments.put(0, ChildFragment1.newInstance());
        fragments.put(1, ChildFragment2.newInstance());
        fragments.put(2, ChildFragment3.newInstance());

        adapter = new MainFragment.MainPageAdapter(getChildFragmentManager(), fragments);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());

        tabs.setupWithViewPager(viewPager);
    }
}
