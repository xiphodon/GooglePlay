package com.gc.googleplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.utils.UIUtils;

/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 使用textview显示当前类的类名
        // TextView view = new TextView(UIUtils.getContext());
        // view.setText(getClass().getSimpleName());
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {

            @Override
            public View onCreateSuccessView() {
                // 注意:此处一定要调用BaseFragment的onCreateSuccessView, 否则栈溢出
                return BaseFragment.this.onCreateSuccessView();
            }

            @Override
            public ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }

        };

        return mLoadingPage;
    }

    /**
     * 加载成功的布局, 必须由子类来实现
     * @return
     */
    public abstract View onCreateSuccessView();

    /**
     * 加载网络数据, 必须由子类来实现
     * @return
     */
    public abstract LoadingPage.ResultState onLoad();

    /**
     * 开始加载数据
     */
    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }
}
