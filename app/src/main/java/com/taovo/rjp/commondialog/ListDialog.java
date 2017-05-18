package com.taovo.rjp.commondialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.taovo.rjp.commondialog.bean.ListBaseBean;
import com.zhy.adapter.abslistview.CommonAdapter;

/**
 * @author Gimpo create on 2017/5/18 11:47
 * @email : jimbo922@163.com
 */

class ListDialog<T extends ListBaseBean> extends BaseDialog<ListDialog> {
    private int listViewId;
    private CommonAdapter<T> mAdapter;

    ListDialog(Context context) {
        super(context);
    }

    ListDialog<T> setListViewId(int listViewId) {
        this.listViewId = listViewId;
        return this;
    }

    ListDialog<T> setAdapter(CommonAdapter<T> mAdapter) {
        this.mAdapter = mAdapter;
        return this;
    }

    @Override
    protected void childSpecialEventByLayout(ListDialog dialog, View layout) {
        ListView mListView = (ListView) layout.findViewById(listViewId);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onCommonClickListener != null) {
                    onCommonClickListener.onCommonClick(dialog, position, (int) id);
                }
            }
        });
    }
}
