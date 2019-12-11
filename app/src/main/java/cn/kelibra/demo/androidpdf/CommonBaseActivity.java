package cn.kelibra.demo.androidpdf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author: kezy
 * @create_time 2019/10/28
 * @description: 基类Activity
 */
public abstract class CommonBaseActivity extends FragmentActivity {

    protected FragmentActivity mActivity;
    protected View mRootLayout;


    /**
     * 设置layout
     */
    protected abstract int getContentLayout();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getIntentData();

        int layoutId = getContentLayout();
        mRootLayout = LayoutInflater.from(this).inflate(layoutId, null);
        setContentView(mRootLayout);

        initContentView(savedInstanceState);
        initContentView();
        initFooterView();
        initData();

    }

    protected void getIntentData() {
    }


    @CallSuper
    protected void initContentView() {
    }


    protected void initContentView(Bundle savedInstanceState) {
    }

    protected void initFooterView() {
    }


    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
