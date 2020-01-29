package com.example.reportproblems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportProblems extends AppCompatActivity implements OnClickListener{

    private static AtomicInteger numberClicks = new AtomicInteger(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.btn1);
        Button c = (Button)findViewById(R.id.btn2);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        EditText textPanel = (EditText)findViewById(R.id.et1);

        switch (view.getId()){

            case R.id.btn1: //ok
                String input = textPanel.getText().toString();
                if(!"".equals(input)){
                    // just clicking input with no input is ignored
                    int i = numberClicks.addAndGet(1);
                    String msg1 = getString(R.string.acknowlege);
                    msg1 = String.format(msg1, Integer.toString(i));
                    textPanel.setText("");
                    textPanel.setHint(msg1);
                }
                break;

            case R.id.btn2: //clear
            String msg2 = getString(R.string.initialMessage);
            textPanel.setText("");
            textPanel.setText(msg2);
            break;

            default:
                break;

        }
    }
}
