# AnimationBuilder
 [ ![Download](https://api.bintray.com/packages/jspiner/maven/animationbuilder/images/download.svg) ](https://bintray.com/jspiner/maven/animationbuilder/_latestVersion)

AnimationBuilder is easist way to make animation.

# Useage

It is easy and powerful.
 ```java
AnimationBuilder.builder()
        .valueAnimator(0, 18500)
        .duration(300)
        .interpolator(new DecelerateInterpolator())
        .onUpdate(value -> binding.score.setText(numberFormat(value)))
        .start();
```

It support 4 type animator
- builder.valueAnimator(int... values)
- builder.translateAnimator(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
- builder.alphaAnimation(float startAlpha, float endAlpha)
- builder.scaleAnimation(float fromX, float toX, float fromY, float toY)

You can set some parameters like below.
- builder.startDelay(long startDelay)
- builder.duration(long duration)
- builder.interpolator(Interpolator interpolator)
- builder.repeat(int repeatCount)
- builder.targetView(View targetView)

It provides several callbacks.
- onUpdate(int value) * only valueAnimator()
- onStart()
- onEnd()

# will support
- maven deploy
- cancelable
- rotation animator
- chaining animator
- grouping animator

# setup
```
    implementation 'net.jspiner.animationbuilder:animationbuilder:0.0.1'
```
