package com.royrodriguez.transitionbutton;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.royrodriguez.transitionbutton.utils.WindowUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TransitionButton transitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowUtils.makeStatusbarTransparent(this);

        getSupportActionBar().hide();

        transitionButton = findViewById(R.id.transition_button);

        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionButton.startAnimation();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                            @Override
                            public void onAnimationStopEnd() {

                            }
                        });
                    }
                }, 2000);
            }
        });
    }

}
