package com.example.library.commonAdapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.blankj.utilcode.util.ScreenUtils;
import com.example.library.R;
import com.example.library.appUtils.ImageLoaderUtils;
import com.example.library.appUtils.ViewHolderUtil;
import com.example.library.commonActivity.BigImagePagerActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * des:9宫图适配器
 * Created by xialiang
 * on 2016.09.16:33
 */
public class NinePicturesAdapter extends BaseAblistViewAdapter<String> {
    private boolean showAdd = true;
    private int picturnNum = 0;
    private boolean isDelete = false;//当前是否显示删除按钮
    private OnClickAddListener onClickAddListener;
    private boolean isAdd=true;//当前是否显示添加按钮
    private Context context;

    public NinePicturesAdapter(Context context, int picturnNum, OnClickAddListener onClickAddListener) {
        super(context);
        this.context = context;
        this.picturnNum = picturnNum;
        this.onClickAddListener = onClickAddListener;
        showAdd();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_photo, parent, false);
        }

        int screenWidth = ScreenUtils.getScreenWidth();
        final ImageView imageView = ViewHolderUtil.get(convertView, R.id.img_photo);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(screenWidth/3,screenWidth/3 - 40));
        ImageView imgDelete = ViewHolderUtil.get(convertView, R.id.img_delete);
        final String url = getData().get(position);
        //显示图片
        if (TextUtils.isEmpty(url) && showAdd) {
            ImageLoaderUtils.display(mContext, imageView, R.drawable.ic_add_image);
            imgDelete.setVisibility(View.GONE);
        } else {
            imgDelete.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(mContext, imageView, url);
        }

        autoHideShowAdd();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //再次选择图片
                if (TextUtils.isEmpty(url)) {
                    if (onClickAddListener != null) {
                        onClickAddListener.onClickAdd(position);
                    }
                } else {
                    List<String> pictures = getData();
                    List<String> browsePictures = new ArrayList<>();
                    browsePictures.addAll(pictures);
                    int size = browsePictures.size();
                    if(size < picturnNum&&TextUtils.isEmpty(browsePictures.get(size-1))){
                        browsePictures.remove(size-1);
                    }
                    //放大查看图片
                    BigImagePagerActivity.startImagePagerActivity((Activity) mContext, browsePictures, position);
                }
            }
        });
        //删除按钮
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
                if (!isDelete && getCount() < 1) {
                    add("");
                    isDelete = true;
                }
                boolean hasAdd=false;
                for (int i = 0; i < getData().size(); i++) {
                    if(TextUtils.isEmpty(getData().get(i))){
                        hasAdd=true;
                        break;
                    }
                }
                if(!hasAdd){
                    showAdd();
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public void setData(List<String> d) {
        boolean hasAdd=false;
        for (int i = 0; i < d.size(); i++) {
            if(TextUtils.isEmpty(d.get(i))){
                hasAdd=true;
                break;
            }
        }
        super.setData(d);
        if(!hasAdd){
            showAdd();
        }
    }

    @Override
    public void addAll(List<String> d) {
        if(isAdd){
            HideAdd();
        }
        super.addAll(d);
        showAdd();
    }

    /**
     * 移除add按钮
     */
    public void autoHideShowAdd(){
        int lastPosition=getData().size()-1;
            if(lastPosition==picturnNum&&getData().get(lastPosition)!=null&&TextUtils.isEmpty(getData().get(lastPosition))){
                getData().remove(lastPosition);
                isAdd=false;
                notifyDataSetChanged();
            }else if(!isAdd){
                showAdd();
            }
    }
    /**
     * 移除add按钮
     */
    public void HideAdd(){
        int lastPosition=getData().size()-1;
        if(getData().get(lastPosition)!=null&&TextUtils.isEmpty(getData().get(lastPosition))){
            getData().remove(lastPosition);
            isAdd=false;
            notifyDataSetChanged();
        }
    }
    /**
     * 显示add按钮
     */
    public void showAdd(){
        if(getData().size()<picturnNum){
            addAt(getData().size(),"");
            isAdd=true;
            notifyDataSetChanged();
        }
    }

    /**
     * 获取图片张数
     * @return
     */
   public int getPhotoCount(){
       return isAdd==true?getCount()-1:getCount();
   }

    /**
     * 加号接口
     */
    public interface OnClickAddListener {
        void onClickAdd(int positin);
    }

}