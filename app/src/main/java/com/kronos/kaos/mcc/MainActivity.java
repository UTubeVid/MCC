package com.kronos.kaos.mcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("LOC estimator");
        final Button button = findViewById(R.id.cal);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                RadioGroup radioGroup = findViewById(R.id.rgPlex);
                String selectedtext = "";
                try {
                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = radioGroup.findViewById(radioButtonID);
                    selectedtext = (String) radioButton.getText();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Select a complexity", Toast.LENGTH_SHORT).show();
                }
                float a = 0.0f, b = 0.0f, c = 0.0f, d = 0.0f;
                switch(selectedtext){
                    case "Simple":    a = 2.4f;
                                        b = 1.05f;
                                        c = 2.5f;
                                        d = 0.38f;
                                        break;
                    case "Average":   a = 3.0f;
                                        b = 1.12f;
                                        c = 2.5f;
                                        d = 0.35f;
                                        break;
                    case "Complex":   a = 3.6f;
                                        b = 1.20f;
                                        c = 2.5f;
                                        d = 0.32f;
                                        break;
                }
                EditText klocin = findViewById(R.id.kloc);
                if(klocin.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Enter KLOC",Toast.LENGTH_SHORT).show();
                else if (a != 0.0f){
                    float kloc = Float.parseFloat(klocin.getText().toString());
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    TextView eff = findViewById(R.id.eff);
                    double effort = a * Math.pow(kloc,b);
                    eff.setText("Effort = " + nf.format(effort) + " person months");
                    TextView dt = findViewById(R.id.dt);
                    double devtime = c * Math.pow(effort,d);
                    dt.setText("Development time = " + nf.format(devtime) + " months");
                }
            }
        });
    }
}
