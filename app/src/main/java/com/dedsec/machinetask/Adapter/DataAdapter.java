package com.dedsec.machinetask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dedsec.machinetask.Model.Data;
import com.dedsec.machinetask.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mCtx;
    private List<Data> dataList;
    private DataAdapterListener listener;

    public DataAdapter(Context mCtx, List<Data> dataList, DataAdapterListener listener) {
        this.mCtx = mCtx;
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data data = dataList.get(position);

        Glide.with(mCtx)
                .load(data.getUrl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.animate_rotate)
                .into(holder.imageView);

//        String author = "By: " + data.getAuthor();
//        holder.textView.setText(author);

        listener.onGetData(dataList.size());
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public DataViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            //textView = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface DataAdapterListener {
        void onGetData(int size);
    }
}
