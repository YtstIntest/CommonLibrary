package com.example.library.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.R;


/**
 * @author Zhaohao
 * @Description: 空界面显示
 * @date 2017-08-27 16:21
 */

public class EmptyViewHelper {
    private Context context;
    private ImageView imageView;
    private TextView tipTextView;
    private View view;
    public EmptyViewHelper(Context context) {
        this.context = context;
        initView();
    }

    public void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.empty_view,null);
        imageView = (ImageView) view.findViewById(R.id.imgIV);
        tipTextView = (TextView) view.findViewById(R.id.tipTV);
    }

    public View setContentView(int imgID, String tip){
        this.imageView.setBackgroundResource(imgID);
        this.tipTextView.setText(tip);
        return view;
    }
}
