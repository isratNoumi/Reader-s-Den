package com.example.pocketguard;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.annotations.NotNull;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class TouchHelper extends ItemTouchHelper.SimpleCallback {
   private MyCartAdapter myCartAdapter;

    public TouchHelper(MyCartAdapter myCartAdapter) {
        super(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
        this.myCartAdapter = myCartAdapter;
    }

    @Override
    public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull  RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            myCartAdapter.delectData(position);
            myCartAdapter.notifyDataSetChanged();
        }else {
            myCartAdapter.delectData(position);

        }

    }



    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX
            , float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c,recyclerView, viewHolder,dX,dY ,actionState,isCurrentlyActive)
                .addSwipeRightBackgroundColor(Color.RED)
                .addSwipeRightActionIcon(R.drawable.ic_baseline_delete)
                .addSwipeLeftBackgroundColor(Color.RED)
                .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete)
                .create()
                .decorate();


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
