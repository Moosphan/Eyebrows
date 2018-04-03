package com.moos.library;

import android.content.Context;

import com.moos.library.maker.EyebrowsAnimatorMaker;
import com.moos.library.maker.EyebrowsBubbleMaker;
import com.moos.library.maker.EyebrowsMaker;

/**
 * Created by moos on 2018/3/30.
 * intensively manage the makers and init work
 */

public class EyebrowsFactory {
    private Context context;

    public EyebrowsFactory(Context context) {
        this.context = context;
    }

    /**
     * create the eyebrowsMaker
     * @param className the target eyebrowsMaker's class
     * @return target eyebrowsMaker
     */
    public EyebrowsMaker createEyebrows(String className){
        if(className == null || className.isEmpty()){
            return new EyebrowsBubbleMaker(context);
        }
        EyebrowsMaker eyebrowsMaker = getClassByName(className, EyebrowsMaker.class);
        return eyebrowsMaker;
    }

    /**
     * create the eyebrowsAnimator
     * @param className the target eyebrowsAnimator's class
     * @return target eyebrowsAnimator
     */
    public EyebrowsAnimatorMaker createEyebrowsAnimator(String className){
        if (className == null || className.isEmpty()) {
            return null;
        }
        EyebrowsAnimatorMaker animatorMaker = getClassByName(className, EyebrowsAnimatorMaker.class);
        return animatorMaker;
    }

    private <T> T getClassByName(String className, Class<T> classOfT) {
        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.newInstance();
            if (classOfT.isInstance(instance)) {
                return classOfT.cast(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
