package com.gc.googleplay.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.googleplay.R;
import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.utils.UIUtils;

/**
 * 游戏
 */
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        ImageView imageView = new ImageView(UIUtils.getContext());
        imageView.setImageResource(R.drawable.game);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.STATE_SUCCESS;
    }
}
