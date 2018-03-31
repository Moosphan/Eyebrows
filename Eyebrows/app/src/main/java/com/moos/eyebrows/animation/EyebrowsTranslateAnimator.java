package com.moos.eyebrows.animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import com.moos.eyebrows.maker.EyebrowsAnimatorMaker;
import com.moos.eyebrows.shape.EyebrowsShape;

/**
 * Created by moos on 2018/3/30.
 * the translate animation for bubble(or other extend eyebrowsShape)
 */

public class EyebrowsTranslateAnimator extends EyebrowsAnimatorMaker<EyebrowsShape> {

    private static final int TRANSLATE_OFFSET = 60;
    private Direction direction = Direction.DOWN_TO_UP;
    private int minDuration = 2000;
    private int maxDuration = 3000;
    private TimeInterpolator mInterpolator ;

    public EyebrowsTranslateAnimator(int minDuration, int maxDuration, Direction direction, TimeInterpolator mInterpolator) {
        this.direction = direction;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.mInterpolator = mInterpolator;
    }

    @Override
    protected ValueAnimator createValueAnimator(EyebrowsShape eyebrowsShape, int width, int height) {
        ValueAnimator valueAnimator = createTranslateAnimator(direction, width, height);
        int duration = minDuration + (int) (Math.random() * maxDuration);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(mInterpolator);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        if (mInterpolator != null) {
            valueAnimator.setInterpolator(mInterpolator);
        }
        return valueAnimator;
    }

    private ValueAnimator createTranslateAnimator(Direction direction, int width, int height){

        switch (direction) {
            case DOWN_TO_UP:
                return ValueAnimator.ofFloat(height + TRANSLATE_OFFSET, 0 - TRANSLATE_OFFSET);
            case LEFT_TO_RIGHT:
                return ValueAnimator.ofFloat(0 - TRANSLATE_OFFSET, width + TRANSLATE_OFFSET);
            case RIGHT_TO_LEFT:
                return ValueAnimator.ofFloat(width + TRANSLATE_OFFSET, 0 - TRANSLATE_OFFSET);
            case UP_TO_DOWN:
                return ValueAnimator.ofFloat(0 - TRANSLATE_OFFSET, height + TRANSLATE_OFFSET);
            default:
                return ValueAnimator.ofFloat(0 - TRANSLATE_OFFSET, width + TRANSLATE_OFFSET);
        }
    }

    @Override
    protected UpdateEyebrowsListener createUpdateListener() {
        return new EyebrowsAnimatorMaker.UpdateEyebrowsListener<EyebrowsShape>(){

            @Override
            public void onUpdate(EyebrowsShape eyebrowsShape, ValueAnimator animator) {
                float value = (float) animator.getAnimatedValue();
                if (direction.equals(Direction.LEFT_TO_RIGHT) || direction.equals(Direction.RIGHT_TO_LEFT)) {
                    eyebrowsShape.setX(value);
                } else {
                    eyebrowsShape.setY(value);
                }
            }
        };
    }

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, UP_TO_DOWN, DOWN_TO_UP
    }

}
