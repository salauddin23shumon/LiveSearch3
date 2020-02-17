package com.s1s1s1.livesearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s1s1s1.livesearch.R;
import com.s1s1s1.livesearch.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=productList.get(position);
        holder.nameTV.setText(product.getName());
        holder.priceTV.setText(product.getPrice());
        holder.manufactureTv.setText(product.getManufacturer());
        Picasso.get().load(product.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTV, priceTV, manufactureTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.productIV);
            nameTV=itemView.findViewById(R.id.nameTV);
            priceTV=itemView.findViewById(R.id.priceTV);
            manufactureTv=itemView.findViewById(R.id.manufacturerTV);
        }
    }

    public void updateProduct(List<Product>productList){
        this.productList=productList;
        notifyDataSetChanged();
    }
}
