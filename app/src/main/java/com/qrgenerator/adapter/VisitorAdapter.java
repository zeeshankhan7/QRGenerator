package com.qrgenerator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qrgenerator.models.Visitor;
import com.qrgeneratorapp.max.R;

import java.util.List;


/**
 * Created by inmkhan021 on 7/17/2017.
 */

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder>{
        private Context mContext;
    private List<Visitor> visitorList;


    public VisitorAdapter(Context mContext, List<Visitor> visitorList) {
        this.mContext = mContext;
        this.visitorList = visitorList;
    }
        @Override
    public VisitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_row, parent, false);
            return new VisitorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VisitorViewHolder holder, int position) {
        Visitor visitor= visitorList.get(position);
        holder.visitorName.setText( visitor.getVisitorName());
        holder.visitorAdrress.setText(visitor.getPatientId());
        holder.visitorContactNo.setText(visitor.getVisitorMobileNo());

    }
    public void addItem(Visitor dataObj) {
        visitorList.add(dataObj);
        notifyDataSetChanged();
//        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        visitorList.remove(index);
        notifyItemRemoved(index);
    }


    @Override
    public int getItemCount() {
        return visitorList.size();
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder{
        private TextView visitorName;
        private TextView visitorAdrress;
        private TextView visitorContactNo;
        public VisitorViewHolder(View itemView) {
            super(itemView);
            visitorName = (TextView) itemView.findViewById(R.id.visitor_name);
            visitorAdrress = (TextView) itemView.findViewById(R.id.visitor_address);
            visitorContactNo = (TextView) itemView.findViewById(R.id.visitor_contact_no);
        }
    }
}
