package com.daitu_liang.study.mytest.effect.scene;


import com.daitu_liang.study.mytest.effect.base.EffectScence;
import com.daitu_liang.study.mytest.effect.item.MusicPoint;

public class MusicJumpScence extends EffectScence {
	@Deprecated
	public MusicJumpScence(int width, int height, int musicNum){
		super(width, height, musicNum);
	}
	@Deprecated
	public MusicJumpScence(int width, int height, int musicNum, int itemColor){
		super(width, height, musicNum, itemColor);
	}
	
	public MusicJumpScence(int width, int height, int musicNum, int itemColor, boolean randColor){
		super(width, height, musicNum, itemColor, randColor);
	}
	
	protected void initScence() {
		for(int i = 0; i < itemNum; i ++){
			list.add(new MusicPoint(i * width / itemNum, width, height, itemColor, randColor));
		}		
	}
	
}
