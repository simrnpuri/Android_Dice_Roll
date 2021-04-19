package com.simrnpuri.rolldice_simran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private int sides, sideUp, result1, result2;
    private boolean doubleDigits, twoDice, zeroToN;

    private ImageButton ButtonRoll;
    private RadioButton radioSix;
    private TextView textViewOne;
    private TextView textViewTwo;
    private RadioGroup radioGroup;
    private EditText editTextSides;
    private Switch switch2Dice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonRoll = findViewById(R.id.imageButtonRoll);
        radioSix = findViewById(R.id.radioButton6);
        radioGroup = findViewById(R.id.radioGroup);
        textViewOne = findViewById(R.id.textViewFirst);
        textViewTwo = findViewById(R.id.textViewSecond);
        editTextSides = findViewById(R.id.editTextSides);
        switch2Dice = findViewById(R.id.switch1);


        ButtonRoll.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        switch2Dice.setOnCheckedChangeListener(this);
        editTextSides.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextSides.getText().toString() != ""){
                    ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).setChecked(false);
                    sides = Integer.parseInt(editTextSides.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //this is called on OnCreate() for setting the default values
        resetDefault();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButtonRoll:
                 displayResult();
                break;
        }
    }

    //This method resets the view and values to default
    private void resetDefault() {
        sides = 6;
        radioSix.setChecked(true);
        textViewOne.setText("?");
        textViewTwo.setText("?");
        result1=0;
        result2=0;
        doubleDigits = false;
        twoDice=false;
        //Toast.makeText(getApplicationContext(),"Default 6 sided Die selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        doubleDigits = false;
        zeroToN = false;
        switch (checkedId) {
            case R.id.radioButton4:
                sides = 4;
                break;
            case R.id.radioButton6:
                sides = 6;
                break;
            case R.id.radioButton8:
                sides = 8;
                break;
            case R.id.radioButton10:
                sides = 10;
                zeroToN = true;
                Toast.makeText(getApplicationContext(),"This die fetches values 0-9", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton10s:
                sides = 10;
                doubleDigits = true;
                break;
            case R.id.radioButton12:
                sides = 12;
                break;
            case R.id.radioButton20:
                sides = 20;
                break;
        }
    }

    public int roll(){
        sideUp = (int) (Math.random() * sides) + 1;
        if(doubleDigits == true){
            sideUp = Integer.parseInt(sideUp+"0");
        }
        return sideUp;
    }

    public int rollZeroToN(){
        sideUp = (int) (Math.random() * sides) ;
        return sideUp;
    }

    public void displayResult(){
        if(twoDice == false){
            if(zeroToN == true){
                result1 = rollZeroToN();
                result2 = rollZeroToN();
            }
            else{
                result1 = roll();
                result2 = roll();
            }
        }
        else{
            if(zeroToN == true){
                result1 = rollZeroToN();
            }
            else{
                result1= roll();
            }
        }
        textViewOne.setText(""+result1);
        textViewTwo.setText(""+result2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        resetDefault();
        if (isChecked) {
            twoDice = true;
            textViewTwo.setVisibility(View.INVISIBLE);
        } else {
            twoDice = false;
            textViewTwo.setVisibility(View.VISIBLE);
        }
    }
}