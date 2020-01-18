package com.csiw.swiperecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csiw.swiperecyclerview.R;
import com.csiw.swiperecyclerview.model.Task;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Task> taskList;
    private int lastPosition = -1;
    public RecyclerViewAdapter(Context context){
        mContext = context;
        taskList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Task task = taskList.get(position);
        holder.tvTaskName.setText(task.getName());
        holder.tvTaskDesc.setText(task.getDesc());

        holder.imUserImage.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher_round));

        /* set Image Animation */
//        holder.imUserImage.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        /* set Animation Layouts */
//        holder.mParentLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_sacl_animation));

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTaskName;
        private TextView tvTaskDesc;
        private ImageView imUserImage;
        private RelativeLayout mParentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.task_name);
            tvTaskDesc = itemView.findViewById(R.id.task_desc);
            imUserImage = itemView.findViewById(R.id.user_image);
            mParentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}