package com.example.shankaratalk.adopterclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shankaratalk.R;

import java.util.List;

public class ForMyBlog extends RecyclerView.Adapter<ForMyBlog.View_blog_holder> {
    Context context;
    List<String> Data_for_me;

    public ForMyBlog(Context context, List<String> data_for_me) {
        this.context = context;
        Data_for_me = data_for_me;
    }

    @NonNull
    @Override
    public ForMyBlog.View_blog_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybloges_layout,parent,false);
        return  new View_blog_holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ForMyBlog.View_blog_holder holder, int position) {

         holder.myblogesShows.setText(Data_for_me.get(position));
    }

    @Override
    public int getItemCount() {
        return Data_for_me.size();
    }

    public class View_blog_holder extends RecyclerView.ViewHolder {
        private TextView myblogesShows;
        private ImageButton imageButton;



        public View_blog_holder(@NonNull View itemView) {
            super(itemView);
            myblogesShows = itemView.findViewById(R.id.mybloges_shows);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }
}
