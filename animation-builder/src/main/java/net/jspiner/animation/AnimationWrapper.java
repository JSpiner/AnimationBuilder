package net.jspiner.animation;

import android.animation.ValueAnimator;
import android.view.animation.Animation;

public class AnimationWrapper {

    private boolean isViewAnimation;
    private Animation viewAnimation;
    private ValueAnimator animator;

    public AnimationWrapper(Animation animation){
        this.viewAnimation = animation;
        this.isViewAnimation = true;
    }

    public AnimationWrapper(ValueAnimator animator){
        this.animator = animator;
        this.isViewAnimation = false;
    }

    public boolean isViewAnimation(){
        return isViewAnimation;
    }

    public Animation getViewAnimation(){
        return viewAnimation;
    }

    public ValueAnimator getAnimator(){
        return animator;
    }

}