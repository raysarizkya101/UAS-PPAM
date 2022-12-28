package lat.pam.pizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import lat.pam.mypizza.R;

public class FourthActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "lat.pam.myPizza.FourthActivity.extra.MESSAGE";
    public static final String EXTRA_STORE = "lat.pam.myPizza.FourthActivity.extra.STORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        TextView name = findViewById(R.id.nama_user);
        TextView store = findViewById(R.id.store);
        TextView title = findViewById(R.id.detalTitle);
        TextView price = findViewById(R.id.price);
        TextView desc = findViewById(R.id.detail_desc);
        ImageView image = findViewById(R.id.detail_image);

        Button btn_order = findViewById(R.id.btn_order);
        Button btn_back = findViewById(R.id.btn_back);

        Bundle itemBundle = getIntent().getExtras();
        name.setText(itemBundle.getString("name"));
        store.setText(itemBundle.getString("store"));
        title.setText(itemBundle.getString("title"));
        price.setText(itemBundle.getString("price"));
        desc.setText(itemBundle.getString("desc"));


        if (title.getText().toString().equals(getString(R.string.image1_title))) {
            image.setImageResource(R.drawable.main_2);
        } else if (title.getText().toString().equals(getString(R.string.image2_title))) {
            image.setImageResource(R.drawable.spaghetti_detail);
        } else if (title.getText().toString().equals(getString(R.string.image3_title))) {
            image.setImageResource(R.drawable.burger_detail);
        } else if (title.getText().toString().equals(getString(R.string.image4_title))) {
            image.setImageResource(R.drawable.steak_detail);
        } else {
            image.setImageResource(R.drawable.french_fries_detail);
        }

        btn_back.setOnClickListener(view -> {
            Intent sendIntent = new Intent(FourthActivity.this, ThirdActivity.class);
            sendIntent.putExtra(EXTRA_MESSAGE, name.getText().toString());
            sendIntent.putExtra(EXTRA_STORE, store.getText().toString());
            startActivity(sendIntent);
        });

        btn_order.setOnClickListener(view -> {
            Bundle sendBundle = new Bundle();
            sendBundle.putString("name", name.getText().toString());
            sendBundle.putString("store", store.getText().toString());
            sendBundle.putString("title", title.getText().toString());

            Intent sendIntent = new Intent(FourthActivity.this, FifthActivity.class);
            sendIntent.putExtras(sendBundle);
            startActivity(sendIntent);
        });
    }
}