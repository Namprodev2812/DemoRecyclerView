package com.asterisk.nam.demorecyclerview;

import android.support.v7.widget.RecyclerView;

public interface RecyclerItemTouchHelperListener {
    void onSwipeDelete(RecyclerView.ViewHolder viewHolder, int direction, int position);
    void onDrag(RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder viewHolder1,RecyclerView recyclerView);
}