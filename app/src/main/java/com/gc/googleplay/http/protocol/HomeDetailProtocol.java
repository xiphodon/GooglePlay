package com.gc.googleplay.http.protocol;

import com.gc.googleplay.domain.AppInfo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/*
 * 首页详情页网络访问
 */
public class HomeDetailProtocol extends BaseProtocol<AppInfo> {

	public String packageName;

	public HomeDetailProtocol(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String getKey() {
		return "detail";
	}

	@Override
	public String getParams() {
		return "&packageName=" + packageName;
	}

	// 补充字段, 供应用详情页使用
	public String author;
	public String date;
	public String downloadNum;
	public String version;
	public ArrayList<AppInfo.SafeInfo> safe;
	public ArrayList<String> screen;

	@Override
	public AppInfo parseData(String result) {
		try {
			JSONObject jo = new JSONObject(result);

			AppInfo info = new AppInfo();
			info.des = jo.getString("des");
			info.downloadUrl = jo.getString("downloadUrl");
			info.iconUrl = jo.getString("iconUrl");
			info.id = jo.getString("id");
			info.name = jo.getString("name");
			info.packageName = jo.getString("packageName");
			info.size = jo.getLong("size");
			info.stars = (float) jo.getDouble("stars");

			info.author = jo.getString("author");
			info.date = jo.getString("date");
			info.downloadNum = jo.getString("downloadNum");
			info.version = jo.getString("version");

			JSONArray ja = jo.getJSONArray("safe");

			// 解析安全信息
			ArrayList<AppInfo.SafeInfo> safe = new ArrayList<AppInfo.SafeInfo>();
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo1 = ja.getJSONObject(i);

				AppInfo.SafeInfo safeInfo = new AppInfo.SafeInfo();
				safeInfo.safeDes = jo1.getString("safeDes");
				safeInfo.safeDesUrl = jo1.getString("safeDesUrl");
				safeInfo.safeUrl = jo1.getString("safeUrl");

				safe.add(safeInfo);
			}

			info.safe = safe;

			// 解析截图信息
			JSONArray ja1 = jo.getJSONArray("screen");
			ArrayList<String> screen = new ArrayList<String>();
			for (int i = 0; i < ja1.length(); i++) {
				String pic = ja1.getString(i);
				screen.add(pic);
			}

			info.screen = screen;

			return info;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

}
