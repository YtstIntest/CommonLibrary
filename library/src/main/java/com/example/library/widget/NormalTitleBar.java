package com.example.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.appUtils.DisplayUtil;

import androidx.annotation.DrawableRes;


/**
 * @author xialiang
 * @Date 2019/03/09 10:42
 * @Description: 簡單标题栏
 */
public class NormalTitleBar extends RelativeLayout {

    private ImageView ivRight;
    public TextView actionBack, actionTitle, tvRight;
    private RelativeLayout rlCommonTitle;
    private Context context;

    public NormalTitleBar(Context context) {
        super(context, null);
        this.context = context;
    }

    public NormalTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.bar_normal, this);

        actionBack = (TextView) findViewById(R.id.actionBack);

        actionTitle = (TextView) findViewById(R.id.actionTitle);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.image_right);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);
        //setHeaderHeight();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NormalTitleBar);

        int textColor = a.getColor(R.styleable.NormalTitleBar_textColor, 0xFFFFFFFF);
        float titleTextSize = a.getDimension(R.styleable.NormalTitleBar_titleTextSize, DisplayUtil.sp2px(20.0f));
        float subtitleTextSize = a.getDimension(R.styleable.NormalTitleBar_subtitleTextSize, DisplayUtil.sp2px(18.0f));
        String titleText = a.getString(R.styleable.NormalTitleBar_titleText);

        actionTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        actionTitle.setTextColor(textColor);
        actionTitle.setText(titleText);

        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleTextSize);
        actionBack.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleTextSize);

    }

    public void setHeaderHeight() {
        rlCommonTitle.setPadding(0, DisplayUtil.getStatusBarHeight(context), 0, 0);
        rlCommonTitle.requestLayout();
    }

    /**
     * 管理返回按钮
     */
    public void setBackVisibility(boolean visible) {
        if (visible) {
            actionBack.setVisibility(View.VISIBLE);
        } else {
            actionBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable
     */
    public void setTvLeftVisiable(boolean visiable) {
        if (visiable) {
            actionBack.setVisibility(View.VISIBLE);
        } else {
            actionBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        actionBack.setText(tvLeftText);
    }


    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            actionTitle.setVisibility(View.VISIBLE);
        } else {
            actionTitle.setVisibility(View.GONE);
        }
    }

    public void setTitleText(String string) {
        actionTitle.setText(string);
    }

    public void setTitleText(CharSequence string) {
        actionTitle.setText(string);
    }

    public void setTitleText(int string) {
        actionTitle.setText(string);
    }

    public void setTitleColor(int color) {
        actionTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightImagSrc(int id) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }

    public void setRightImageClick(@DrawableRes int rid, OnClickListener listener) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(rid);
        ivRight.setOnClickListener(listener);
    }

    public void setRightTextClick(String text, OnClickListener listener) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(text);
        tvRight.setOnClickListener(listener);
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        actionBack.setCompoundDrawables(getResources().getDrawable(id), null, null, null);
    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        actionBack.setText(str);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightTitle(String text) {
        tvRight.setText(text);
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        actionBack.setOnClickListener(listener);
    }

    public void setOnRightImagListener(OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(color);
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

}
