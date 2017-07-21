package com.whitewolf.mymemorydemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whitewolf.mymemorydemoapp.helper.MemoryHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myStatsButton = (Button) findViewById(R.id.main_button_stats);
        myStatsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.main_button_stats:
                intent = new Intent(this, MemoryAllocationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
