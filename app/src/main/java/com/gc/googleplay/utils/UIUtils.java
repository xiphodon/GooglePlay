package com.gc.googleplay.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

import com.gc.googleplay.global.GooglePlayApplication;

/**
 * 工具类
 */
public class UIUtils {

    /**
     * 获取Context对象
     * @return
     */
    public static Context getContext() {
        return GooglePlayApplication.getContext();
    }

    /**
     * 获取Handler
     * @return
     */
    public static Handler getHandler(){
        return GooglePlayApplication.getHandler();
    }


    //*********************************************************
    //-----------------------加载资源文件------------------------
    //*********************************************************

    /**
     * 获取字符串
     * @param id
     * @return
     */
    public static String getString(int id){
        return getContext().getResources().getString(id);
    }

    /**
     * 获取字符串数组
     * @param id
     * @return
     */
    public static String[] getStringArrary(int id){
        return getContext().getResources().getStringArray(id);
    }

    /**
     * 获取图片
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }

    /**
     * 获取颜色
     * @param id
     * @return
     */
    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    /**
     * 根据id获取颜色的状态选择器
     * @param id
     * @return
     */
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    /**
     * 获取尺寸
     * @param id
     * @return
     */
    public static int getDimen(int id){
        return getContext().getResources().getDimensionPixelSize(id);
    }

    //*********************************************************
    //-----------------------dip、px 转换------------------------
    //*********************************************************

    /**
     * dp转px
     * @param dip dp
     * @return px（像素）
     */
    public static int dip2px(float dip){
        //获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        //四舍五入
        return (int) (dip * density + 0.5f);
    }

    /**
     * px转dp
     * @param px 像素
     * @return dp
     */
    public static float px2dip(int px){
        //获取屏幕密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    //*********************************************************
    //-----------------------加载布局文件------------------------
    //*********************************************************

    /**
     * 加载布局文件
     * @param id
     * @return
     */
    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }

    //*********************************************************
    //-----------------------线程间方法-------------------------
    //*********************************************************


    /**
     * 获取主线程id
     * @return
     */
    public static int mainThreadId(){
        return GooglePlayApplication.getMainThreadId();
    }


    /**
     * 判断此线程是否是主线程
     * @return
     */
    public static boolean isRunOnUIThread(){
        int myTid = Process.myTid();
        return myTid == mainThreadId();
    }

    /**
     * 运行在主线程
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable){
        if(isRunOnUIThread()){
            //已经在主线程中
            runnable.run();
        }else{
            //不在主线程中，借助Handler让其运行在主线程
            getHandler().post(runnable);
        }
    }

}
