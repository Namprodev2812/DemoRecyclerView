package com.asterisk.nam.demorecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private List<Person> mListPersons, mListSubPersons;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private PersonsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LayoutAnimationController mController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        createPersons();
        createRecyclerView();
        createSwipeRefeshLayout();
    }

    public void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_main);
    }

    public void createPersons() {
        mListPersons = new ArrayList<>();
        mListPersons.add(new Person(getString(R.string.user_trinh_xuan_nam), 22, getString(R.string.phone_trinh_xuan_nam), R.drawable.newcap));
        mListPersons.add(new Person(getString(R.string.user_nguyen_duc_canh), 23, getString(R.string.phone_nguyen_duc_canh), R.drawable.acanh));
        mListPersons.add(new Person(getString(R.string.user_dao_tuan_anh), 23, getString(R.string.phone_dao_tuan_anh), R.drawable.ata));
        mListPersons.add(new Person(getString(R.string.user_ronaldo), 34, getString(R.string.phone_ronaldo), R.drawable.c_ronaldo));

        mListSubPersons = new ArrayList<>();
        mListSubPersons.addAll(mListPersons);
    }

    public void createRecyclerView() {
        mAdapter = new PersonsAdapter(this, mListPersons);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(divider);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(mRecyclerView);
    }


    public void createSwipeRefeshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(false); // co the su dung handle de quy dinh thoi gian refesh,
        // de chinh sac thoi gian load API thi can setRefeshing
    }

    @Override
    public void onRefresh() {
        //mListPersons.clear();

        //mListPersons.remove(mListPersons.size()-1);
        //mAdapter.notifyDataSetChanged();
        //mSwipeRefreshLayout.setRefreshing(false);

        Random random = new Random();
        int random1 = random.nextInt(4);
        runAnimation(mRecyclerView, random1);
        mListPersons.clear();
        mListPersons.addAll(mListSubPersons);
        mRecyclerView.setLayoutAnimation(mController);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
        mSwipeRefreshLayout.setRefreshing(false);
    }

//    @Override
//    public void onClick(int position) {
//        Toast.makeText(this,"position: " +position,Toast.LENGTH_SHORT).show();
//    }

    public void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();

        if (type == 0) {
            mController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim1);
        } else if (type == 1) {
            mController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim2);
        } else if (type == 2) {
            mController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim3);
        } else if (type == 3) {
            mController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim4);
        }
    }


    @Override
    public void onSwipeDelete(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PersonsAdapter.ViewHolder) {

            String name = mListPersons.get(viewHolder.getAdapterPosition()).getmName();
            Person person = mListPersons.get(viewHolder.getAdapterPosition());

            int deleteIndex = viewHolder.getAdapterPosition();
            mAdapter.removeItem(deleteIndex);
        }

    }

    @Override
    public void onDrag(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1, RecyclerView recyclerView) {
        int position_dragger = viewHolder.getAdapterPosition();
        int position_target = viewHolder1.getAdapterPosition();

        Collections.swap(mListPersons, position_dragger, position_target);
        mAdapter.notifyItemMoved(position_dragger, position_target);
    }
}
