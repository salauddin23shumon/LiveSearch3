package com.s1s1s1.livesearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.s1s1s1.livesearch.adapter.MixedAdapter;
import com.s1s1s1.livesearch.adapter.ProductAdapter;
import com.s1s1s1.livesearch.models.Footer;
import com.s1s1s1.livesearch.models.Header;
import com.s1s1s1.livesearch.models.Product;
import com.s1s1s1.livesearch.models.RecyclerViewItem;
import com.s1s1s1.livesearch.models.Slider;
import com.s1s1s1.livesearch.networking.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> productList;
    private ProductAdapter adapter;
    private ProgressBar progressBar;
    private MixedAdapter mixedAdapter;
    private List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        progressBar = findViewById(R.id.progress);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList=new ArrayList<>();
//        adapter=new ProductAdapter(this, productList);
        mixedAdapter=new MixedAdapter(recyclerViewItems, ProductActivity.this);
        recyclerView.setAdapter(mixedAdapter);
//        recyclerView.setAdapter(adapter);
        getProduct();
        getProduct2("");
        getSlider();
    }

    private void getProduct() {
        Call<List<Product>>call = ApiClient.getInstance().getApiInterface().getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                productList=response.body();
                Log.e("getProduct", "onResponse: "+productList.size() );
                mixedAdapter.updateList(productList);
//                for (Product p: productList) {
//                    mixedAdapter.updateList(p);
//                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("", "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    public void getProduct2(String key){
        Call<List<Product>>call =ApiClient.getInstance().getApiInterface().getProducts2(key);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);
                productList=response.body();
                mixedAdapter.updateList(productList);
//                for (Product p: productList) {
//                    mixedAdapter.updateList(p);
//                }
//                adapter.updateProduct(productList);
                Log.e("getProduct2", "onResponse: "+productList.size() );
//                adapter.updateProduct(productList);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("", "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    public void getSlider(){
        Call <List<Slider>> call=ApiClient.getInstance().getApiInterface().getSlider();
        call.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
                mixedAdapter.updateSlider(response.body());
                Log.e("getSlider", "onResponse: "+response.body().size() );
            }

            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {
                Log.e("getSlider", "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("", "onQueryTextSubmit: called" );
                getProduct2( query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("", "onQueryTextChange: called" );
                getProduct2( newText);
                return false;
            }
        });
        return true;
    }

}
