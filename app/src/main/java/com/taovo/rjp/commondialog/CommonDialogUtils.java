package com.taovo.rjp.commondialog;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.taovo.rjp.commondialog.bean.ListBaseBean;
import com.zhy.adapter.abslistview.CommonAdapter;


/**
 * @author Gimpo create on 2017/5/18 11:47
 * @email : jimbo922@163.com
 */
public class CommonDialogUtils {

    /**
     * list 的dialog
     *
     * @param context
     * @param layoutResourceId
     * @param ratio
     * @param onCommonClickListener
     */
    public static ListDialog dialogList(Context context, int layoutResourceId, int listViewId, CommonAdapter<ListBaseBean> mAdapter, float ratio, int gravity, BaseDialog.OnCommonClickListener<ListDialog> onCommonClickListener) {
        ListDialog<ListBaseBean> dialog = new ListDialog<>(context);
        dialog.setListViewId(listViewId);
        dialog.setAdapter(mAdapter);
        dialog.setLayoutResourceId(layoutResourceId)
                .setOnCommonClickListener(onCommonClickListener)
                .setDialogWidthRatio(ratio)
                .setGravity(gravity)
                .initDialog(dialog)
                .show();
        return dialog;
    }

    /**
     * 通用 的dialog 固定宽度0.8 中间显示
     *
     * @param context
     * @param layoutResourceId
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, WindowManager.LayoutParams params, BaseDialog.OnCommonClickListener<CommonDialog> onCommonClickListener) {
        return dialog(context, layoutResourceId, 0.3f, Gravity.CENTER, 0, params, onCommonClickListener);
    }

    /**
     * 通用 的dialog 固定宽度0.8 中间显示
     *
     * @param context
     * @param layoutResourceId
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, int gravity, int animStyle, BaseDialog.OnCommonClickListener<CommonDialog> onCommonClickListener) {
        return dialog(context, layoutResourceId, 1.0f, gravity, animStyle, null, onCommonClickListener);
    }


    /**
     * 通用 的dialog
     *
     * @param context
     * @param layoutResourceId
     * @param ratio
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, float ratio, int gravity, int animStyle, WindowManager.LayoutParams params, BaseDialog.OnCommonClickListener<CommonDialog> onCommonClickListener) {
        CommonDialog dialog = new CommonDialog(context);
        dialog.setLayoutResourceId(layoutResourceId)
                .setOnCommonClickListener(onCommonClickListener)
                .setDialogWidthRatio(ratio)
                .setGravity(gravity)
                .setLayoutParams(params)
                .setAnimStyle(animStyle)
                .initDialog(dialog)
                .show();
        return dialog;
    }
}
