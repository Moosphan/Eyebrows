package com.moos.library.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by moos on 2018/3/30.
 * a super class used for extending,the base shape
 */

public abstract class EyebrowsShape {
    Point startPoint;
    Point drawPoint;
    Paint paint;
    float size;

    public EyebrowsShape(Point startPoint, Paint paint) {
        this.startPoint = startPoint;
        this.paint = paint;
        drawPoint = new Point(startPoint);
    }

    public void draw(Canvas canvas) {
        draw(canvas, drawPoint);
    }

    protected abstract void draw(Canvas canvas, Point drawPoint);

    public void setX(float x) {
        drawPoint.x = x;
    }

    public void setY(float y) {
        drawPoint.y = y;
    }

    public float getX(){
        return drawPoint.x;
    }

    public float getY() {
        return drawPoint.y;
    }

    public Point getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(Point point) {
        this.drawPoint = point;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Paint getPaint() {
        return paint;
    }
    public void setShapeSize(float size){
        this.size = size;
    }
}
