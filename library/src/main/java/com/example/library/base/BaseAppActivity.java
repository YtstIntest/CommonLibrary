package com.example.library.base;



import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.example.library.R;
import com.example.library.appUtils.ActivityUtils;
import com.example.library.widget.NormalTitleBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by xialiang on 2019/4/17.
 * Activity基类
 */

public abstract class BaseAppActivity extends AppCompatActivity {
    //    private Unbinder unbinder;
    protected NormalTitleBar normalTitleBar;
    public Context context;
    public static String TAG = "测试";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.context = this;
        ButterKnife.bind(this);
        ActivityUtils.addActivity(this);
        afterCreate(savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        //设置填充activity_base布局
        super.setContentView(view);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            view.setFitsSystemWindows(true);
        }

        //加载子类Activity的布局
        initDefaultView(layoutResID);
    }


    /**
     * 初始化默认布局的View
     *
     * @param layoutResId 子View的布局id
     */
    private void initDefaultView(int layoutResId) {
        normalTitleBar = (NormalTitleBar) findViewById(R.id.titleBar);
        FrameLayout container = (FrameLayout) findViewById(R.id.fl_activity_child_container);
        View childView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(childView, 0);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    public void setTitle(String title) {
        if (normalTitleBar != null) {
            normalTitleBar.setTitleText(title);
        }
    }

    public void setTitle(CharSequence title) {
        if (normalTitleBar != null) {
            normalTitleBar.setTitleText(title);
        }
    }

    /**
     * 隐藏标题栏
     */
    public void hideTitleBar() {
        if (normalTitleBar != null) {
            normalTitleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示返回按钮
     *
     * @param isShow
     */
    public void isShowBack(boolean isShow) {
        if (normalTitleBar != null) {
            normalTitleBar.setBackVisibility(isShow);
        }
    }

    public void setBackClick(View.OnClickListener listener) {
        if (normalTitleBar != null) {
            normalTitleBar.setBackVisibility(true);
            normalTitleBar.setOnBackListener(listener);
        }
    }

    public void setTitleBarRightImageClick(@DrawableRes int rid, View.OnClickListener listener) {
        if (normalTitleBar != null) {
            normalTitleBar.setRightImageClick(rid, listener);
        }
    }

    public void setTitleBarRightTextClick(String text, View.OnClickListener listener) {
        if (normalTitleBar != null) {
            normalTitleBar.setRightTextClick(text, listener);
        }
    }

    public void setRightTitleVisibility(boolean visible) {
        if (normalTitleBar != null) {
            normalTitleBar.setRightTitleVisibility(visible);
        }
    }

    public void setTitleLeftText(String text) {
        if (normalTitleBar != null) {
            normalTitleBar.setTvLeft(text);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 显示忙碌进度条
     *
     * @param isCancelable 是否允许返回键关闭取消
     */
    public void showProgressBusy(boolean isCancelable) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressDrawable(new BitmapDrawable());
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(isCancelable);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.busy_view);
        }
    }

    /**
     * 显示忙碌进度条
     * @param isCancelable 是否允许返回键关闭取消
     * @param tip  提示文字
     */
    public void showProgressBusy(boolean isCancelable,String tip){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.busy_view,null);
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTV);
        if(!StringUtils.isEmpty(tip)){
            tipTextView.setText(tip);
        }
        progressDialog.setProgressDrawable(new BitmapDrawable());
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(isCancelable);
        if(!progressDialog.isShowing()){
            progressDialog.show();
            progressDialog.setContentView(view);
        }
    }

    /**
     * 关闭忙碌进度条
     */
    public void hideProgressBusy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }

    ResultCallback callback;

    public interface ResultCallback {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public void setCallback(ResultCallback callback) {
        this.callback = callback;
    }
}
