package com.example.shankaratalk.adopterclasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shankaratalk.R;
import com.example.shankaratalk.modelclass.Create_user;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatrecycaler extends RecyclerView.Adapter<chatrecycaler.VIewholder2> {
   List<Create_user> Data;

   Context context;

    public chatrecycaler(List<Create_user> data,Context context) {
        Data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public chatrecycaler.VIewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.totaluserforchat,parent,false);
        return new VIewholder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatrecycaler.VIewholder2 holder, int position) {
        holder.nametext.setText(Data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class VIewholder2 extends RecyclerView.ViewHolder {
        private CardView forcard;
        private CircleImageView profileImage;
        private TextView nametext;


        public VIewholder2(@NonNull View itemView) {
            super(itemView);

            forcard = itemView.findViewById(R.id.forcard);
            profileImage = itemView.findViewById(R.id.profile_image);
            nametext = itemView.findViewById(R.id.nametext);
        }
    }
}
