package hanks.pub.themedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment1
 * Created by hanks on 2017/8/9.
 */

public class Fragment2 extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<User> data = new ArrayList<>();

    public static Fragment2 newInstance() {

        Bundle args = new Bundle();

        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.name = "路人" + i;
            user.age = i;
            data.add(user);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SimpleListAdapter(data);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        data.clear();
        recyclerView.clearDisappearingChildren();
    }

    public static class SimpleListAdapter extends RecyclerView.Adapter<SimpleViewHolder>{

        private final List<User> data;

        public SimpleListAdapter(List<User> data) {
            this.data = data;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user, parent, false);
            final SimpleViewHolder holder = new SimpleViewHolder(view);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"click:" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    CommonActivity.start(v.getContext());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            User user = data.get(position);
            holder.tv_username.setText(user.name);
            holder.tv_age.setText("年龄" + user.age);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_avatar;
        private final TextView tv_username;
        private final TextView tv_age;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            iv_avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_age = (TextView) itemView.findViewById(R.id.tv_age);
        }
    }


}
