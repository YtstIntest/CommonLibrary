package com.example.library.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.example.library.R;
import com.example.library.widget.NormalTitleBar;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ZHT on 2017/4/18.
 * Fragment基类
 */

public abstract class BaseAppFragment extends Fragment {

    private View mView;
    public static String TAG = "调试日志:";
    private ProgressDialog progressDialog;
    private NormalTitleBar normalTitleBar;
    public Context context;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_base, container, false);
        this.context = getActivity();
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (null != parent) {
            parent.removeView(mView);
        }

        addChildView(inflater);

        unbinder = ButterKnife.bind(this, mView);

//        initDialog();

        afterCreate(savedInstanceState);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 添加子Fragment的布局文件
     * @param inflater
     */
    private void addChildView(LayoutInflater inflater) {
        normalTitleBar = (NormalTitleBar) mView.findViewById(R.id.titleBar);
        FrameLayout container = (FrameLayout) mView.findViewById(R.id.fl_fragment_child_container);
        View child = inflater.inflate(getLayoutId(), null);
        container.addView(child, 0);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    public void setTitle(String title){
        if(normalTitleBar != null){
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
     * @param isShow
     */
    public void isShowBack(boolean isShow){
        if(normalTitleBar != null){
            normalTitleBar.setBackVisibility(isShow);
        }
    }

    public void setBackClick(View.OnClickListener listener){
        if(normalTitleBar != null){
            normalTitleBar.setBackVisibility(true);
            normalTitleBar.setOnBackListener(listener);
        }
    }

    public void setTitleBarRightImageClick(@DrawableRes int rid, View.OnClickListener listener){
        if(normalTitleBar != null){
            normalTitleBar.setRightImageClick(rid,listener);
        }
    }

    public void setTitleBarRightTextClick(String text,View.OnClickListener listener){
        if(normalTitleBar != null){
            normalTitleBar.setRightTextClick(text,listener);
        }
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
    public void hideProgressBusy(){
        if(progressDialog != null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
        }

    }

}
