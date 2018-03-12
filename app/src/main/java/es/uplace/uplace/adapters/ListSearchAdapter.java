package es.uplace.uplace.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.uplace.uplace.R;
import es.uplace.uplace.domain.Property;

public class ListSearchAdapter extends RecyclerView.Adapter<ListSearchAdapter.ViewHolder> {

    List<Property> properties;

    Resources resources;

    public ListSearchAdapter(List<Property> properties) {
        this.properties = properties;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //        Property property;
        ImageView imgProperty, imgFav, imgChar1, imgChar2, imgChar3;
        TextView txtPropertyName, txtLocation, txtPrice, txtType, txtYearOfConst,
                txtChar1, txtChar2, txtChar3;

        public ViewHolder(View view) {
            super(view);
            imgProperty = view.findViewById(R.id.imgProperty);
            imgFav = view.findViewById(R.id.imgFav);
            imgChar1 = view.findViewById(R.id.imgChar1);
            imgChar2 = view.findViewById(R.id.imgChar2);
            imgChar3 = view.findViewById(R.id.imgChar3);
            txtPropertyName = view.findViewById(R.id.txtPropertyName);
            txtLocation = view.findViewById(R.id.txtLocation);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtType = view.findViewById(R.id.txtType);
            txtYearOfConst = view.findViewById(R.id.txtYearOfConst);
            txtChar1 = view.findViewById(R.id.txtChar1);
            txtChar2 = view.findViewById(R.id.txtChar2);
            txtChar3 = view.findViewById(R.id.txtChar3);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.itemClicked(view);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_property, parent, false);
        resources = parent.getResources();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Property property = properties.get(position);
        holder.txtPropertyName.setText(property.getTitle());
        holder.txtLocation.setText(property.getDescription());
        holder.txtPrice.setText(resources.getString(R.string.str_price, property.getPrice()));
        holder.txtType.setText(property.getPropertyType());
        holder.txtYearOfConst.setText(property.getYearConstruction());
    }

    @Override
    public int getItemCount() {
        return properties.size() - 1;
    }

    public interface OnItemClickListener {
        void itemClicked(View view);
    }

    private OnItemClickListener listener;

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
