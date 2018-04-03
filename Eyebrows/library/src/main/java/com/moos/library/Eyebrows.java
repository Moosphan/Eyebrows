package com.moos.library;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;

/**
 * Created by moos on 2018/3/29.
 * desc:the gradient color animation make you eyebrows...
 */

public class Eyebrows{
    private View mView;
    private int mDuration;
    private int mGradientAnimation;
    private AnimationDrawable mAnimationDrawable = null;


    public static final int ANIM_GREEN_PURPLE = R.drawable.anim_green_purple;
    public static final int ANIM_BLUE_PURPLE = R.drawable.anim_blue_purple;
    public static final int ANIM_RED_PURPLE = R.drawable.anim_red_purple;


    private Eyebrows(Builder builder){
        this.mView = builder.view;
        this.mDuration = builder.duration;
        this.mGradientAnimation = builder.gradientAnimation;
    }

    /**
     * start the gradient anim
     */
    public void startGradientAnimation(){
        if(mView!=null){
            mView.setBackgroundResource(mGradientAnimation);
            mAnimationDrawable = (AnimationDrawable) mView.getBackground();
            if(mAnimationDrawable!=null){
                mView.setBackground(mAnimationDrawable);
                mAnimationDrawable.setEnterFadeDuration(mDuration);
                mAnimationDrawable.setExitFadeDuration(mDuration);
                mView.post(new Runnable() {
                    @Override
                    public void run() {
                        mAnimationDrawable.start();
                    }
                });
            }
        }
    }

    /**
     * stop the gradient anim
     */
    public void stopGradientAnimation(){
        if(mView!=null && mAnimationDrawable.isRunning()){
            mAnimationDrawable.stop();
        }
    }

    /**
     * the builder mode to create eyebrows animation
     */
    public static class Builder{
        private View view = null;
        private int duration = 3000;
        private int gradientAnimation = ANIM_GREEN_PURPLE;



        public Builder bindTargetView(View targetView){
            this.view = targetView;
            return this;
        }

        public Builder setDuration(int duration){
            this.duration = duration;
            return this;
        }

        public Builder setGradientAnimation(int gradientType){
            this.gradientAnimation = gradientType;
            return this;
        }

        public Eyebrows build(){
            return new Eyebrows(this);
        }
    }

}
