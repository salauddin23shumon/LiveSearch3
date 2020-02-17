package com.s1s1s1.livesearch.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.s1s1s1.livesearch.R;
import com.s1s1s1.livesearch.models.Footer;
import com.s1s1s1.livesearch.models.Header;
import com.s1s1s1.livesearch.models.Product;
import com.s1s1s1.livesearch.models.RecyclerViewItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MixedAdapter extends RecyclerView.Adapter {

    //Declare List of Recyclerview Items
    private List<RecyclerViewItem> recyclerViewItems;
    //Header Item Type
    private static final int HEADER_ITEM = 0;
    //Footer Item Type
    private static final int FOOTER_ITEM = 1;
    ////Food Item Type
    private static final int PRODUCT_ITEM = 2;
    private Context context;

    public MixedAdapter(List<RecyclerViewItem> recyclerViewItems, Context context) {
        this.recyclerViewItems = recyclerViewItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        //Check fot view Type inflate layout according to it
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.custom_row_header, parent, false);
            return new HeaderHolder(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.custom_row_footer, parent, false);
            return new FooterHolder(row);
        } else if (viewType == PRODUCT_ITEM) {
            row = inflater.inflate(R.layout.product_row_item, parent, false);
            return new ProductItemHolder(row);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //Check holder instance to populate data  according to it
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            Header header = (Header) recyclerViewItem;
            //set data
            headerHolder.texViewHeaderText.setText(header.getHeaderText());
            headerHolder.textViewCategory.setText(header.getCategory());
            Picasso.get().load(header.getImageUrl()).into(headerHolder.imageViewHeader);

        } else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
            //set data
            footerHolder.texViewQuote.setText(footer.getQuote());
            footerHolder.textViewAuthor.setText(footer.getAuthor());
            Picasso.get().load(footer.getImageUrl()).into(footerHolder.imageViewFooter);

        } else if (holder instanceof ProductItemHolder) {
            ProductItemHolder productItemHolder = (ProductItemHolder) holder;

            Product product = (Product) recyclerViewItem;

            productItemHolder.nameTV.setText(product.getName());
            productItemHolder.priceTV.setText(product.getPrice());
            productItemHolder.manufactureTv.setText(product.getManufacturer());
            Picasso.get().load(product.getImage()).into(productItemHolder.imageView);

        }
    }

    @Override
    public int getItemViewType(int position) {
        //here we can set view type
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //if its header then return header item
        if (recyclerViewItem instanceof Header)
            return HEADER_ITEM;
            //if its Footer then return Footer item
        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;
            //if its FoodItem then return Food item
        else if (recyclerViewItem instanceof Product)
            return PRODUCT_ITEM;
        else
            return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }


    //Food item holder
    private class ProductItemHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTV, priceTV, manufactureTv;

        ProductItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productIV);
            nameTV = itemView.findViewById(R.id.nameTV);
            priceTV = itemView.findViewById(R.id.priceTV);
            manufactureTv = itemView.findViewById(R.id.manufacturerTV);
        }
    }

    //header holder
    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView texViewHeaderText, textViewCategory;
        ImageView imageViewHeader;

        HeaderHolder(View itemView) {
            super(itemView);
            texViewHeaderText = itemView.findViewById(R.id.texViewHeaderText);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            imageViewHeader = itemView.findViewById(R.id.imageViewHeader);
        }
    }

    //footer holder
    private class FooterHolder extends RecyclerView.ViewHolder {
        TextView texViewQuote, textViewAuthor;
        ImageView imageViewFooter;

        FooterHolder(View itemView) {
            super(itemView);
            texViewQuote = itemView.findViewById(R.id.texViewQuote);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            imageViewFooter = itemView.findViewById(R.id.imageViewFooter);
        }
    }

    public void updateList(List<Product> products) {


        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
                "https://cdn.pixabay.com/photo/2017/09/30/15/10/pizza-2802332_640.jpg");
        //add header
        recyclerViewItems.add(header);

        recyclerViewItems.addAll(products);

        Footer footer = new Footer("Your diet is a bank account. Good food choices are good investments.",
                "Bethenny Frankel", "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
        //add footer
        recyclerViewItems.add(footer);


        this.recyclerViewItems = recyclerViewItems;

        Log.e("", "updateList: " + recyclerViewItems.size());
        notifyDataSetChanged();
    }


    public void updateList(Product products) {


        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
                "https://cdn.pixabay.com/photo/2017/09/30/15/10/pizza-2802332_640.jpg");
        //add header
        recyclerViewItems.add(header);

        recyclerViewItems.add(products);

        Footer footer = new Footer("Your diet is a bank account. Good food choices are good investments.",
                "Bethenny Frankel", "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
        //add footer
        recyclerViewItems.add(footer);


        this.recyclerViewItems = recyclerViewItems;

        Log.e("", "updateList: " + recyclerViewItems.size());
        notifyDataSetChanged();
    }
}
