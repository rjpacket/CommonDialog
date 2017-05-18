package com.taovo.rjp.commondialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.taovo.rjp.commondialog.bean.ListBaseBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CommonDialog dialog1;
    private ListDialog dialog2;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        width = getWidth();
        height = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                dialog0(null, x, y);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void dialog0(View view, float x, float y) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.x = (int) x - width / 2;
        params.y = (int) y - height / 2;
        CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_0, params, (dialog, position, viewId) -> {
            switch (viewId) {
                case R.id.tv_confirm:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<confirm>", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<cancel>", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public void dialog00(View view) {
        CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_0, Gravity.TOP, R.style.dialog_top_anim, (dialog, position, viewId) -> {
            switch (viewId) {
                case R.id.tv_confirm:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<confirm>", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<cancel>", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public void dialog000(View view) {
        CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_0, Gravity.BOTTOM, R.style.dialog_bottom_anim, (dialog, position, viewId) -> {
            switch (viewId) {
                case R.id.tv_confirm:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<confirm>", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "<cancel>", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public void dialog1(View view) {
        if (dialog1 == null) {
            dialog1 = CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_1, null, (dialog, position, viewId) -> {
                switch (viewId) {
                    case R.id.tv_login:
                        String name = dialog.getText(R.id.et_name);
                        String password = dialog.getText(R.id.et_password);
                        Toast.makeText(MainActivity.this, "login:" + name + ";" + password, Toast.LENGTH_SHORT).show();
                        break;
                }
            });
        } else {
            dialog1.show();
        }
    }

    private List<ListBaseBean> models = new ArrayList<>();
    private CommonAdapter<ListBaseBean> mAdapter = new CommonAdapter<ListBaseBean>(this, R.layout.item_list_dialog_list_view, models) {
        @Override
        protected void convert(ViewHolder viewHolder, ListBaseBean item, int position) {
            TextView tvListView = viewHolder.getView(R.id.tv_list_view);
            if (item.isCheck) {
                tvListView.setBackgroundColor(Color.parseColor("#eb1c42"));
            } else {
                tvListView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
    };

    public void dialog2(View view) {
        if (dialog2 == null) {
            for (int i = 0; i < 10; i++) {
                models.add(new ListBaseBean());
            }
            dialog2 = CommonDialogUtils.dialogList(this, R.layout.list_dialog_layout_2, R.id.list_view, mAdapter, 0.8f, Gravity.CENTER, (dialog, position, viewId) -> {
                ListBaseBean listBaseBean = models.get(position);
                listBaseBean.isCheck = true;
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            });
        } else {
            dialog2.show();
        }
    }

    public int getHeight() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public int getWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
