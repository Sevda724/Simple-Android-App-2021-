package com.example.simple_app;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    public static List<DataNameImg> dataList;
    private Context context;
    private ItemClickListener listener;


    public CustomAdapter(Context context,List<DataNameImg> dataList, ItemClickListener listener){
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        TextView txtStatus;
        TextView txtBirthday;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.NameOfPerson);
            coverImage = mView.findViewById(R.id.coverImage);
            txtStatus = mView.findViewById(R.id.StatusOfPerson);
            txtBirthday = mView.findViewById(R.id.BirthdayOfPerson);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getName());
        holder.txtStatus.setText(dataList.get(position).getStatus());
        holder.txtBirthday.setText(dataList.get(position).getBirthday());

        Picasso.get()
                .load(dataList.get(position).getImg())
                .resize(200,200)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

        holder.itemView.setOnClickListener(view -> {
            int id = dataList.get(position).getChar_id();
            listener.onItemClick(dataList.get(position));
        });

    }
    public interface ItemClickListener{
        void onItemClick(DataNameImg dataNameImg);
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
