package com.example.adminapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context mContext;
    private List<Insert2> mUploads;
    private OnItemClickListener mListener;
    public BookAdapter(Context context, List<Insert2> uploads) {
        mContext = context;
        mUploads = uploads;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_layout, parent, false);
        return new BookViewHolder(v);
    }
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Insert2 uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getBookName());
        Picasso.get()
                .load(uploadCurrent.getBookImage())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.textViewprice.setText(uploadCurrent.getBookPrice()+ "Taka");
        holder.textViewcat.setText(uploadCurrent.getCategoryId());

    }
    @Override
    public int getItemCount() {
        return mUploads.size();
    }
    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public TextView textViewprice;
        public TextView textViewcat;
        public ImageView imageView;
        public BookViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nametext);
            textViewprice=itemView.findViewById(R.id.prc2text);
            textViewcat=itemView.findViewById(R.id.ctegtext);

            imageView = itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            //MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");
            //doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (item.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onWhatEverClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
