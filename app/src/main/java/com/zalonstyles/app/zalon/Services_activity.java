package com.zalonstyles.app.zalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by KASHISH on 12-07-2016.
 */
public class Services_activity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_screen);
        Button body = (Button) findViewById(R.id.Body);
        Button nail = (Button)findViewById(R.id.Nails);
        Button hair = (Button) findViewById(R.id.hair);
        Button massage = (Button) findViewById(R.id.Massage);
        Button face = (Button) findViewById(R.id.Face);
        Button hairremoval = (Button)findViewById(R.id.hair_removal) ;

        hairremoval.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_activity.this,popup_hr_removal.class));

            }
        });


       face.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_activity.this,popup_face.class));

            }
        });


        massage.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_activity.this,popup_massage.class));

            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_activity.this,Popup_body.class));

            }
        });
        nail.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Services_activity.this,popup_nail.class));
                                    }
                                }
        );
        hair.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Services_activity.this,popup_hair.class));
                                    }
                                }
        );


    }
}
