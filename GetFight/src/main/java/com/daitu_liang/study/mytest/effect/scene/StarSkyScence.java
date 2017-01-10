package com.daitu_liang.study.mytest.effect.scene;

import android.graphics.Canvas;

import com.daitu_liang.study.mytest.effect.base.EffectScence;
import com.daitu_liang.study.mytest.effect.item.Star;


public class StarSkyScence extends EffectScence {

	@Deprecated
	public StarSkyScence(int width, int height, int sknowNum){
		super(width, height, sknowNum);
	}
	public StarSkyScence(int width, int height, int sknowNum, int itemColor){
		super(width, height, sknowNum, itemColor);
	}
	public StarSkyScence(int width, int height, int sknowNum, int itemColor, boolean randColor){
		super(width, height, sknowNum, itemColor, randColor);
	}
	protected void initScence() {
		for(int i = 0; i < itemNum; i ++){
			list.add(new Star(width, height, itemColor, randColor));
		}		
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(0x80000000);
		super.draw(canvas);
	}
	
}
