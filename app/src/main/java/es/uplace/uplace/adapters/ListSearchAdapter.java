package es.uplace.uplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.uplace.uplace.R;

public class ListSearchAdapter extends RecyclerView.Adapter<ListSearchAdapter.ViewHolder> {

//    private PropertyList propertyList;

    public ListSearchAdapter(/*PropertyList propertyList*/) {
//        this.propertyList = propertyList;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Property property = propertyList.getProperties()[position];
        holder.txtPropertyName.setText("Test Nombre Largo, 1, 2, 3");
        holder.txtLocation.setText("Test Location: c/Lorem ipsum 123, Prpl. 4 08001, Barcelona, Spain");
        holder.txtPrice.setText("9999€");
        holder.txtType.setText("Test Type");
        holder.txtYearOfConst.setText("2018");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public interface OnItemClickListener {
        void itemClicked(View view);
    }

    private OnItemClickListener listener;

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
