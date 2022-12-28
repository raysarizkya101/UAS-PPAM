package lat.pam.pizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import lat.pam.mypizza.R;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "lat.pam.myPizza.SecondActivity.extra.MESSAGE";
    public static final String EXTRA_STORE = "lat.pam.myPizza.SecondActivity.extra.STORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView namaPengguna = findViewById(R.id.nama_pemesan);
        TextView storeLocate = findViewById(R.id.store);
        Button btnMenu = findViewById(R.id.btn_menu);

        Bundle itemBundle = getIntent().getExtras();
        namaPengguna.setText(itemBundle.getString("username"));
        storeLocate.setText(itemBundle.getString("store"));

        btnMenu.setOnClickListener(view -> {
            Intent sendIntent = new Intent(SecondActivity.this, ThirdActivity.class);
            sendIntent.putExtra(EXTRA_MESSAGE, namaPengguna.getText().toString());
            sendIntent.putExtra(EXTRA_STORE, storeLocate.getText().toString());
            startActivity(sendIntent);
        });
    }
}