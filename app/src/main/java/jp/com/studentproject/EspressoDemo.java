package jp.com.studentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EspressoDemo extends AppCompatActivity {
    private TextView testEspresso;
    private Button btnEspresso;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso_demo);
        testEspresso = (TextView) findViewById(R.id.test_espresso);
        btnEspresso  = (Button) findViewById(R.id.btn_espresso);

        btnEspresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();
            }
        });
    }
    public void customDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("Thong Bao");
        dialog.setContentView(R.layout.custom_dialog_espresso);

        dialog.show();
    }
}
