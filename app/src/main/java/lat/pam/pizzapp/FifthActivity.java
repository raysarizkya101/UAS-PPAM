package lat.pam.pizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import lat.pam.mypizza.R;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        TextView namaPemesan = findViewById(R.id.nama_pemesan);
        TextView store = findViewById(R.id.isi_toko);
        TextView orderan = findViewById(R.id.isiOrder);

        Bundle itemBundle = getIntent().getExtras();
        namaPemesan.setText(itemBundle.getString("name"));
        store.setText(itemBundle.getString("store"));
        orderan.setText(String.format("%s %s", itemBundle.getString("title"), getString(R.string.pesanan)));

        RadioGroup pengirimanMethod = findViewById(R.id.radioGrup);

        Button btn_done = findViewById(R.id.btn_done);

        btn_done.setOnClickListener(view -> {
            int selectedID = pengirimanMethod.getCheckedRadioButtonId();

            RadioButton radioButton = findViewById(selectedID);

            if (radioButton != null) {
                String item = "Terima kasih " + namaPemesan.getText().toString() + " sudah memesan ditoko kami, pesanan " + itemBundle.getString("title") + " anda dikirim menggunakan " + radioButton.getText();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan pilih metode pengiriman!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}