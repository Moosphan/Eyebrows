package com.moos.library.maker;

import android.content.Context;
import android.graphics.Paint;

import com.moos.library.R;
import com.moos.library.shape.EyebrowsBubble;
import com.moos.library.shape.EyebrowsShape;
import com.moos.library.shape.Point;

import java.util.Random;
import java.util.Vector;

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
