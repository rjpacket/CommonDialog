package com.taovo.rjp.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by rjp on 2017/5/17.
 * email:jimbo922@163.com.
 */
public class CommonDialog extends Dialog {

    private static final int NO_ID = -1;

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 刷新dialog UI
     *
     * @param viewId
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
     * @param viewId
     * @return
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
     * @param state
     * @return
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
     * @param viewId
     * @return
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
     * @param state
     * @return
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
     * @param viewId
     * @return
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
     * @param state
     * @return
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
     * @param viewId
     * @return
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

    public static class Builder {
        private Context context;
        private OnCommonClickListener onCommonClickListener;
        private int layoutResourceId;
        private float ratio = 1.0f;
        private int gravity;
        private Map<Integer, String> uiMap = new HashMap<>();
        private int animStyle;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置 布局id
         *
         * @param layoutResourceId
         * @return
         */
        public Builder setLayoutResourceId(int layoutResourceId) {
            this.layoutResourceId = layoutResourceId;
            return this;
        }

        /**
         * 设置dialog的宽度
         *
         * @param ratio
         * @return
         */
        public Builder setDialogWidthRatio(float ratio) {
            this.ratio = ratio;
            return this;
        }

        /**
         * 设置 布局中TextView的值
         *
         * @param txtId
         * @param txt
         * @return
         */
        public Builder setText(int txtId, String txt) {
            uiMap.put(txtId, txt);
            return this;
        }

        /**
         * 设置 布局中位置
         *
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * 设置 动画style
         *
         * @param animStyle
         * @return
         */
        public Builder setAnimStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        /**
         * 设置 回调
         *
         * @param onCommonClickListener
         * @return
         */
        public Builder setOnCommonClickListener(OnCommonClickListener onCommonClickListener) {
            this.onCommonClickListener = onCommonClickListener;
            return this;
        }

        /**
         * 创建 dialog
         *
         * @return
         */
        public CommonDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            CommonDialog dialog = new CommonDialog(context, R.style.Dialog);
            View layout = inflater.inflate(layoutResourceId, null);
            bindClickEventByLayout(dialog, layout);
            Window window = dialog.getWindow();
            if (window != null) {
                window.setGravity(gravity);
                window.setWindowAnimations(animStyle);
            }
            dialog.setContentView(layout, new ViewGroup.LayoutParams((int) (getWindowWidth(context) * ratio), ViewGroup.LayoutParams.WRAP_CONTENT));
            return dialog;
        }

        /**
         * 循环为设置了id的view加上onClick事件
         *
         * @param dialog 弹框
         * @param layout 布局
         */
        private void bindClickEventByLayout(CommonDialog dialog, View layout) {
            if (layout.getId() != NO_ID) {
                layout.setOnClickListener((v) -> {
                    if (onCommonClickListener != null) {
                        onCommonClickListener.onCommonClick(dialog, layout.getId());
                    }
                });
            }
            if (layout instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) layout;
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    View childView = viewGroup.getChildAt(i);
                    bindClickEventByLayout(dialog, childView);
                }
            }
        }

        /**
         * 获取屏幕宽度
         *
         * @return
         */
        public int getWindowWidth(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
    }

    public interface OnCommonClickListener {
        void onCommonClick(CommonDialog dialog, int viewId);
    }
}
