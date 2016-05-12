package com.gc.googleplay.ui.fragment;

import android.view.View;

import com.gc.googleplay.ui.view.LoadingPage;

/**
 * 应用HomeFragment
 */
public class AppFragment extends BaseFragment {
    //只有成功才走此方法
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.STATE_ERROR;
    }
}
