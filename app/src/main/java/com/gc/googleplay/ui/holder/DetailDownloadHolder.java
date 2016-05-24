package com.gc.googleplay.ui.holder;

import android.view.View;

import com.gc.googleplay.R;
import com.gc.googleplay.domain.AppInfo;
import com.gc.googleplay.utils.UIUtils;


/**
 * 详情页-下载模块
 */
public class DetailDownloadHolder extends BaseHolder<AppInfo> {


	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.layout_detail_download);

		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
	}

}
