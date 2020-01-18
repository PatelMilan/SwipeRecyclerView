package com.csiw.swiperecyclerview;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csiw.swiperecyclerview.adapter.RecyclerViewAdapter;
import com.csiw.swiperecyclerview.fragment.BottomSheetFragment;
import com.csiw.swiperecyclerview.interfaces.OnRowClickListener;
import com.csiw.swiperecyclerview.interfaces.OnSwipeOptionsClickListener;
import com.csiw.swiperecyclerview.model.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerviewAdapter;
    private RecyclerTouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerviewAdapter = new RecyclerViewAdapter(this);

        final List<Task> taskList = new ArrayList<>();
        Task task = new Task("Buy Dress", "Buy Dress at Shoppershop for coming functions");
        taskList.add(task);
        task = new Task("Go For Walk", "Wake up 6AM go for walking");
        taskList.add(task);
        task = new Task("Office Work", "Complete the office works on Time");
        taskList.add(task);
        task = new Task("watch Repair", "Give watch to service center");
        taskList.add(task);
        task = new Task("Recharge Mobile", "Recharge for 10$ to my **** number");
        taskList.add(task);
        task = new Task("Read book", "Read android book completely");
        taskList.add(task);
        recyclerviewAdapter.setTaskList(taskList);
        recyclerView.setAdapter(recyclerviewAdapter);

        touchListener = new RecyclerTouchListener(this, recyclerView);
        touchListener
                .setClickable(new OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(getApplicationContext(), taskList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeAble(true)
                .setSwipeOptionViews(R.id.delete_task, R.id.edit_task, R.id.copy_task)
                .setSwipeAble(R.id.rowFG, R.id.rowBG, new OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        switch (viewID) {
                            case R.id.delete_task:
                                taskList.remove(position);
                                recyclerviewAdapter.setTaskList(taskList);
                                break;
                            case R.id.edit_task:
                                showBottomSheetDialog();
                                break;
                            case R.id.copy_task:
                                taskList.add(position, taskList.get(position));
                                recyclerviewAdapter.setTaskList(taskList);
                                break;

                        }
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(touchListener);
    }

    private void showBottomSheetDialog(){
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
