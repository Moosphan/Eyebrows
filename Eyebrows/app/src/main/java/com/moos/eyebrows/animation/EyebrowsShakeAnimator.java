package com.moos.eyebrows.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

import com.moos.eyebrows.maker.EyebrowsAnimatorMaker;
import com.moos.eyebrows.shape.EyebrowsShape;
import com.moos.eyebrows.shape.Point;

/**
 * Created by moos on 2018/3/30.
 * the shake animation for eyebrowsShape children
 */

public class EyebrowsShakeAnimator extends EyebrowsAnimatorMaker<EyebrowsShape> {

    private float SHAKE_OFFSET = 80;
    private ShakeDirection direction = ShakeDirection.HORIZONTAL;
    private int minDuration = 2000;
    private int maxDuration = 3000;
    private TimeInterpolator mInterpolator ;

    public EyebrowsShakeAnimator(int minDuration, int maxDuration, ShakeDirection direction, TimeInterpolator mInterpolator) {
        this.direction = direction;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.mInterpolator = mInterpolator;
    }

    @Override
    protected ValueAnimator createValueAnimator(EyebrowsShape eyebrowsShape, int width, int height) {
        Point startPoint = eyebrowsShape.getStartPoint();
        ValueAnimator valueAnimator = createShakeAnimator(direction, startPoint);
        int duration = minDuration + (int)(Math.random() * maxDuration);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return valueAnimator;
    }

    private ValueAnimator createShakeAnimator(ShakeDirection direction, Point startPoint) {
        if (direction.equals(ShakeDirection.HORIZONTAL)){
            return ValueAnimator.ofFloat(startPoint.x - SHAKE_OFFSET, startPoint.x + SHAKE_OFFSET);
        } else {
            return ValueAnimator.ofFloat(startPoint.y - SHAKE_OFFSET, startPoint.y + SHAKE_OFFSET);
        }
    }

    @Override
    protected UpdateEyebrowsListener createUpdateListener() {
        return new UpdateEyebrowsListener<EyebrowsShape>() {
            @Override
            public void onUpdate(EyebrowsShape eyebrowsShape, ValueAnimator animator) {
                float value = (float) animator.getAnimatedValue();
                if (direction.equals(ShakeDirection.HORIZONTAL)) {
                    eyebrowsShape.setX(value);
                } else {
                    eyebrowsShape.setY(value);
                }
            }
        };
    }

    public enum ShakeDirection{
        HORIZONTAL, VERTICAL
    }
}
