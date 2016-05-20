package com.gc.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListAdapter;

import com.gc.googleplay.domain.AppInfo;
import com.gc.googleplay.http.protocol.AppProtocol;
import com.gc.googleplay.ui.adapter.MyBaseAdapter;
import com.gc.googleplay.ui.holder.AppHolder;
import com.gc.googleplay.ui.holder.BaseHolder;
import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.ui.view.MyListView;
import com.gc.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * 应用HomeFragment
 */
public class AppFragment extends BaseFragment {
    private ArrayList<AppInfo> data;

    // 只有成功才走此方法
    @Override
    public View onCreateSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter((ListAdapter) new AppAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        AppProtocol protocol = new AppProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class AppAdapter extends MyBaseAdapter<AppInfo> {

        public AppAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder() {
            return new AppHolder();
        }

        @Override
        public ArrayList<AppInfo> onLoadMore() {
            AppProtocol protocol = new AppProtocol();
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }

    }

}
