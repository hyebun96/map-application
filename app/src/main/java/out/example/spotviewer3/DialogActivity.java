package out.example.spotviewer3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("정보");
        setContentView(R.layout.activity_dialog);

        Intent intent = getIntent();

        text = findViewById(R.id.dialog_text);

        Shop shop = intent.getParcelableExtra("shop");



        text.setText("매장이름: " + shop.shopName
                + "\n\n매장타입: " + shop.shopType.substring(1)
                + "\n\n주소: " + shop.address
                + "\n\n번호: " + shop.tel);
    }
}
