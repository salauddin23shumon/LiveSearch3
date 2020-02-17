package com.s1s1s1.livesearch.networking;


import com.s1s1s1.livesearch.models.Contact;
import com.s1s1s1.livesearch.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {

    @GET("getcontacts.php")
    Call<List<Contact>> getContact(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );


    @GET("getproducts.php")
    Call<List<Product>> getProducts();

    @GET("getproducts2.php")
    Call<List<Product>> getProducts2( @Query("key") String keyword);
}
