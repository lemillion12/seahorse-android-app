package com.soma.amul.amulcalculate;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String packageNamePrefix = "com.soma.amul.amulcalculate.";
    public static final int items = 5;
    public static final double taazaPrice = 38.75;
    public static final double goldPrice = 48.75;
    public static final double dahiPrice = 18;
    public static final double chaachPrice = 9.50;
    public static final double paneerPrice = 58;

    Button resetButton, calculateButton;

    protected ArrayList<Double> price;
    protected ArrayList<Integer> pQuantity;
    protected ArrayList<TextInputEditText> pQuantityText;
    protected ArrayList<TextView> pPrices;


    public void reset(){
        for(int i=0; i<items; i++){
            pQuantityText.get(i).setText("0");
            pQuantity.set(i,0);
        }
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, Overview.class);
        intent.putExtra( packageNamePrefix + "items", items);
        intent.putExtra(packageNamePrefix + "price", price);
        intent.putExtra(packageNamePrefix + "pQuantity", pQuantity);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = (Button) findViewById(R.id.resetButton);
        calculateButton = (Button) findViewById(R.id.calculateButton);

        price = new ArrayList<Double>() {
            {
                add(taazaPrice);
                add(goldPrice);
                add(dahiPrice);
                add(chaachPrice);
                add(paneerPrice);
            }
        };

        pQuantity = new ArrayList<Integer>() {
            {
                for(int i=0; i<items; i++){
                    add(0);
                }
            }
        };

        pQuantityText = new ArrayList<TextInputEditText>() {
            {
                add( (TextInputEditText) findViewById(R.id.taazaQuantity) );
                add( (TextInputEditText) findViewById(R.id.goldQuantity) );
                add( (TextInputEditText) findViewById(R.id.dahiQuantity) );
                add( (TextInputEditText) findViewById(R.id.chaachQuantity) );
                add( (TextInputEditText) findViewById(R.id.paneerQuantity) );
            }
        };

        pPrices = new ArrayList<TextView>() {
            {
                add( (TextView) findViewById(R.id.taazaPrice) );
                add( (TextView) findViewById(R.id.goldPrice) );
                add( (TextView) findViewById(R.id.dahiPrice) );
                add( (TextView) findViewById(R.id.chaachPrice) );
                add( (TextView) findViewById(R.id.paneerPrice) );
            }
        };

        for(int i=0; i<pPrices.size(); i++){
            pPrices.get(i).setText( "Rs. " + price.get(i).toString() );
        }

        reset();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<items; i++){
                    String tempText = pQuantityText.get(i).getText().toString();
                    if( tempText == null || tempText.equals("") || tempText.isEmpty() ){
                        tempText = new String("0");
                        pQuantityText.get(i).setText(tempText);
                    }
                    pQuantity.set(i, Integer.parseInt(tempText) );
                }
                calculate(v);
            }
        });
    }
}
