package com.soma.amul.amulcalculate;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Overview extends AppCompatActivity {

    public static final String packageNamePrefix = "com.soma.amul.amulcalculate.";
    public static int items;

    Button newButton, backButton;

    protected ArrayList<Double> price;
    protected ArrayList<Integer> pQuantity;
    protected ArrayList<Double> pCost;
    protected ArrayList<TextView> pQuantityText;
    protected ArrayList<TextView> pCostText;
    protected Double totalCost;
    protected TextView totalCostText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();

        newButton = (Button) findViewById(R.id.newButton);
        backButton = (Button) findViewById(R.id.backButton);

        pQuantityText = new ArrayList<TextView>() {
            {
                add( (TextView) findViewById(R.id.taazaQuantity) );
                add( (TextView) findViewById(R.id.goldQuantity) );
                add( (TextView) findViewById(R.id.dahiQuantity) );
                add( (TextView) findViewById(R.id.chaachQuantity) );
                add( (TextView) findViewById(R.id.paneerQuantity) );
            }
        };

        pCostText = new ArrayList<TextView>() {
            {
                add( (TextView) findViewById(R.id.taazaCost) );
                add( (TextView) findViewById(R.id.goldCost) );
                add( (TextView) findViewById(R.id.dahiCost) );
                add( (TextView) findViewById(R.id.chaachCost) );
                add( (TextView) findViewById(R.id.paneerCost) );
            }
        };

        totalCostText = (TextView) findViewById(R.id.totalCost);

        items = (Integer) intent.getIntExtra( packageNamePrefix + "items", 0);
        price = (ArrayList<Double>) intent.getSerializableExtra(packageNamePrefix + "price");
        pQuantity = (ArrayList<Integer>) intent.getSerializableExtra(packageNamePrefix + "pQuantity");


        pCost = new ArrayList<Double>() {
            {
                for(int i=0; i<items; i++){
                    add( price.get(i) * pQuantity.get(i) );
                }
            }
        };

        for(int i=0; i<items; i++){
            pQuantityText.get(i).setText( pQuantity.get(i).toString() + " items" );
        }

        for(int i=0; i<items; i++){
            pCostText.get(i).setText( ( "Rs. " + pCost.get(i).toString() ) );
        }


        totalCost = 0.0;
        for(int i=0; i<items; i++){
            totalCost += pCost.get(i);
        }
        totalCostText.setText( ( "Rs. " + totalCost.toString() ) );

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName() );

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(i);
            }
        });
    }
}
