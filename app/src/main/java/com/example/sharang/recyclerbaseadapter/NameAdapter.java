package com.example.sharang.recyclerbaseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sharang on 21/8/16.
 */

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder>{

    private Context mContext;
    RecyclerView recyclerView;
    ArrayList<String> mNames = new ArrayList<>();
    private Random mRandom = new Random();

    public NameAdapter(Context context,RecyclerView recyclerView) {
        mContext = context;
        this.recyclerView=recyclerView;
        for (int i = 0; i < 5; i++) {
            mNames.add(getRandomName());
            notifyDataSetChanged();
        }
        Log.d("array",""+mNames.size());
    }

    private String getRandomName() {
        String[] names = new String[]{
                "Hannah", "Emily", "Sarah", "Madison", "Brianna",
                "Kaylee", "Kaitlyn", "Hailey", "Alexis", "Elizabeth",
                "Michael", "Jacob", "Matthew", "Nicholas", "Christopher",
                "Joseph", "Zachary", "Joshua", "Andrew", "William"
        };
        return names[mRandom.nextInt(names.length)];
    }

    public void addName(){
        mNames.add(getRandomName());
        Log.d("add",""+mNames.size());
       //notifyDataSetChanged();
        notifyItemInserted(0);
        recyclerView.getLayoutManager().scrollToPosition(0);
    }
    public void remove(int pos){
        mNames.remove(pos);
        Log.d("remove",""+mNames.size());
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.name_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name=mNames.get(position);
        holder.nameText.setText(name);
        holder.posText.setText(String.format("I am #%d", (position+1)));
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;
        private TextView posText;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    remove(getAdapterPosition());
                    return true;
                }
            });
            nameText=(TextView)itemView.findViewById(R.id.n_view);
            posText=(TextView)itemView.findViewById(R.id.position_view);
        }
    }
}
