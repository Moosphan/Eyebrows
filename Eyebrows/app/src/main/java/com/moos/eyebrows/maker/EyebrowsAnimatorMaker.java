package com.moos.eyebrows.maker;

import android.animation.ValueAnimator;

import com.moos.eyebrows.shape.EyebrowsShape;

/**
 * Created by moos on 2018/3/30.
 * the base eyebrows animator
 */

public abstract class EyebrowsAnimatorMaker<T extends EyebrowsShape> {
    public ValueAnimator makeEyeAnimator(T eyebrowsShape, int width, int height){
        ValueAnimator valueAnimator = createValueAnimator(eyebrowsShape, width, height);
        UpdateEyebrowsListener<T> updateEyebrowsListener = createUpdateListener();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener(eyebrowsShape, updateEyebrowsListener));
        return valueAnimator;
    }

    protected abstract ValueAnimator createValueAnimator(T eyebrowsShape, int width, int height);

    protected abstract UpdateEyebrowsListener<T> createUpdateListener();

    public interface UpdateEyebrowsListener<T> {
        void onUpdate(T eyebrowsShape, ValueAnimator animator);
    }

    /**
     * implement the interface and use generics to get different instances
     */
    private class AnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private final T eyebrowsShape;
        private final UpdateEyebrowsListener updateEyebrowsListener;

        private AnimatorUpdateListener(T eyebrowsShape, UpdateEyebrowsListener updateEyebrowsListener) {
            this.eyebrowsShape = eyebrowsShape;
            this.updateEyebrowsListener = updateEyebrowsListener;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            updateEyebrowsListener.onUpdate(eyebrowsShape, animation);
        }
    }
}
