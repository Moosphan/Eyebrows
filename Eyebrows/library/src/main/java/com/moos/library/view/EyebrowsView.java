package com.moos.library.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.moos.library.EyebrowsFactory;
import com.moos.library.R;
import com.moos.library.animation.EyebrowsTranslateAnimator;
import com.moos.library.maker.EyebrowsAnimatorMaker;
import com.moos.library.maker.EyebrowsMaker;
import com.moos.library.shape.EyebrowsShape;
import com.moos.library.shape.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static android.content.ContentValues.TAG;

/**
 * Created by moos on 2018/3/30.
 * the view of bubbles
 */

public class EyebrowsView extends FrameLayout {

    private Vector<EyebrowsAnimatorMaker> eyebrowsAnimatorVector;
    private EyebrowsMaker eyebrowsMaker ;
    private Vector<EyebrowsShape> eyebrowsVector;
    private Vector<ValueAnimator> eyebrowsAnimators;
    private ValueAnimator viewRefreshAnimator;
    private EyebrowsFactory factory = new EyebrowsFactory(getContext());
    /**
     * the colors of shapes
     */
    private List<Integer> colors = new ArrayList<>();
    /**
     * the min size of shape
     */
    private float minSize = 20;
    /**
     * the max shape of shape
     */
    private float maxSize = 30;


    public EyebrowsView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public EyebrowsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public EyebrowsView( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Eyebrows);

        minSize = typedArray.getFloat(R.styleable.Eyebrows_bubble_min_size,20);
        maxSize = typedArray.getFloat(R.styleable.Eyebrows_bubble_max_size,30);

        typedArray.recycle();
        initializeRefreshAnimator();
    }



    private void initializeRefreshAnimator() {
        eyebrowsMaker = factory.createEyebrows(null);
        // todo:创建animator
        //factory.createEyebrowsAnimator(null);
        viewRefreshAnimator = ValueAnimator.ofInt(0, 1);
        viewRefreshAnimator.setRepeatCount(ValueAnimator.INFINITE);
        viewRefreshAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                start();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Vector<Point> points = eyebrowsMaker.makePoints(w,h);
        Log.e(TAG, "onSizeChanged: points =="+ points.size());
        eyebrowsVector = generateShapes(points);
        eyebrowsAnimators = generateAnimators(eyebrowsVector);
    }

    private Vector<EyebrowsShape> generateShapes(Vector<Point> points) {
        Vector<EyebrowsShape> vector = new Vector<>(points.size());
        for (Point point : points) {
            Paint paint = eyebrowsMaker.makePaint();
            EyebrowsShape eyebrowsShape = eyebrowsMaker.makeEyebrowsShape(point, paint, minSize, maxSize);
            vector.add(eyebrowsShape);
        }
        return vector;
    }

    private Vector<ValueAnimator> generateAnimators(Vector<EyebrowsShape> eyebrowsVector) {
        //eyebrowsAnimatorVector = new Vector<>();
        //eyebrowsAnimatorVector.add(new EyebrowsTranslateAnimator());
        //eyebrowsAnimatorVector.add(new EyebrowsShakeAnimator());
        //eyebrowsAnimatorVector.add(new EyebrowsScaleAnimator());
        if(eyebrowsAnimatorVector.size() == 0){
            eyebrowsAnimatorVector.add(new EyebrowsTranslateAnimator(3000, 2000, EyebrowsTranslateAnimator.Direction.UP_TO_DOWN,  new AccelerateInterpolator()));
        }
        Vector<ValueAnimator> valueAnimators = new Vector<>(eyebrowsVector.size());
        for (EyebrowsShape eyebrowsShape : eyebrowsVector) {
            for (EyebrowsAnimatorMaker eyebrowsAnimatorMaker : eyebrowsAnimatorVector) {
                ValueAnimator valueAnimator = eyebrowsAnimatorMaker.makeEyeAnimator(eyebrowsShape, getWidth(), getHeight());
                valueAnimators.add(valueAnimator);
            }
        }
        return valueAnimators;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int counter = 0;
        for(int i = 0; i<eyebrowsVector.size(); i++){
            EyebrowsShape eyebrowsShape = eyebrowsVector.get(i);
            if(counter >= colors.size()){
                counter = 0;
            }
            if(colors.size() == 0){
                eyebrowsShape.getPaint().setColor(getResources().getColor(R.color.trans_white));
            }else {
                eyebrowsShape.getPaint().setColor(colors.get(counter));
            }

            counter++;
            eyebrowsShape.draw(canvas);

        }

    }

    /**
     * start the anim
     */
    public void start() {
        viewRefreshAnimator.start();
        for (ValueAnimator valueAnimator : eyebrowsAnimators) {
            valueAnimator.start();
        }
    }

    /**
     * stop the anim
     */
    public void stop(){
        viewRefreshAnimator.setRepeatCount(0);
        viewRefreshAnimator.removeAllListeners();
        viewRefreshAnimator.removeAllUpdateListeners();
        viewRefreshAnimator.cancel();
        viewRefreshAnimator.end();
        for (ValueAnimator valueAnimator : eyebrowsAnimators) {
            valueAnimator.setRepeatCount(0);
            valueAnimator.removeAllListeners();
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.cancel();
            valueAnimator.end();
        }
    }


    /**
     * set the small cell size for eyebrows
     * @param min min size
     * @param max max size
     */
    public void setEyebrowsShapeSize(float min, float max){
        this.minSize = min;
        this.maxSize = max;
    }

    /**
     * set the color
     * @param colors
     */
    public void setEyebrowsShapeColors(List<Integer> colors){
        this.colors = colors;
    }

    /**
     * set the small animators
     * @param animatorMakers
     */
    public void setEyebrowsAnimators(Vector<EyebrowsAnimatorMaker> animatorMakers){

        this.eyebrowsAnimatorVector = animatorMakers;
    }



}
