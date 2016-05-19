package com.gc.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.gc.googleplay.R;
import com.gc.googleplay.domain.AppInfo;
import com.gc.googleplay.utils.UIUtils;

/**
 * 首页holder
 */
public class HomeHolder extends BaseHolder<AppInfo> {

    private TextView tvContent;

    @Override
    public View initView() {
        // 1. 加载布局
        View view = UIUtils.inflate(R.layout.list_item_home);
        // 2. 初始化控件
        //tvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
       // tvContent.setText(data.name);
    }

}
