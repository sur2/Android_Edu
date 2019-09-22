package com.pyong.tutorial_html_parsing_jsoup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.annotation.GlideModule;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<ItemObject> mList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_img;
        private TextView textView_title, textView_release, textView_reservation, texView_director;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_img = (ImageView) itemView.findViewById(R.id.imageView_img);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_release = (TextView) itemView.findViewById(R.id.textView_release);
            textView_reservation = (TextView) itemView.findViewById(R.id.textView_reservation);
            texView_director = (TextView) itemView.findViewById(R.id.textView_director);
        }
    }

    public MyAdapter(ArrayList<ItemObject> mlist) {

        this.mList = mlist;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
        holder.textView_title.setSelected(true);
        holder.textView_release.setText(String.valueOf(mList.get(position).getRelease()));
        holder.textView_release.setSelected(true);
        holder.textView_reservation.setText(String.valueOf(mList.get(position).getReservation()));
        holder.texView_director.setText(String.valueOf(mList.get(position).getDirector()));
        Glide.with(holder.itemView).load(mList.get(position).getImg_url()).apply(RequestOptions.overrideOf(300, 450))
                .into(holder.imageView_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(holder.itemView.getContext(), holder.textView_title.getText().toString()+" 눌렀다", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
