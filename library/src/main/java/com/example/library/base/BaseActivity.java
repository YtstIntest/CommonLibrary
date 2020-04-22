package com.example.library.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.library.R;
import com.example.library.tools.daynightmodeutils.ChangeModeController;
import com.example.library.tools.statusBar.StatusBarCompat;
import com.lzy.imagepicker.view.SystemBarTintManager;


import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


/**
 *
 * @author xialiang
 * @Date 2019/03/09 8:50
 * @Description: Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Context context;
    public static String TAG ="Debug:";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSystemBarTint();
        super.onCreate(savedInstanceState);
        //设置昼夜主题
//        initTheme();
        setContentView(initLayoutId());
        context = this;
        ButterKnife.bind(this);
        initView();
        initData();
    }


    //设置布局文件
    public abstract int initLayoutId();

    //初始化view
    public abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }

    /** 设置状态栏颜色 */
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

    /**
     * 设置主题
     */
    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /** 获取深主题色 */
    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * 显示Toase提示
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示忙碌进度条
     * @param isCancelable 是否允许返回键关闭取消
     */
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

    /**
     * 关闭忙碌进度条
     */
    public void hideProgressBusy(){
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
