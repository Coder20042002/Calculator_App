package easy.tuto.easycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        TextView txtMessage=findViewById(R.id.txtMessage);
        String[] meesage=getIntent().getStringArrayExtra("keyResult");
        for (int i=0;i<meesage.length-1;i++) {
            txtMessage.setText(meesage[i]);
        }

    }

}