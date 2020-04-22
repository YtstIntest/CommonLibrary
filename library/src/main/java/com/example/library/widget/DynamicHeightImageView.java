package com.example.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Zhaohao
 * @Description: 动态高度的ImageView 用于图片瀑布流
 * @Date 2016/12/08 15:28
 */
public class DynamicHeightImageView extends ImageView {

    /**
     * 图片高宽比（高/宽）
     */
    private double hwRatio ;

    public DynamicHeightImageView(Context context , AttributeSet attrs) {
        super(context , attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context) ;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec , int heightMeasureSpec)
    {
        //获取当前ImageView分配的宽度(即Item项的宽度)
        int widthSize = MeasureSpec. getSize(widthMeasureSpec) ;
        if (widthSize!=0 && hwRatio != 0 )
        {
            //根据高宽比，计算出ImagView需要的高度widthSize* hwRatio，并设置其大小
            setMeasuredDimension(widthSize , ( int )(widthSize* hwRatio ));
        }
        else{
            super .onMeasure(widthMeasureSpec, heightMeasureSpec) ;
        }
    }

    @Override
    public void setImageResource (int resId) {
        super.setImageResource(resId) ;
        //获取图片的高宽比（高/宽）
        BitmapFactory.Options options = new BitmapFactory.Options() ;
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeResource(getResources() , resId, options) ;
        hwRatio = options. outHeight /(double )options. outWidth;
        bmp.recycle();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        //获取图片的高宽比（高/宽）
        hwRatio = bm. getHeight() /(double )bm.getWidth();
        bm.recycle();
    }
}
