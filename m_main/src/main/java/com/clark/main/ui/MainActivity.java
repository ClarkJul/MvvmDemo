package com.clark.main.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.clark.base.activity.MvvmBaseActivity;
import com.clark.base.storage.MmkvHelper;
import com.clark.base.viewmodel.IMvvmBaseViewModel;
import com.clark.common.adapter.ScreenAutoAdapter;
import com.clark.common.router.RouterActivityPath;
import com.clark.common.router.RouterFragmentPath;
import com.clark.main.R;
import com.clark.main.adapter.MainPageAdapter;
import com.clark.main.databinding.MainActivityMainBinding;
import com.clark.main.utils.ColorUtils;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends MvvmBaseActivity<MainActivityMainBinding, IMvvmBaseViewModel> {

    private List<Fragment> fragments;

    private MainPageAdapter adapter;

    private NavigationController mNavigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScreenAutoAdapter.match(this, 375.0f);
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.main_color_bar)
                .navigationBarColor(R.color.main_color_bar)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();
        initView();
        initFragment();
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.main_activity_main;
    }

    @Override
    protected void onRetryBtnClick() {

    }

    private void initView() {
        mNavigationController = viewDataBinding.bottomView.material()
                .addItem(R.drawable.main_home, "首页", ColorUtils.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_community, "社区", ColorUtils.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_notify, "通知", ColorUtils.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_user, "我的", ColorUtils.getColor(this, R.color.main_bottom_check_color))
                .setDefaultColor(ColorUtils.getColor(this, R.color.main_bottom_default_color))
                .enableAnimateLayoutChanges()
                .build();
        mNavigationController.setHasMessage(2, true);
        mNavigationController.setMessageNumber(3, 6);
        adapter = new MainPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewDataBinding.cvContentView.setOffscreenPageLimit(1);
        viewDataBinding.cvContentView.setAdapter(adapter);
        mNavigationController.setupWithViewPager(viewDataBinding.cvContentView);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        //通过ARouter 获取其他组件提供的fragment
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        Fragment communityFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Community.PAGER_COMMUNITY).navigation();
        Fragment moreFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.More.PAGER_MORE).navigation();
        Fragment userFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.User.PAGER_USER).navigation();
        fragments.add(homeFragment);
        fragments.add(communityFragment);
        fragments.add(moreFragment);
        fragments.add(userFragment);
        adapter.setData(fragments);
    }

    public static void start(Context context) {
        MmkvHelper.getInstance().getMmkv().encode("first", false);
        context.startActivity(new Intent(context, MainActivity.class));
    }
}