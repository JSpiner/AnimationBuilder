package net.jspiner.animation;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationBuilder {

    public static AnimationBuilder builder() {
        return new AnimationBuilder();
    }

    private AnimationBuilder() {

    }

    private static final int DEFAULT_DURATION = 500;
    private static final Interpolator DEFAULT_INTERPOLATOR = new LinearInterpolator();

    private AnimationWrapper targetAnimation;
    private long startDelay = 0;
    private long duration = DEFAULT_DURATION;
    private Runnable startListener;
    private Runnable endListener;
    private UpdateListener updateListener;
    private Interpolator animationInterpolator = DEFAULT_INTERPOLATOR;
    private View targetView;
    private int repeatCount;

    public AnimationBuilder alphaAnimation(float startAlpha, float endAlpha) {
        this.targetAnimation = new AnimationWrapper(
                new AlphaAnimation(
                        startAlpha,
                        endAlpha
                )
        );
        return this;
    }

    public AnimationBuilder translateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        this.targetAnimation = new AnimationWrapper(
                new TranslateAnimation(
                        fromXDelta,
                        toXDelta,
                        fromYDelta,
                        toYDelta
                )
        );
        return this;
    }

    public AnimationBuilder scaleAnimation(float fromX, float toX, float fromY, float toY) {
        this.targetAnimation = new AnimationWrapper(
                new ScaleAnimation(
                        fromX, toX, fromY, toY
                )
        );
        return this;
    }

    public AnimationBuilder valueAnimator(int... values) {
        this.targetAnimation = new AnimationWrapper(
                ValueAnimator.ofInt(values)
        );
        return this;
    }

    public AnimationBuilder startDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public AnimationBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public AnimationBuilder onUpdate(UpdateListener updateListener) {
        this.updateListener = updateListener;
        return this;
    }

    public AnimationBuilder onStart(Runnable startListener) {
        this.startListener = startListener;
        return this;
    }

    public AnimationBuilder onEnd(Runnable endListener) {
        this.endListener = endListener;
        return this;
    }

    public AnimationBuilder interpolator(Interpolator interpolator) {
        this.animationInterpolator = interpolator;
        return this;
    }

    public AnimationBuilder repeat(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public AnimationBuilder targetView(View targetView) {
        this.targetView = targetView;
        return this;
    }

    public void start() {
        if (targetAnimation.isViewAnimation()) {
            startViewAnimation();
        }
        else {
            startAnimator();
        }
    }

    private void startViewAnimation() {
        initAnimation();
        this.targetView.startAnimation(targetAnimation.getViewAnimation());
    }

    private void initAnimation() {
        targetAnimation.getViewAnimation().setInterpolator(animationInterpolator);
        targetAnimation.getViewAnimation().setDuration(duration);
        targetAnimation.getViewAnimation().setStartOffset(startDelay);
        targetAnimation.getViewAnimation().setRepeatCount(repeatCount);
        targetAnimation.getViewAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (startListener != null) startListener.run();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (endListener != null) endListener.run();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startAnimator() {
        initAnimator();
        targetAnimation.getAnimator().start();
    }

    private void initAnimator() {
        targetAnimation.getAnimator().setInterpolator(animationInterpolator);
        targetAnimation.getAnimator().setDuration(duration);
        targetAnimation.getAnimator().setStartDelay(startDelay);
        targetAnimation.getAnimator().setRepeatCount(repeatCount);
        targetAnimation.getAnimator().addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (startListener != null) startListener.run();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (endListener != null) endListener.run();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        targetAnimation.getAnimator().addUpdateListener(valueAnimator -> {
            updateListener.onUpdate((Integer) valueAnimator.getAnimatedValue());
        });
    }
}
