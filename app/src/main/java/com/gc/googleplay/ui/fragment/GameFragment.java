package com.gc.googleplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.utils.UIUtils;

/**
 * 游戏
 */
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        TextView view = new TextView(UIUtils.getContext());
        view.setText("GameFragment");
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.STATE_SUCCESS;
    }
}
