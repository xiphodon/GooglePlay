package com.gc.googleplay.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.googleplay.R;
import com.gc.googleplay.domain.AppInfo;
import com.gc.googleplay.http.protocol.HomeProtocol;
import com.gc.googleplay.ui.adapter.MyBaseAdapter;
import com.gc.googleplay.ui.holder.BaseHolder;
import com.gc.googleplay.ui.holder.HomeHeaderHolder;
import com.gc.googleplay.ui.holder.HomeHolder;
import com.gc.googleplay.ui.view.LoadingPage;
import com.gc.googleplay.ui.view.MyListView;
import com.gc.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    // private ArrayList<String> data;
    private ArrayList<AppInfo> data;
    // 轮播条数据
    private ArrayList<String> mPictureList;

    // 如果加载数据成功, 就回调此方法, 在主线程运行
    @Override
    public View onCreateSuccessView() {
        // TextView view = new TextView(UIUtils.getContext());
        // view.setText(getClass().getSimpleName());
        MyListView view = new MyListView(UIUtils.getContext());

        // 给listview增加头布局展示轮播条
        HomeHeaderHolder header = new HomeHeaderHolder();
        view.addHeaderView(header.getRootView());// 先添加头布局,再setAdapter

        view.setAdapter(new HomeAdapter(data));

        if (mPictureList != null) {
            // 设置轮播条数据
            header.setData(mPictureList);
        }

        return view;
    }

    // 运行在子线程,可以直接执行耗时网络操作
    @Override
    public LoadingPage.ResultState onLoad() {
        // 请求网络, HttpClient, HttpUrlConnection, XUtils
        // data = new ArrayList<String>();
        // for (int i = 0; i < 20; i++) {
        // data.add("测试数据:" + i);
        // }
        HomeProtocol protocol = new HomeProtocol();
        data = protocol.getData(0);// 加载第一页数据

        mPictureList = protocol.getPictureList();

        return check(data);// 校验数据并返回
    }

    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> data) {
            super(data);
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }

        // 此方法在子线程调用
        @Override
        public ArrayList<AppInfo> onLoadMore() {
            // ArrayList<String> moreData = new ArrayList<String>();
            // for(int i=0;i<20;i++) {
            // moreData.add("测试更多数据:" + i);
            // }
            //
            // SystemClock.sleep(2000);
            HomeProtocol protocol = new HomeProtocol();
            // 20, 40, 60....
            // 下一页数据的位置 等于 当前集合大小
            ArrayList<AppInfo> moreData = protocol.getData(getListSize());

            return moreData;
        }

        // @Override
        // public View getView(int position, View convertView, ViewGroup parent)
        // {
        // ViewHolder holder;
        // if (convertView == null) {
        // //1. 加载布局文件
        // convertView = UIUtils.inflate(R.layout.list_item_home);
        // holder = new ViewHolder();
        // //2. 初始化控件 findViewById
        // holder.tvContent = (TextView) convertView
        // .findViewById(R.id.tv_content);
        //
        // //3. 打一个标记tag
        // convertView.setTag(holder);
        // } else {
        // holder = (ViewHolder) convertView.getTag();
        // }
        //
        // //4. 根据数据来刷新界面
        // String content = getItem(position);
        // holder.tvContent.setText(content);
        //
        // return convertView;
        // }

    }

    static class ViewHolder {
        public TextView tvContent;
    }
}
