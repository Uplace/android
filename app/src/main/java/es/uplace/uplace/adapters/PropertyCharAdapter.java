package es.uplace.uplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import es.uplace.uplace.R;

public class PropertyCharAdapter extends BaseAdapter {

    private Context context;

    public PropertyCharAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtX;
        if (view == null) {
            txtX = new TextView(context);
        } else {
            txtX = (TextView) view;
        }

        txtX.setText("Characteristic");
        return txtX;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgX;
        TextView txtX;

        public ViewHolder(View view) {
            super(view);
            imgX = view.findViewById(R.id.imgX);
            txtX = view.findViewById(R.id.txtX);
        }
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.property_x, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.txtX.setText("Characteristic");
//    }
//
//    @Override
//    public int getItemCount() {
//        return 5;
//    }
}
