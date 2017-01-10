package com.daitu_liang.study.mytest.effect.scene;


import com.daitu_liang.study.mytest.effect.base.EffectScence;
import com.daitu_liang.study.mytest.effect.item.SnowPoint2;

public class Snow2Scence extends EffectScence {

	@Deprecated
	public Snow2Scence(int width, int height, int sknowNum){
		super(width, height, sknowNum);
	}
	public Snow2Scence(int width, int height, int sknowNum, int itemColor){
		super(width, height, sknowNum, itemColor);
	}
	public Snow2Scence(int width, int height, int sknowNum, int itemColor, boolean randColor){
		super(width, height, sknowNum, itemColor, randColor);
	}
	protected void initScence() {
		for(int i = 0; i < itemNum; i ++){
			list.add(new SnowPoint2(width, height, itemColor, randColor));
		}		
	}
	
}
