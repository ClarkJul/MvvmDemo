package com.clark.home.daily;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.clark.base.fragment.MvvmBaseFragment;
import com.clark.base.fragment.MvvmLazyFragment;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.R;
import com.clark.home.daily.adapter.ProviderDailyAdapter;
import com.clark.home.databinding.HomeFragmentDailyBinding;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.ArrayList;

public class DailyFragment extends MvvmLazyFragment<HomeFragmentDailyBinding, DailyViewModel> implements IDailyView {
    private ProviderDailyAdapter adapter;

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {
        // 确定Item的改变不会影响RecyclerView的宽高
        viewDataBinding.rvDailyView.setHasFixedSize(true);
        viewDataBinding.rvDailyView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProviderDailyAdapter();
        viewDataBinding.rvDailyView.setAdapter(adapter);
        viewDataBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout.setEnableLoadMore(true);
        viewDataBinding.refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryToRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadMore();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_daily;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected DailyViewModel getViewModel() {
        return ViewModelProviders.of(this).get(DailyViewModel.class);
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels, boolean isFirstPage) {
        if (isFirstPage) {
            adapter.setNewData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(true);
        } else {
            adapter.addData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishLoadMore(true);
        }
    }

    @Override
    public void onLoadMoreFailure(String message) {
        viewDataBinding.refreshLayout.finishLoadMore(false);
    }

    @Override
    public void onLoadMoreEmpty() {
        viewDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
    }
}