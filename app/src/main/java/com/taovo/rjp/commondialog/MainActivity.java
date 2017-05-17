package com.taovo.rjp.commondialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CommonDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dialog0(View view) {
        CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_0, (dialog, viewId) -> {
            switch (viewId) {
                case R.id.tv_confirm:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_cancel:
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public void dialog1(View view) {
        if(dialog1 == null) {
            dialog1 = CommonDialogUtils.dialog(this, R.layout.common_dialog_layout_1, (dialog, viewId) -> {
                switch (viewId) {
                    case R.id.tv_login:
                        String name = dialog.getText(R.id.et_name);
                        String password = dialog.getText(R.id.et_password);
                        Toast.makeText(MainActivity.this, "login:" + name + ";" + password, Toast.LENGTH_SHORT).show();
                        break;
                }
            });
        }else{
            dialog1.show();
        }
    }
}
