package com.example.mytask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context context;
    private List<Object> list;
    CardView cardView;

    public RecyclerAdapter(Context context, List<Object> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list_card, parent, false);
        MyViewHolder vh = new MyViewHolder(cardView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            JSONObject jsnobject = new JSONObject(list.get(position).toString());
            final String name=jsnobject.getString("name");
            final String description=jsnobject.getString("description");

            JSONObject ownerObj=jsnobject.getJSONObject("owner");
            final String imageUrl=ownerObj.getString("avatar_url");

            Log.d("onBindViewHolder",jsnobject.getString("name"));
            holder.title.setText(name.toUpperCase());
            holder.description.setText(description);
            Picasso.get().load(imageUrl).into(holder.image);
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ResponseData data=new ResponseData(name,description,imageUrl);
                    Intent intent=new Intent(context,ImageActivity.class).putExtra("DATA",data);
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,description;
        ImageView image;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            image=itemView.findViewById(R.id.image);
            card=itemView.findViewById(R.id.card);

        }
    }


}
