/*
package com.zwh.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.view.SystemBarTintManager;
import com.zwh.common.R;
import com.zwh.common.tools.daynightmodeutils.ChangeModeController;
import com.zwh.common.tools.statusBar.StatusBarCompat;

import butterknife.ButterKnife;


*/
/**
 *
 * @author Zhaohao
 * @Date 2017/03/09 8:50
 * @Description: Activity基类(包含Toolbar控件的标题栏)
 *//*

public abstract class BaseToolbarActivity extends AppCompatActivity {

    public Context context;
    public static final String TAG = BaseToolbarActivity.class.getSimpleName();
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private TextView toolbarTitle;
    private TextView toolbarSubTitle;

    private boolean isShowBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSystemBarTint();
        super.onCreate(savedInstanceState);
        //设置昼夜主题
//        initTheme();
        setContentView(getContentLayoutId());
        context = this;
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);

        if(toolbar != null){
            //将Toolbar显示到界面
            setSupportActionBar(toolbar);
        }

        if(toolbarTitle != null){
            //getTitle()的值是activity的android:lable属性值
            toolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        initView();

        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        */
/**
         * 判断是否有Toolbar,并默认显示返回按钮
         *//*

        if (null != getToolbar() && isShowBack) {
            showBack();
        }
    }

    */
/**
     * 设置头部标题
     * @param title
     *//*

    public void setTitle(CharSequence title) {
        if(toolbarTitle != null){
            toolbarTitle.setText(title);
        }else{
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }


    */
/**
     * 设置右边标题
     * @param isShow  是否可见 默认不可见
     * @param title 标题
     * @param listener 点击事件
     *//*

    public void setSubTitle(boolean isShow, CharSequence title,  View.OnClickListener listener){
        if(toolbarSubTitle != null){
            if(isShow){
                toolbarSubTitle.setVisibility(View.VISIBLE);
            }else{
                toolbarSubTitle.setVisibility(View.GONE);
            }
            toolbarSubTitle.setText(title);
            toolbarSubTitle.setOnClickListener(listener);
        }
    }


    */
/**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     *//*

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    */
/**
     * 版本号小于21的后退按钮图片
     *//*

    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.drawable.icon_arrow_back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    */
/**
     * 是否显示后退按钮,默认显示
     *
     * @return
     *//*

    public void isShowBack(boolean showBack) {
        isShowBack = showBack;
    }



    //设置布局文件
    protected abstract int getContentLayoutId();

    //初始化view
    protected abstract void initView();

    */
/**
     * 初始化数据
     *//*

    protected abstract void initData();

    */
/** 子类可以重写改变状态栏颜色 *//*

    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    */
/** 子类可以重写决定是否使用透明状态栏 *//*

    protected boolean translucentStatusBar() {
        return false;
    }

    */
/** 设置状态栏颜色 *//*

    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }

    */
/**
     * 设置主题
     *//*

    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    */
/** 获取主题色 *//*

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    */
/** 获取深主题色 *//*

    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    */
/**
     * 沉浸状态栏（4.4以上系统有效）
     *//*

    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

    */
/**
     * 显示Toase提示
     * @param msg
     *//*

    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }




    */
/**
     * 显示忙碌进度条
     * @param isCancelable 是否允许返回键关闭取消
     *//*

    public void showProgressBusy(boolean isCancelable){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressDrawable(new BitmapDrawable());
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(isCancelable);
        if(!progressDialog.isShowing()){
            progressDialog.show();
            progressDialog.setContentView(R.layout.busy_view);
        }
    }

    */
/**
     * 关闭忙碌进度条
     *//*

    public void hideProgressBusy(){
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
*/
