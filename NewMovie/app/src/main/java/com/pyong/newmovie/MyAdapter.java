package com.pyong.newmovie;

import android.content.Intent;
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

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private ArrayList<ItemObject> mList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_img;
        private TextView textView_title, textView_release, textView_reservation,
                textView_genre, textView_rating, texView_director;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_img = (ImageView) itemView.findViewById(R.id.imageView_img);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_genre = (TextView) itemView.findViewById(R.id.textView_geren);
            textView_release = (TextView) itemView.findViewById(R.id.textView_release);
            textView_rating = (TextView) itemView.findViewById(R.id.textView_rating);
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
    public void onBindViewHolder(@NonNull final MyAdapter.ViewHolder holder, final int position) {

        holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
        holder.textView_title.setSelected(true);
        holder.textView_release.setText("개봉일: "+String.valueOf(mList.get(position).getRelease()));
        holder.textView_rating.setText("평점: "+String.valueOf(mList.get(position).getRating()));
        holder.textView_reservation.setText(String.valueOf(mList.get(position).getReservation()));
        holder.texView_director.setText("감독: "+String.valueOf(mList.get(position).getDirector()));
        holder.textView_genre.setText(String.valueOf(mList.get(position).getGenre()));
        Glide.with(holder.itemView).load(mList.get(position).getImg_url()).apply(RequestOptions.overrideOf(300, 450))
                .into(holder.imageView_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), holder.textView_title.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent eIntent = new Intent(holder.itemView.getContext(), DisplayMovie.class);
                eIntent.putExtra("name", mList.get(position).getTitle());
                eIntent.putExtra("release", mList.get(position).getRelease());
                eIntent.putExtra("rating", mList.get(position).getRating());
                eIntent.putExtra("director", mList.get(position).getDirector());
                eIntent.putExtra("genre", mList.get(position).getGenre());

                holder.itemView.getContext().startActivity(eIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
