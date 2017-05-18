package com.taovo.rjp.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;


/**
 * @author Gimpo create on 2017/5/18 11:47
 * @email : jimbo922@163.com
 */

abstract class BaseDialog<T extends BaseDialog> extends Dialog {
    private static final int NO_ID = -1;

    private Context context;
    OnCommonClickListener<T> onCommonClickListener;
    private int layoutResourceId;
    private float ratio = 1.0f;
    private int gravity;
    private WindowManager.LayoutParams layoutParams;
    private int animStyle;

    BaseDialog(Context context) {
        super(context, R.style.Dialog);
        this.context = context;
    }

    /**
     * 刷新dialog UI
     *
     * @param viewId 控件id
     * @param txt 控件内容
     */
    public void setText(int viewId, String txt) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            if (findView instanceof TextView) {
                ((TextView) findView).setText(txt);
            }
        }
    }

    /**
     * 通过id获取控件的内容
     *
     * @param viewId 控件id
     * @return string
     */
    public String getText(int viewId) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            if (findView instanceof TextView) {
                return ((TextView) findView).getText().toString().trim();
            }
        }
        return "";
    }

    /**
     * 通过id获取控件的select
     *
     * @param viewId 控件id
     * @param state 是否select
     */
    public void setSelected(int viewId, boolean state) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            findView.setSelected(state);
        }
    }

    /**
     * 通过id获取控件的select
     *
     * @param viewId 控件id
     * @return 是否select
     */
    public boolean isSelected(int viewId) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            return findView.isSelected();
        }
        return false;
    }

    /**
     * 通过id获取控件的select
     *
     * @param viewId 控件id
     * @param state 是否check
     */
    public void setChecked(int viewId, boolean state) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            if(findView instanceof CompoundButton) {
                ((CompoundButton)findView).setChecked(state);
            }
        }
    }

    /**
     * 通过id获取控件的check
     *
     * @param viewId 控件id
     * @return 是否check
     */
    public boolean isChecked(int viewId) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            if(findView instanceof CompoundButton) {
                return ((CompoundButton)findView).isChecked();
            }
        }
        return false;
    }

    /**
     * 通过id获取控件的select
     *
     * @param viewId 控件id
     * @param state 是否enable
     */
    public void setEnabled(int viewId, boolean state) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            findView.setEnabled(state);
        }
    }

    /**
     * 通过id获取控件的check
     *
     * @param viewId 控件id
     * @return 是否enable
     */
    public boolean isEnabled(int viewId) {
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            View findView = decorView.findViewById(viewId);
            return findView.isEnabled();
        }
        return false;
    }

    /**
     * 设置 布局id
     *
     * @param layoutResourceId 布局id
     * @return 构建者
     */
    BaseDialog<T> setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
        return this;
    }

    /**
     * 设置dialog的宽度
     *
     * @param ratio 宽度比例
     * @return 构建者
     */
    BaseDialog<T> setDialogWidthRatio(float ratio) {
        this.ratio = ratio;
        return this;
    }

    /**
     * 设置 布局中位置
     *
     * @param gravity 重心
     * @return 构建者
     */
    BaseDialog<T> setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置 布局显示的精确位置
     *
     * @param layoutParams 布局参数
     * @return 构建者
     */
    BaseDialog<T> setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        return this;
    }

    /**
     * 设置 动画style
     *
     * @param animStyle 动画
     * @return 构建者
     */
    BaseDialog<T> setAnimStyle(int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    /**
     * 设置 回调
     *
     * @param onCommonClickListener 监听
     * @return 构建者
     */
    BaseDialog<T> setOnCommonClickListener(OnCommonClickListener<T> onCommonClickListener) {
        this.onCommonClickListener = onCommonClickListener;
        return this;
    }

    /**
     * 创建 dialog
     *
     * @param dialog 传递进来子类设置布局返回
     * @return T
     */
    T initDialog(T dialog) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(layoutResourceId, null);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(gravity);
            window.setWindowAnimations(animStyle);
            window.setAttributes(layoutParams);
        }
        bindClickByLayout(dialog, layout);
        childSpecialEventByLayout(dialog, layout);
        dialog.setContentView(layout, new ViewGroup.LayoutParams((int) (getWindowWidth(context) * ratio), ViewGroup.LayoutParams.WRAP_CONTENT));
        return dialog;
    }

    /**
     * 获取屏幕宽度
     * @param context 上下文
     * @return 宽度
     */
    private int getWindowWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 循环为设置了id的view加上onClick事件
     *
     * @param dialog 弹框
     * @param layout 布局
     */
    private void bindClickByLayout(T dialog, View layout){
        if (layout.getId() != NO_ID && !(layout instanceof AdapterView)) {
            layout.setOnClickListener((v) -> {
                if (onCommonClickListener != null) {
                    onCommonClickListener.onCommonClick(dialog, 0, layout.getId());
                }
            });
        }
        if (layout instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) layout;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View childView = viewGroup.getChildAt(i);
                bindClickByLayout(dialog, childView);
            }
        }
    }

    protected abstract void childSpecialEventByLayout(T dialog, View layout);

    interface OnCommonClickListener<T> {
        void onCommonClick(T dialog, int position, int viewId);
    }
}
