package com.example.pocketguard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;



import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    private CartActivity cartActivity;
    // Context context;
    private List<MyCartModel> list;

    // int totalPrice =0;

    private FirebaseFirestore firestore= FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();;

    public MyCartAdapter(CartActivity cartActivity, List<MyCartModel> list) {
        this.cartActivity = cartActivity;
        this.list = list;

    }

    public void delectData(int position){
        MyCartModel item  = list.get(position);
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart")
                .document(item.getDocumentId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(cartActivity,"Item Deleted" ,Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(cartActivity, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }

    public void notifyRemoved(int position){
        list.remove(position);
        notifyItemRemoved(position);
        // cartActivity.showBook();



    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(cartActivity).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getBookName());
        holder.price.setText(list.get(position).getBookPrice());
        holder.date.setText(list.get(position).getCurrentDate());
        holder.time.setText(list.get(position).getCurrentTime());
        holder.quantity.setText(list.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));



        //   holder.delectItem.setOnClickListener(new View.OnClickListener() {
         /*  @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(list.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Item Delected" ,Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context,"Error" +task.getException().getMessage() ,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });*/

        //Passing total cost to Cart Activity
      /*  totalPrice= totalPrice +list.get(position).getTotalPrice();
        Intent intent =new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalPrice);
        LocalBroadcastManager.getInstance(cartActivity).sendBroadcast(intent); ///n
*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,date,time,quantity,totalPrice;
        // ImageView delectItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.book_name);
            price=itemView.findViewById(R.id.book_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            quantity=itemView.findViewById(R.id.total_quantity);
            totalPrice=itemView.findViewById(R.id.total_price);
            //  delectItem = itemView.findViewById(R.id.delect);

        }
    }
}
