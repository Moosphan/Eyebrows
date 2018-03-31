package com.moos.eyebrows.maker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.Log;

import com.moos.eyebrows.R;
import com.moos.eyebrows.shape.EyebrowsBubble;
import com.moos.eyebrows.shape.EyebrowsShape;
import com.moos.eyebrows.shape.Point;

import java.util.Random;
import java.util.Vector;

import static android.content.ContentValues.TAG;

/**
 * Created by moos on 2018/3/30.
 * init bubble settings
 */

public class EyebrowsBubbleMaker implements EyebrowsMaker{

    /**
     * the min radius of bubble
     */
    private float startSize = 20;
    /**
     * the max radius of bubble
     */
    private float endSize = 60;
    private int cellSize = 300;
    private int offset = 100;
    private int count = 0;
    private Context context;
    private final Random random = new Random();

    @ColorInt
    private int[] colors = {R.color.hot_pink, R.color.bisque, R.color.floral_white, R.color.misty_rose, R.color.orchid,
                            R.color.pale_turquoise, R.color.light_coral};

    public EyebrowsBubbleMaker(Context context) {
        this.context = context;
    }

    @Override
    public EyebrowsShape makeEyebrowsShape(Point startPoint, Paint paint, float min, float max) {
        float radius = getBubbleSize(min,max);
        return new EyebrowsBubble(startPoint, paint, radius);
    }

    private float getBubbleSize(float start, float end){
        return (float) Math.random() * (end - start) + start;
    }

    @Override
    public Vector<Point> makePoints(int width, int height) {
        Vector<Point> points = new Vector<>();
        for (int j = 0; j < height; j += cellSize) {
            for (int i = 0; i < width; i += cellSize) {
                int x = i + (random.nextInt(offset));
                int y = j + random.nextInt(offset);
                points.add(new Point(x, y));
            }
        }
        return points;
    }

    @Override
    public Paint makePaint() {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int color = R.color.trans_white;
        //paint.setColor(context.getResources().getColor(color));
        return paint;
    }




}
