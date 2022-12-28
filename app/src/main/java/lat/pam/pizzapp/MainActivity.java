package lat.pam.pizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import lat.pam.mypizza.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner List = findViewById(R.id.dropdownCity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        List.setAdapter(adapter);

        Button btnSubmit = findViewById(R.id.btn_submit);
        EditText userName = findViewById(R.id.inputNama);

        btnSubmit.setOnClickListener(view -> {

            String storeLocate = List.getSelectedItem().toString();
            String namaPengguna = userName.getText().toString();

            if (storeLocate.equals("---Pilih Kota---") || namaPengguna.equals("")) {
                Toast.makeText(getApplication(), "Mohon Lengkapi Dahulu!", Toast.LENGTH_SHORT).show();
            } else {
                Bundle itemBundle = new Bundle();
                itemBundle.putString("store", storeLocate);
                itemBundle.putString("username", namaPengguna);


                Intent sendIntent = new Intent(MainActivity.this, SecondActivity.class);
                sendIntent.putExtras(itemBundle);
                startActivity(sendIntent);
            }
        });
    }
}

