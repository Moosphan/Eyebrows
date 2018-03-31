package com.moos.eyebrows.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by moos on 2018/3/30.
 * to draw the bubble shape
 */

public class EyebrowsBubble extends EyebrowsShape {

    private float radius;

    public EyebrowsBubble(Point startPoint, Paint paint) {
        super(startPoint, paint);
    }

    public EyebrowsBubble(Point startPoint, Paint paint, float radius) {
        super(startPoint, paint);
        this.radius = radius;
    }

    @Override
    protected void draw(Canvas canvas, Point drawPoint) {
        canvas.drawCircle(drawPoint.getX(), drawPoint.getY(), radius, paint);
    }


}
