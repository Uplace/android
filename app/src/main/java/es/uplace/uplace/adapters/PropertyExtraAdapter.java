package es.uplace.uplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.uplace.uplace.R;

public class PropertyExtraAdapter extends RecyclerView.Adapter<PropertyExtraAdapter.ViewHolder> {

    private Context context;

    public PropertyExtraAdapter(Context context) {
        this.context = context;
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

    @Override
    public PropertyExtraAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.property_x, parent, false);
        return new PropertyExtraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PropertyExtraAdapter.ViewHolder holder, int position) {
        holder.txtX.setText("Extra");
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
