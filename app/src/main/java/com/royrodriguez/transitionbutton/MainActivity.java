package com.royrodriguez.transitionbutton;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.royrodriguez.transitionbutton.utils.WindowUtils;

public class MainActivity extends AppCompatActivity {

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
                // Then start the loading animation when the user tap the button
                transitionButton.startAnimation();

                // Do your networking task or background work here.
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean isSuccessful = true;

                        if (isSuccessful) {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                                @Override
                                public void onAnimationStopEnd() {
                                    Intent intent = new Intent(getBaseContext(), NewActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 2000);
            }
        });
    }

}
