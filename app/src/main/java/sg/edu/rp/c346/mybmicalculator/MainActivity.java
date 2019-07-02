package sg.edu.rp.c346.mybmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalc, btnReset;
    TextView tvDate, tvBMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etWeight.requestFocus();
        etHeight = findViewById(R.id.editTextHeight);

        btnCalc = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);

        tvDate = findViewById(R.id.textViewLastDate);
        tvBMI = findViewById(R.id.textViewLastBMI);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float weightf = Float.parseFloat(etWeight.getText().toString());
                float heighti = Float.parseFloat(etHeight.getText().toString());

                float bmi = weightf / (heighti * heighti);

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                tvDate.setText(String.format("Last Calculated Date: %s",datetime));
                tvBMI.setText(String.format("Last Calculated BMI: %.3f",bmi));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHeight.setText("");
                etWeight.setText("");
                tvDate.setText("Last Calculated Date: ");
                tvBMI.setText("Last Calculated BMI: ");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
