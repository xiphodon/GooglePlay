package com.gc.googleplay.ui.fragment;

import android.view.View;

import com.gc.googleplay.domain.SubjectInfo;
import com.gc.googleplay.http.protocol.SubjectProtocol;
import com.gc.googleplay.ui.adapter.MyBaseAdapter;
import com.gc.googleplay.ui.holder.BaseHolder;
import com.gc.googleplay.ui.holder.SubjectHolder;
import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.ui.view.MyListView;
import com.gc.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * 专题
 */
public class SubjectFragment extends BaseFragment {
    private ArrayList<SubjectInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter(new SubjectAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        SubjectProtocol protocol = new SubjectProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

        public SubjectAdapter(ArrayList<SubjectInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<SubjectInfo> getHolder(int position) {
            return new SubjectHolder();
        }

        @Override
        public ArrayList<SubjectInfo> onLoadMore() {
            SubjectProtocol protocol = new SubjectProtocol();
            ArrayList<SubjectInfo> moreData = protocol.getData(getListSize());
            return moreData;
        }

    }
}
