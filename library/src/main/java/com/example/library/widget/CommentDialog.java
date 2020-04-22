package com.example.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.example.library.R;


/**
 * @author Zhaohao
 * @Description: 评论弹出框
 * @Date 2016/12/06 19:36
 */

public class CommentDialog extends Dialog implements View.OnClickListener {

    public interface OnSendListener {
        void sendComment(String content);
    }

    private Context mContext;
    private EditText mEdittext;
    private Button mTvCancel;
    private Button mTvSend;
    private OnSendListener onSendListener;

    public void setOnSendListener(OnSendListener onSendListener) {
        this.onSendListener = onSendListener;
    }

    public CommentDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = View.inflate(mContext, R.layout.view_comment_dialog, null);

        initView(v);
        setContentView(v);
        setLayout();
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                mEdittext.setFocusableInTouchMode(true);
                mEdittext.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) mEdittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mEdittext, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    private void initView(View v) {
        mEdittext = (EditText) v.findViewById(R.id.et_comment);
        mTvCancel = (Button) v.findViewById(R.id.tv_cancel);
        mTvSend = (Button) v.findViewById(R.id.tv_send);
        mTvCancel.setOnClickListener(this);
        mTvSend.setOnClickListener(this);
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_cancel) {
            dismiss();
        }else if(v.getId() == R.id.tv_send) {
            String content = mEdittext.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                ToastUtils.showShort("请输入评论");
                return;
            }
            if (onSendListener != null) {
                onSendListener.sendComment(content);
            }
        }
    }
}
