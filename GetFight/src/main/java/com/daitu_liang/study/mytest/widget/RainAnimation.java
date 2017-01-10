package com.daitu_liang.study.mytest.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.daitu_liang.study.mytest.effect.base.EffectScence;
import com.daitu_liang.study.mytest.effect.scene.RainScence;


public class RainAnimation extends EffectAnimation {

	public RainAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public RainAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RainAnimation(Context context) {
		super(context);
	}

	@Override
	protected EffectScence initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();
		
		return new RainScence(width, height, itemNum);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new RainScence(width, height, itemNum, itemColor);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor,
			boolean randColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new RainScence(width, height, itemNum, itemColor, randColor);
	}

}
