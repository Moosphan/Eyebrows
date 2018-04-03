package com.moos.eyebrows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.moos.library.Eyebrows;
import com.moos.eyebrows.R;
import com.moos.library.animation.EyebrowsScaleAnimator;
import com.moos.library.animation.EyebrowsShakeAnimator;
import com.moos.library.animation.EyebrowsTranslateAnimator;
import com.moos.library.maker.EyebrowsAnimatorMaker;
import com.moos.library.view.EyebrowsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private EyebrowsView targetView;
    private Eyebrows eyebrows;
    private ImageView logo;
    private List<Integer> colors = new ArrayList<>();
    private Vector<EyebrowsAnimatorMaker> animatorMakers = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        targetView = findViewById(R.id.targetView);
        logo = findViewById(R.id.logo);

        animateBackground();
        animateBubbles();


        logo.animate().rotation(-1800).setDuration(18000);

    }

    private void animateBackground(){
        eyebrows = new Eyebrows.Builder()
                .bindTargetView(targetView)
                .setDuration(5000)
                .setGradientAnimation(Eyebrows.ANIM_RED_PURPLE)
                .build();
    }

    private void animateBubbles(){
        colors.add(getResources().getColor(R.color.trans_yellow));
        colors.add(getResources().getColor(R.color.trans_white));
        colors.add(getResources().getColor(R.color.trans_gray));
        targetView.setEyebrowsShapeColors(colors);
        targetView.setEyebrowsShapeSize(20, 50);
        animatorMakers.add(new EyebrowsTranslateAnimator(5000, 6000, EyebrowsTranslateAnimator.Direction.UP_TO_DOWN,  new AccelerateInterpolator()));
        animatorMakers.add(new EyebrowsScaleAnimator(2000, 3000, new AccelerateDecelerateInterpolator(), 20, 50));
        animatorMakers.add(new EyebrowsShakeAnimator(2000, 3000, EyebrowsShakeAnimator.ShakeDirection.HORIZONTAL, new AccelerateInterpolator()));
        targetView.setEyebrowsAnimators(animatorMakers);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eyebrows.startGradientAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        eyebrows.stopGradientAnimation();
    }
}
