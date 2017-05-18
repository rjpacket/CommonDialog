package com.taovo.rjp.commondialog;

import android.content.Context;
import android.view.View;


/**
 * @author Gimpo create on 2017/5/18 11:47
 * @email : jimbo922@163.com
 */
class CommonDialog extends BaseDialog<CommonDialog> {

    CommonDialog(Context context) {
        super(context);
    }

    @Override
    protected void childSpecialEventByLayout(CommonDialog dialog, View layout) {
        //通用的dialog无操作
    }
}
