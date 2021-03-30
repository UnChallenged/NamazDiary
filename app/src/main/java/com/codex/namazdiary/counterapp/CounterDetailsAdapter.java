package com.codex.namazdiary.counterapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unchallenged.mynamaz.R;

import java.util.List;

public class CounterDetailsAdapter extends RecyclerView.Adapter<CounterDetailsAdapter.CounterViewHolder> {

    List<CounterDetails> counterDetailsList;
    Context context;
    CounterDatabase dbHelper;
    SQLiteDatabase db;

    public CounterDetailsAdapter(List<CounterDetails> counterDetailsList) {
        this.counterDetailsList=counterDetailsList;

    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.list_item, parent, false);
        CounterViewHolder viewHolder = new CounterViewHolder(iteView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CounterViewHolder holder, final int position) {
        CounterDetails userDetails = counterDetailsList.get(position);
        holder.tvNote.setText(userDetails.getNote());
        holder.tvCount.setText(userDetails.getCounts());
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CounterDetails counterDetails = counterDetailsList.get(position);
                final int countID = counterDetails.getUserId();
                dbHelper = new CounterDatabase(context);
                db = dbHelper.getWritableDatabase();
                PopupMenu menu = new PopupMenu(context, holder.ivMenu);

                menu.inflate(R.menu.popup_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                db.delete(CounterDatabaseContract.CounterDatabase.TABLE_NAME, CounterDatabaseContract.CounterDatabase._ID + " = " + countID, null);
                                notifyItemRangeChanged(position, counterDetailsList.size());
                                counterDetailsList.remove(position);
                                notifyItemRemoved(position);
                                db.close();
                                break;
                        }
                        return false;
                    }
                });
                menu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return counterDetailsList.size();
    }

    public class CounterViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote, tvCount;
        ImageView ivMenu;

        public CounterViewHolder(View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tv_note);
            tvCount = itemView.findViewById(R.id.tv_count);

            ivMenu = itemView.findViewById(R.id.iv_menu);
        }
    }


}
