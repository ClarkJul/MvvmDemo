package com.clark.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.clark.base.fragment.MvvmLazyFragment;
import com.clark.base.viewmodel.IMvvmBaseViewModel;
import com.clark.common.router.RouterFragmentPath;
import com.clark.home.adapter.HomeFragmentPageAdapter;
import com.clark.home.daily.DailyFragment;
import com.clark.home.databinding.HomeFragmentHomeBinding;
import com.clark.home.discover.DiscoverFragment;
import com.clark.home.nominate.NominateFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends MvvmLazyFragment<HomeFragmentHomeBinding, IMvvmBaseViewModel> {
    private HomeFragmentPageAdapter pageAdapter;

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(DiscoverFragment.newInstance());
        fragments.add(NominateFragment.newInstance());
        fragments.add(DailyFragment.newInstance());
        pageAdapter.setData(fragments);
        viewDataBinding.vpHomeContent.setCurrentItem(1);
    }


    private void initView() {
        pageAdapter = new HomeFragmentPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewDataBinding.vpHomeContent.setAdapter(pageAdapter);
        viewDataBinding.tabLayout.setupWithViewPager(viewDataBinding.vpHomeContent);
        viewDataBinding.vpHomeContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(viewDataBinding.tabLayout));
        viewDataBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewDataBinding.vpHomeContent.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
        viewDataBinding.ivNetSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                VideoHeaderBean videoHeaderBean = new VideoHeaderBean("1","1","1",0,9,"1","1","1","1");
//                LiveDatabus.getInstance().withSticky("player",VideoHeaderBean.class).setValue(videoHeaderBean);
//                startActivity(new Intent(getContext(), VideoInfoAndPlayerActivity.class));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}