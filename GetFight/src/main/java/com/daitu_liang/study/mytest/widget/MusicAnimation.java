package com.daitu_liang.study.mytest.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.daitu_liang.study.mytest.effect.base.EffectScence;
import com.daitu_liang.study.mytest.effect.scene.MusicJumpScence;


public class MusicAnimation extends EffectAnimation {

	public MusicAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MusicAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MusicAnimation(Context context) {
		super(context);
	}

	@Override
	protected EffectScence initScence(int itemNum) {
		int width = getWidth();
		int height = getHeight();
		
		return new MusicJumpScence(width, height, itemNum);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new MusicJumpScence(width, height, itemNum, itemColor);
	}

	@Override
	protected EffectScence initScence(int itemNum, int itemColor,
			boolean randColor) {
		int width = getWidth();
		int height = getHeight();
		
		return new MusicJumpScence(width, height, itemNum, itemColor, randColor);
	}

}
