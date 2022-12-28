package lat.pam.pizzapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lat.pam.mypizza.R;
import lat.pam.pizzapp.model.ProductModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> results;
    private OnAdapterListener listener;

    public ProductAdapter (List<ProductModel> results, OnAdapterListener listener) {
        this.results = results;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_third, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        ProductModel result = results.get(position);

        holder.tFoodName.setText(result.getFoodName());
        holder.tFoodDesc.setText(result.getDetails());
        holder.itemView.setOnClickListener(view -> listener.onClick(result));

        if (result.getId() == 1) {
            holder.imageView.setImageResource(R.drawable.papperoni_pizza);
        } else if (result.getId() == 2) {
            holder.imageView.setImageResource(R.drawable.spaghetti);
        } else if (result.getId() == 3) {
            holder.imageView.setImageResource(R.drawable.burger);
        } else if (result.getId() == 4) {
            holder.imageView.setImageResource(R.drawable.steak);
        } else if (result.getId() == 5) {
            holder.imageView.setImageResource(R.drawable.french_fries);
        } else {
            holder.imageView.setImageResource(R.drawable.papperoni_pizza);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tFoodName;
        TextView tFoodDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageProduct);
            tFoodName = itemView.findViewById(R.id.foodName);
            tFoodDesc = itemView.findViewById(R.id.foodDesc);
        }
    }

    public void setItem(List<ProductModel> item) {
        results.clear();
        results.addAll(item);
        notifyDataSetChanged();
    }

    public interface OnAdapterListener {
        void onClick(ProductModel result);
    }
}
