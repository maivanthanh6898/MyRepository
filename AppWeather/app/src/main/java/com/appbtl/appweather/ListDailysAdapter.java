package com.appbtl.appweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appbtl.appweather.model.ListDailys;

import java.util.List;

public class ListDailysAdapter extends RecyclerView.Adapter<ListDailysAdapter.recylerHolder> {
    ListDailys listDailys;
    Context context;
    public ListDailysAdapter(ListDailys listDailys, Context context) {
        this.listDailys = listDailys;
        this.context = context;
    }

    @NonNull
    @Override
    public recylerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new recylerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recylerHolder holder, int position) {
        double ma = listDailys.getList().get(position).getTemp().getMax();
        double mi = listDailys.getList().get(position).getTemp().getMin();
        int max=(int)ma;
        int min=(int)mi;
        holder.tMax.setText(max+"°C");
        holder.tMin.setText(min+"°C");
    }

    @Override
    public int getItemCount() {
        return listDailys.getList().size();
    }

    protected class recylerHolder extends RecyclerView.ViewHolder{
        ImageView imgTT;
        TextView tdate,txtTT,tMax,tMin;
        public recylerHolder(@NonNull View itemView) {
            super(itemView);
            tdate = (TextView)itemView.findViewById(R.id.tDate);
            txtTT=(TextView)itemView.findViewById(R.id.txtTT);
            tMax =(TextView)itemView.findViewById(R.id.tMax);
            tMin = (TextView)itemView.findViewById(R.id.tMin);
        }
    }
}
