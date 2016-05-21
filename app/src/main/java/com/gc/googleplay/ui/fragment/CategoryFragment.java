package com.gc.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListAdapter;

import com.gc.googleplay.domain.CategoryInfo;
import com.gc.googleplay.http.protocol.CategoryProtocol;
import com.gc.googleplay.ui.adapter.MyBaseAdapter;
import com.gc.googleplay.ui.holder.BaseHolder;
import com.gc.googleplay.ui.holder.CategoryHolder;
import com.gc.googleplay.ui.holder.TitleHolder;
import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.ui.view.MyListView;
import com.gc.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * 分类
 */
public class CategoryFragment extends BaseFragment {
    private ArrayList<CategoryInfo> data;

    @Override
    public View onCreateSuccessView() {
        MyListView view = new MyListView(UIUtils.getContext());
        view.setAdapter((ListAdapter) new CategoryAdapter(data));
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        CategoryProtocol protocol = new CategoryProtocol();
        data = protocol.getData(0);
        return check(data);
    }

    class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

        public CategoryAdapter(ArrayList<CategoryInfo> data) {
            super(data);
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;// 在原来基础上增加一种标题类型
        }

        @Override
        public int getInnerType(int position) {
            // 判断是标题类型还是普通分类类型
            CategoryInfo info = data.get(position);

            if (info.isTitle) {
                // 返回标题类型
                return super.getInnerType(position) + 1;// 原来类型基础上加1; 注意:
                // 将TYPE_NORMAL修改为1;
            } else {
                // 返回普通类型
                return super.getInnerType(position);
            }
        }

        @Override
        public BaseHolder<CategoryInfo> getHolder(int position) {
            // 判断是标题类型还是普通分类类型, 来返回不同的holder
            CategoryInfo info = data.get(position);

            if (info.isTitle) {
                return new TitleHolder();
            } else {
                return new CategoryHolder();
            }
        }

        @Override
        public boolean hasMore() {
            return false;// 没有更多数据, 需要隐藏加载更多的布局
        }


        @Override
        public ArrayList<CategoryInfo> onLoadMore() {
            return null;
        }

    }
}
