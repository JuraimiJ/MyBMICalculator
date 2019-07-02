package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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


                // Step 1b: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1d: Add the key-value pair
                prefEdit.putString("date",datetime);
                prefEdit.putFloat("bmi", bmi);

                // Step 1e: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();
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
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String dates = prefs.getString("date", "");
        Float bmif = prefs.getFloat("bmi", 0.00f);

        // Step 2c: Update the UI element with the value
        tvDate.setText(String.format("Last Calculated Date: %s",dates));
        tvBMI.setText(String.format("Last Calculated BMI: %.3f",bmif));
    }
}
