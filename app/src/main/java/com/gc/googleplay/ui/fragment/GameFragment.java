package com.gc.googleplay.ui.fragment;

import android.view.View;

import com.gc.googleplay.ui.view.LoadingPage;

/**
 * 游戏
 */
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.STATE_EMPTY;
    }
}
