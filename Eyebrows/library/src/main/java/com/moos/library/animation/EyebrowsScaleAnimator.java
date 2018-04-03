package com.moos.library.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

import com.moos.library.maker.EyebrowsAnimatorMaker;
import com.moos.library.shape.EyebrowsShape;

/**
 * Created by moos on 2018/3/30.
 * the scale animation for bubble shape(from small to big)
 */

public class EyebrowsScaleAnimator extends EyebrowsAnimatorMaker<EyebrowsShape> {
    private int animationDuration = 3000;
    /**
     * the start radius of bubble
     */
    private float startSize = 15;
    /**
     * the end radius of bubble
     */
    private float endSize = 45;
    private int minDuration = 2000;
    private int maxDuration = 3000;
    private TimeInterpolator mInterpolator ;

    public EyebrowsScaleAnimator(int minDuration, int maxDuration, TimeInterpolator interpolator, float startSize, float endSize) {
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.mInterpolator = interpolator;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    @Override
    protected ValueAnimator createValueAnimator(EyebrowsShape eyebrowsShape, int width, int height) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startSize , endSize);
        int duration = minDuration + (int) (Math.random() * maxDuration);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return valueAnimator;
    }

    @Override
    protected UpdateEyebrowsListener createUpdateListener() {
        return new UpdateEyebrowsListener<EyebrowsShape>() {
            @Override
            public void onUpdate(EyebrowsShape shape, ValueAnimator animator) {
                float radius = (float) animator.getAnimatedValue();
                shape.setShapeSize(radius);
            }
        };
    }
}
