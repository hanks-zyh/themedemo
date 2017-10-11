package hanks.pub.themedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Fragment1
 * Created by hanks on 2017/8/9.
 */

public class ChildFragment1 extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<User> data = new ArrayList<>();


    public static ChildFragment1 newInstance() {

        Bundle args = new Bundle();

        ChildFragment1 fragment = new ChildFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("saveData", true);

        if (getActivity() instanceof MainActivity) {
            Map<String, List> savedData =
                    ((MainActivity) getActivity()).getSavedData();
            ArrayList<Object> list = new ArrayList<>();
            list.addAll(data);
            savedData.put("userList", list);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            if (getActivity() instanceof MainActivity) {
                Map<String, List> savedData = ((MainActivity) getActivity()).getSavedData();
                List list = savedData.get("userList");
                data.clear();
                data.addAll(list);
            }
        } else {
            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.name = "商品" + i;
                user.age = i;
                data.add(user);
            }
        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Fragment2.SimpleListAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        data.clear();
        recyclerView.clearDisappearingChildren();
    }
}
