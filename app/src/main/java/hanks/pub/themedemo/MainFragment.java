package hanks.pub.themedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment
 * Created by hanks on 2017/8/9.
 */

public class MainFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabs;
    private PagerAdapter adapter;
    private SparseArray<Fragment> fragments = new SparseArray<>();

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragments.put(0, Fragment1.newInstance());
        fragments.put(1, Fragment2.newInstance());
        fragments.put(2, Fragment3.newInstance());
        fragments.put(3, Fragment4.newInstance());

        adapter = new MainPageAdapter(getChildFragmentManager(), fragments);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());

        tabs.setupWithViewPager(viewPager);
    }

    public static class MainPageAdapter extends FragmentPagerAdapter {

        private final SparseArray<Fragment> fragments;

        public MainPageAdapter(FragmentManager fm, SparseArray<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "F" + (position + 1);
        }
    }

}
