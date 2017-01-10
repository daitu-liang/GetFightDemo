package com.daitu_liang.study.mytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.daitu_liang.study.mytest.fragment.LightFragment;
import com.daitu_liang.study.mytest.fragment.MusicFragment;
import com.daitu_liang.study.mytest.fragment.RainFragment;
import com.daitu_liang.study.mytest.fragment.SnowFragment;
import com.daitu_liang.study.mytest.fragment.StarFragment;

import java.util.ArrayList;


public class MainTwoActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private TabViewLayout mTabLayout;
	private ParticlePagerAdapter pagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_two);
		mViewPager = (ViewPager) findViewById(R.id.fragment_containers);
		mTabLayout = (TabViewLayout) findViewById(R.id.tab_layout);
		
		pagerAdapter = new ParticlePagerAdapter(getSupportFragmentManager(), getFragments());
		mViewPager.setAdapter(pagerAdapter);
		mTabLayout.setViewPager(mViewPager);


		
	}



	private ArrayList<Fragment> getFragments(){
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		list.add(new StarFragment());
		list.add(new MusicFragment());
		list.add(new RainFragment());
		list.add(new SnowFragment());
		list.add(new LightFragment());
		return list;
	}
	
	class ParticlePagerAdapter extends MyFragmentPagerAdapter{

		private ArrayList<Fragment> list;
		
		public ParticlePagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
			super(fm);
			this.list = list;
		}

		@Override
		public Fragment getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return list.get(position).getClass().getSimpleName().replace("Fragment", "");
		}
	}
	
}
