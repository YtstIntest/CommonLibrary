package com.example.library.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.appUtils.DisplayUtil;


/**
 * @Description:  搜索栏
 * @author xialiang
 * @Date 2016/12/12 16:40
 */
public class SearchTitleBar extends RelativeLayout {

    private ImageView ivRight;
    private TextView ivBack,tvRight;
    private EditText searchEditText;
    private RelativeLayout rlCommonTitle;
    private Context context;

    public SearchTitleBar(Context context) {
        super(context, null);
        this.context = context;
    }

    public SearchTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.bar_search, this);
        ivBack = (TextView) findViewById(R.id.actionBack);
        searchEditText = (EditText) findViewById(R.id.et_searchkey);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.image_right);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);
        //setHeaderHeight();
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
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧是否可见
     * @param visiable
     */
    public void setBackLeftVisiable(boolean visiable){
        if (visiable){
            ivBack.setVisibility(View.VISIBLE);
        }else{
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText){
        ivBack.setText(tvLeftText);
    }


//    /**
//     * 管理标题
//     */
//    public void setTitleVisibility(boolean visible) {
//        if (visible) {
//            tvTitle.setVisibility(View.VISIBLE);
//        } else {
//            tvTitle.setVisibility(View.GONE);
//        }
//    }

    /**
     * 返回输入查询关键字
     * @return
     */
    public String getSearchKeyText() {
        return searchEditText.getText().toString();
    }

    /**
     * 设置查询关键字
     * @param string
     */
    public void setSearchKeyText(int string) {
        searchEditText.setText(string);
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

    /**
     * 获取右按钮
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
        ivBack.setCompoundDrawables(getResources().getDrawable(id),null,null,null);
    }
    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        ivBack.setText(str);
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
        ivBack.setOnClickListener(listener);
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
