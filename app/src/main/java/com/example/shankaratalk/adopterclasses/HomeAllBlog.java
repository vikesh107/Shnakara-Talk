package com.example.shankaratalk.adopterclasses;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shankaratalk.R;

import com.example.shankaratalk.modelclass.Create_user;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.like.LikeButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAllBlog  extends RecyclerView.Adapter<HomeAllBlog.View_holder> {

    Context context;
    Create_user create_user2;

    List<String> value,key ;

    public HomeAllBlog(Context context, List<String> value,List<String> key) {
        this.context = context;
        this.key = key;
        this.value = value;
    }

    @NonNull
    @Override
    public HomeAllBlog.View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.allblog_shows,parent,false);
        return new View_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAllBlog.View_holder holder, int position) {
        holder.blog.setText(value.get((value.size() - 1) - position).toString().trim());
        String uniqeid= key.get((key.size() - 1) - position);


        FirebaseDatabase.getInstance().getReference("All_users").child(uniqeid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                create_user2 = snapshot.getValue(Create_user.class);
                holder.name.setText(create_user2.getName());
                holder.textView7.setText("");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return value.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;
        private TextView name;
        private TextView blog;
        private LikeButton starButton;
        private TextView textView7;



        public View_holder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            blog = itemView.findViewById(R.id.blog);
            starButton = itemView.findViewById(R.id.star_button);
            textView7 = itemView.findViewById(R.id.textView7);
        }
    }
}
