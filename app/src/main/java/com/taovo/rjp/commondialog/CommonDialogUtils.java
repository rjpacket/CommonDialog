package com.taovo.rjp.commondialog;

import android.content.Context;
import android.view.Gravity;

import java.util.Map;

/**
 * Created by rjp on 2017/5/16.
 */
public class CommonDialogUtils {

    /**
     * 通用 的dialog
     *
     * @param context
     * @param layoutResourceId
     * @param uiMap
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, Map<Integer, String> uiMap, CommonDialog.OnCommonClickListener onCommonClickListener) {
        return dialog(context, layoutResourceId, 0.8f, Gravity.CENTER, uiMap, onCommonClickListener);
    }

    /**
     * 通用 的dialog
     *
     * @param context
     * @param layoutResourceId
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, CommonDialog.OnCommonClickListener onCommonClickListener) {
        return dialog(context, layoutResourceId, 0.8f, Gravity.CENTER, null, onCommonClickListener);
    }

    /**
     * 通用 的dialog
     *
     * @param context
     * @param layoutResourceId
     * @param ratio
     * @param onCommonClickListener
     */
    public static CommonDialog dialog(Context context, int layoutResourceId, float ratio, int gravity, Map<Integer, String> uiMap, CommonDialog.OnCommonClickListener onCommonClickListener) {
        CommonDialog.Builder builder = new CommonDialog.Builder(context);
        builder.setLayoutResourceId(layoutResourceId)
                .setOnCommonClickListener(onCommonClickListener)
                .setDialogWidthRatio(ratio)
                .setGravity(gravity);
        if (uiMap != null) {
            for (Map.Entry<Integer, String> entry : uiMap.entrySet()) {
                builder.setText(entry.getKey(), entry.getValue());
            }
        }
        CommonDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }
}
