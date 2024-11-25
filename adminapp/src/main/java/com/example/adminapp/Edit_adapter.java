package com.example.adminapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;



public class Edit_adapter extends FirebaseRecyclerAdapter<Insert2,Edit_adapter.myviewholder> {
    public Edit_adapter(@NonNull FirebaseRecyclerOptions<Insert2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final Insert2 model) {
        holder.name.setText(model.getBookName());
        holder.price.setText(model.getBookPrice());
        holder.category.setText(model.getCategoryId());

        // Glide.with(holder.img.getContext()).load(model.getBookImage()).into(holder.img);
        Picasso.get().load(model.getBookImage()).into(holder.img);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final  DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true, 1350)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText purl = myview.findViewById(R.id.uimgurl);
                final EditText name = myview.findViewById(R.id.uname);
                final EditText price = myview.findViewById(R.id.uprc);
                final EditText category = myview.findViewById(R.id.ucateg);
                final EditText des = myview.findViewById(R.id.udes);
                Button submit = myview.findViewById(R.id.usubmit);

                purl.setText(model.getBookImage());
                name.setText(model.getBookName());
                price.setText(model.getBookPrice()+ "Taka");
                category.setText(model.getCategoryId());
                des.setText(model.getDescription());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("bookImage", purl.getText().toString());
                        map.put("bookName", name.getText().toString());
                        map.put("bookPrice", price.getText().toString());
                        map.put("categoryId", category.getText().toString());
                        map.put("description",des.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("category").child(model.getCategoryId()).child("data")
                                .child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                        FirebaseDatabase.getInstance().getReference().child("Admincategory")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_layout, parent, false);
        return new myviewholder(view);
    }



    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        Button edit;
        TextView name, price,category;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            price = (TextView) itemView.findViewById(R.id.prctext);
            category = (TextView) itemView.findViewById(R.id.catgtext);
            edit=(Button) itemView.findViewById(R.id.editbtn);


        }
    }
}