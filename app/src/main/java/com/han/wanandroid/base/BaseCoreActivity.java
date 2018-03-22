package com.han.wanandroid.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.han.wanandroid.R;
import com.han.wanandroid.tools.CustomDialog;
import com.han.wanandroid.tools.CustomProgressDialog;
import com.han.wanandroid.tools.CustomProgressDialogHelper;
import com.han.wanandroid.utils.baseutils.ToastUtils;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public abstract class BaseCoreActivity extends BaseRxActivity {

    //  加载进度的dialog
    private CustomProgressDialog mProgressDialog;

    //  对话框
    private CustomDialog dialog;

    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = CustomProgressDialogHelper.createDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public void showToast(String msg){
        ToastUtils.show(msg);
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }
    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param color    color xml文件下的颜色
     */
    public void setStatusBarColor(Activity activity, int color) {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }*/
        if (Build.VERSION.SDK_INT >= 21) {
            Window statusBar = getWindow();
            statusBar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            statusBar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBar.setStatusBarColor(color);
        }
    }


    /**
     * @param content         内容
     * @param confirm         确定键文字
     * @param cancel          取消键文字
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    public void showTwoButtonDialog(String content, String confirm, String cancel,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(R.style.IdeaDialog)
                .setContent(content)
                .addConfirmClickListener(confirm, confirmListener)
                .addCancelClickListener(cancel, cancelListener)
                .build();
        dialog.show();
    }

    /**
     * @param content         内容
     * @param confirm         确定键文字
     * @param cancel          取消键文字
     * @param confirmColor    确定键颜色
     * @param cancelColor     取消键颜色
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    public void showTwoButtonDialog(String content, String confirm, String cancel,
                                    @ColorInt int confirmColor, @ColorInt int cancelColor,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(R.style.IdeaDialog)
                .setContent(content)
                .setConfirmColor(confirmColor)
                .setCancelColor(cancelColor)
                .addConfirmClickListener(confirm, confirmListener)
                .addCancelClickListener(cancel, cancelListener)
                .build();
        dialog.show();
    }

    /**
     * @param content         内容
     * @param confirm         按钮文字
     * @param confirmListener 按钮监听
     */
    public void showOneButtonDialog(String content, String confirm, View.OnClickListener confirmListener) {
        dialog = new CustomDialog.Builder(this)
                .setTheme(R.style.IdeaDialog)
                .setContent(content)
                .addConfirmClickListener(confirm, confirmListener)
                .showOneButton()
                .build();
        dialog.show();
    }


    /**
     * create custom dialog
     * 可以定制任意的dialog样式
     *
     * @param dialogLayoutRes    dialog布局资源文件
     * @param cancelTouchOutside 点击外部是否可以取消
     * @return
     */
    public View createDialog(@LayoutRes Integer dialogLayoutRes, boolean cancelTouchOutside) {
        if (dialogLayoutRes == null) {
            dialogLayoutRes = R.layout.custom_dialog;
        }
        dialogView = LayoutInflater.from(this).inflate(dialogLayoutRes, null);
        //  计算dialog宽高
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        dialogView.measure(measureSpec, measureSpec);
        int height = dialogView.getMeasuredHeight();
        int width = dialogView.getMeasuredWidth();

        dialog = new CustomDialog.Builder(this)
                .setTheme(R.style.IdeaDialog)
                .setHeightPx(height)
                .setWidthPx(width)
                .cancelTouchOutside(cancelTouchOutside)
                .setDialogLayout(dialogView).build();
        return dialogView;
    }

    /**
     * 显示dialog
     */
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏dialog
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
