package com.gc.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.gc.googleplay.R;
import com.gc.googleplay.domain.CategoryInfo;
import com.gc.googleplay.utils.UIUtils;


/**
 * 分类模块标题holder
 * 
 */
public class TitleHolder extends BaseHolder<CategoryInfo> {

	public TextView tvTitle;

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_title);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

	@Override
	public void refreshView(CategoryInfo data) {
		tvTitle.setText(data.title);
	}

}
