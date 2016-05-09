package com.gc.googleplay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.gc.googleplay.R;
import com.gc.googleplay.ui.fragment.BaseFragment;
import com.gc.googleplay.ui.fragment.FragmentFactory;
import com.gc.googleplay.ui.view.PagerTab;
import com.gc.googleplay.utils.UIUtils;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {

    private PagerTab pt_pagerTab;
    private ViewPager vp_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在ActionBar上显示logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_launcher_x_small);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_main);

        pt_pagerTab = (PagerTab) findViewById(R.id.pt_pagerTab);
        vp_viewPager = (ViewPager) findViewById(R.id.vp_viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp_viewPager.setAdapter(viewPagerAdapter);

        //将PagerTab和ViewPager绑定在一起
        pt_pagerTab.setViewPager(vp_viewPager);
    }

    /**
     * FragmentPagerAdapter是PagerAdapter的子类
     */
    class ViewPagerAdapter extends FragmentPagerAdapter{

        private final String[] stringArrary;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            //加载页面标题数组
            stringArrary = UIUtils.getStringArrary(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrary[position];
        }

        //返回当前页面位置的fragment对象
        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        //fragment数量
        @Override
        public int getCount() {
            return stringArrary.length;
        }
    }
}
