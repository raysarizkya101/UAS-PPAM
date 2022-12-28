package lat.pam.pizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lat.pam.mypizza.R;
import lat.pam.pizzapp.adapter.ProductAdapter;
import lat.pam.pizzapp.api.JsonApi;
import lat.pam.pizzapp.model.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdActivity extends AppCompatActivity {

    List<ProductModel> results = new ArrayList<>();
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String BASE_URL = "https://retoolapi.dev/StWODX/";

        TextView namaPengguna = findViewById(R.id.nama_pemesan);
        TextView store_name = findViewById(R.id.store);

        Intent item = getIntent();

        String data = item.getStringExtra(SecondActivity.EXTRA_MESSAGE);
        String data2 = item.getStringExtra(FourthActivity.EXTRA_MESSAGE);
        String store = item.getStringExtra(SecondActivity.EXTRA_STORE);
        String store2 = item.getStringExtra(FourthActivity.EXTRA_STORE);

        if (data != null) {
            namaPengguna.setText(data);
            store_name.setText(store);
        } else {
            namaPengguna.setText(data2);
            store_name.setText(store2);
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

//        LinearLayout item1 = findViewById(R.id.item1);
//        LinearLayout item2 = findViewById(R.id.item2);
//        LinearLayout item3 = findViewById(R.id.item3);
//        LinearLayout item4 = findViewById(R.id.item4);

        String name = namaPengguna.getText().toString();
        String storeName = store_name.getText().toString();

        recyclerView = findViewById(R.id.productList);
        productAdapter = new ProductAdapter(results, new ProductAdapter.OnAdapterListener() {
            @Override
            public void onClick(ProductModel result) {
                Bundle itemBundle = new Bundle();
                itemBundle.putString("name", name);
                itemBundle.putString("store", storeName);
                itemBundle.putString("title", result.getFoodName());
                itemBundle.putString("price", result.getPrice());
                itemBundle.putString("desc", result.getDetails());

                Intent sendIntent = new Intent(ThirdActivity.this, FourthActivity.class);
                sendIntent.putExtras(itemBundle);
                startActivity(sendIntent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

        Call<List<ProductModel>> call = jsonApi.getProduct();

        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code "+response.code(), Toast.LENGTH_SHORT).show();
                }

                List<ProductModel> results = response.body();
                productAdapter.setItem(results);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        item1.setOnClickListener(view -> {
//            TextView title = findViewById(R.id.papperoniTitle);
//            String price = getString(R.string.papperoni_price);
//            String desc = getString(R.string.papperoni_detail_desc);
//
//            Bundle itemBundle = new Bundle();
//            itemBundle.putString("name", name);
//            itemBundle.putString("store", storeName);
//            itemBundle.putString("title", title.getText().toString());
//            itemBundle.putString("price", price);
//            itemBundle.putString("desc", desc);
//
//            Intent sendIntent = new Intent(ThirdActivity.this, FourthActivity.class);
//            sendIntent.putExtras(itemBundle);
//            startActivity(sendIntent);
//        });

//        item2.setOnClickListener(view -> {
//            TextView title = findViewById(R.id.spaghettiTitle);
//            String price = getString(R.string.spaghetti_price);
//            String desc = getString(R.string.spaghetti_detail_desc);
//
//            Bundle itemBundle = new Bundle();
//            itemBundle.putString("name", name);
//            itemBundle.putString("store", storeName);
//            itemBundle.putString("title", title.getText().toString());
//            itemBundle.putString("price", price);
//            itemBundle.putString("desc", desc);
//
//            Intent sendIntent = new Intent(ThirdActivity.this, FourthActivity.class);
//            sendIntent.putExtras(itemBundle);
//            startActivity(sendIntent);
//        });
//
//        item3.setOnClickListener(view -> {
//            TextView title = findViewById(R.id.burgerTitle);
//            String price = getString(R.string.burger_price);
//            String desc = getString(R.string.burger_detail_desc);
//
//            Bundle itemBundle = new Bundle();
//            itemBundle.putString("name", name);
//            itemBundle.putString("store", storeName);
//            itemBundle.putString("title", title.getText().toString());
//            itemBundle.putString("price", price);
//            itemBundle.putString("desc", desc);
//
//            Intent sendIntent = new Intent(ThirdActivity.this, FourthActivity.class);
//            sendIntent.putExtras(itemBundle);
//            startActivity(sendIntent);
//        });
//
//        item4.setOnClickListener(view -> {
//            TextView title = findViewById(R.id.steakTitle);
//            String price = getString(R.string.steak_price);
//            String desc = getString(R.string.steak_detail_desc);
//
//            Bundle itemBundle = new Bundle();
//            itemBundle.putString("name", name);
//            itemBundle.putString("store", storeName);
//            itemBundle.putString("title", title.getText().toString());
//            itemBundle.putString("price", price);
//            itemBundle.putString("desc", desc);
//
//            Intent sendIntent = new Intent(ThirdActivity.this, FourthActivity.class);
//            sendIntent.putExtras(itemBundle);
//            startActivity(sendIntent);
//        });
    }
}