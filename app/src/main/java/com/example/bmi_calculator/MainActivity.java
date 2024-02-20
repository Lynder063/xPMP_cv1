package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextHeight, editTextWeight, editTextAge;
    RadioButton radioButtonMale, radioButtonFemale;
    Button buttonCalculate;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
        editTextAge = findViewById(R.id.age);
        radioButtonMale = findViewById(R.id.male);
        radioButtonFemale = findViewById(R.id.female);
        buttonCalculate = findViewById(R.id.button);
        result = findViewById(R.id.result);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();
        String ageStr = editTextAge.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Vypln vše!", Toast.LENGTH_SHORT).show();
            return;
        }

        float height = Float.parseFloat(heightStr) / 100;
        float weight = Float.parseFloat(weightStr);
        int age = Integer.parseInt(ageStr);

        float bmi = calculateBMIValue(weight, height);

        String gender = radioButtonMale.isChecked() ? "Male" : "Female";

        displayBMI(bmi, age, gender);
    }

    private float calculateBMIValue(float weight, float height) {
        return weight / (height * height);
    }

    private void displayBMI(float bmi, int age, String gender) {
        String category;

        if (gender.equals("Male")) {
            if (age >= 18 && age <= 24) {
                if (bmi < 17.5) category = "Podváha";
                else if (bmi >= 17.5 && bmi < 24.9) category = "Optimální vaha";
                else if (bmi >= 24.9 && bmi < 30) category = "Nadváha";
                else category = "Obezita";
            } else {
                if (bmi < 18.5) category = "Podváha";
                else if (bmi >= 18.5 && bmi < 25) category = "Optimální vaha";
                else if (bmi >= 25 && bmi < 30) category = "Nadváha";
                else category = "Obezita";
            }
        } else {
            if (age >= 18 && age <= 24) {
                if (bmi < 17.5) category = "Podváha";
                else if (bmi >= 17.5 && bmi < 24.9) category = "Optimální vaha";
                else if (bmi >= 24.9 && bmi < 30) category = "Overweight";
                else category = "Obezita";
            } else {
                if (bmi < 18.5) category = "Podváha";
                else if (bmi >= 18.5 && bmi < 24.9) category = "Optimální vaha";
                else if (bmi >= 24.9 && bmi < 30) category = "Nadváha";
                else category = "Obezita";
            }
        }

        result.setText(String.format("BMI: %.2f\n %s", bmi, category));

    }
}