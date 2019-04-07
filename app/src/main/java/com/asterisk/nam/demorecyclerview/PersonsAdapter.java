package com.asterisk.nam.demorecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    //ItemRecyclerView mItemRecyclerView;
    private Context mContext;
    private List<Person> mPersons;

    public PersonsAdapter(Context context, List<Person> persons) {
        this.mContext = context;
        this.mPersons = persons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder myViewHolder, final int i) {
        myViewHolder.bindData(i);
    }


    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public void removeItem(int position) {
        mPersons.remove(position);
        notifyDataSetChanged();
    }

    public void restoreItem(Person person, int position) {
        mPersons.add(position, person);
        notifyItemInserted(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //TextView mTextName, tv_age, tv_phone;
        public TextView mTextName, mText_Age, mTextPhone;
        public ImageView mImageAvarta;
        public CheckBox mCheckBoxMale, mCheckBoxFemale;
        public View mView;

        public ItemRecyclerView mItemRecyclerView;
        public RelativeLayout mViewBackground;
        public LinearLayout mViewForeground;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.mView = itemView;
            mTextName = itemView.findViewById(R.id.tv_name);
            mText_Age = itemView.findViewById(R.id.tv_age);
            mTextPhone = itemView.findViewById(R.id.tv_phone);
            mImageAvarta = itemView.findViewById(R.id.img_avarta);
            mViewBackground = itemView.findViewById(R.id.view_background);
            mViewForeground = itemView.findViewById(R.id.view_foreground);

            mCheckBoxMale = itemView.findViewById(R.id.cb_sex_male);
            mCheckBoxFemale = itemView.findViewById(R.id.cb_sex_female);
            //linearLayout = itemView.findViewById(R.id.linear_layout);
            //setOnItenClickListener((ItemRecyclerView) context);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemRecyclerView != null) {
                        mItemRecyclerView.onClick(itemView, getLayoutPosition());
                    }
                }
            });
        }

        public void setOnItenClickListener(ItemRecyclerView itemClickListener) {
            this.mItemRecyclerView = itemClickListener;
        }

        public void bindData(int position) {
            mTextName.setText("" + mPersons.get(position).getmName());
            mText_Age.setText("" + mPersons.get(position).getAge());
            mTextPhone.setText("" + mPersons.get(position).getPhone());
            mImageAvarta.setImageResource(mPersons.get(position).getmImage());

            setOnItenClickListener(new ItemRecyclerView() {
                @Override
                public void onClick(View view, int position) {

                    if (mCheckBoxMale.isChecked() && mCheckBoxFemale.isChecked() == false)
                        Toast.makeText(mContext, "male", Toast.LENGTH_SHORT).show();
                    else if (mCheckBoxFemale.isChecked() && mCheckBoxMale.isChecked() == false)
                        Toast.makeText(mContext, "female", Toast.LENGTH_SHORT).show();
                    else if (mCheckBoxMale.isChecked() == false && mCheckBoxFemale.isChecked() == false)
                        Toast.makeText(mContext, "gay", Toast.LENGTH_SHORT).show();
                    else if (mCheckBoxMale.isChecked() == true && mCheckBoxFemale.isChecked() == true)
                        Toast.makeText(mContext, "gay", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
