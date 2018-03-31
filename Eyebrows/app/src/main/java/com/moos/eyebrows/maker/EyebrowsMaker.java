package com.moos.eyebrows.maker;

import android.graphics.Paint;

import com.moos.eyebrows.shape.EyebrowsShape;
import com.moos.eyebrows.shape.Point;

import java.util.Vector;

/**
 * Created by moos on 2018/3/30.
 * to generate the settings to display the eyebrowsView
 */

public interface EyebrowsMaker {

    /**
     * create the shapes for eyebrowsView
     */
    EyebrowsShape makeEyebrowsShape(Point startPoint, Paint paint, float minSize, float maxSize);
    /**
     * create the customize points
     * @param width the width of eyebrowsView
     * @param height the height of eyebrowsView
     */
    Vector<Point> makePoints(int width, int height);
    /**
     * create the paint that view needs
     */
    Paint makePaint();

}
