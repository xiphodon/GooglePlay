package com.gc.googleplay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;


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
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //在ActionBar上显示logo
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setLogo(R.drawable.ic_launcher_x_small);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_main);

        pt_pagerTab = (PagerTab) findViewById(R.id.pt_pagerTab);
        vp_viewPager = (ViewPager) findViewById(R.id.vp_viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp_viewPager.setAdapter(viewPagerAdapter);

        //将PagerTab和ViewPager绑定在一起
        pt_pagerTab.setViewPager(vp_viewPager);

        //滑动界面切换监听
        pt_pagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseFragment baseFragment = FragmentFactory.createFragment(position);
                // 让选择的界面开始加载数据
                baseFragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initActionbar();
    }

    /**
     * 初始化actionbar
     */
    private void initActionbar() {
        ActionBar actionbar = getSupportActionBar();

        //在ActionBar上显示logo
        actionbar.setLogo(R.drawable.ic_launcher_x_small);
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

        actionbar.setHomeButtonEnabled(true);// home处可以点击
        actionbar.setDisplayHomeAsUpEnabled(true);// 显示左上角返回键

        actionbar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);//左上角返回键更改为三个杠图片

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);

        // 初始化抽屉开关
        toggle = new ActionBarDrawerToggle(this, drawer, R.drawable.ic_drawer_am, R.string.drawer_open, R.string.drawer_close);

        toggle.syncState();// 同步状态, 将DrawerLayout和开关关联在一起
    }

    /**
     * ToolBar中的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 切换抽屉
                toggle.onOptionsItemSelected(item);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
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
