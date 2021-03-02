package com.clark.home.discover;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.clark.base.fragment.MvvmLazyFragment;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.R;
import com.clark.home.adapter.ProviderDiscoverAdapter;
import com.clark.home.databinding.HomeFragmentFindMoreBinding;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.ArrayList;

public class DiscoverFragment extends MvvmLazyFragment<HomeFragmentFindMoreBinding, DiscoverViewModel> implements IDiscoverView {

    private ProviderDiscoverAdapter adapter;

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {
        // 确定Item的改变不会影响RecyclerView的宽高
        viewDataBinding.rvDiscoverView.setHasFixedSize(true);
        viewDataBinding.rvDiscoverView
                .setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProviderDiscoverAdapter();
        adapter.addFooterView(getFooterView());
        viewDataBinding.rvDiscoverView.setAdapter(adapter);
        viewDataBinding.refreshLayout
                .setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryToRefresh();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
    }

    private View getFooterView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.home_item_foote_view, viewDataBinding.rvDiscoverView, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_find_more;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected DiscoverViewModel getViewModel() {
        return ViewModelProviders.of(this).get(DiscoverViewModel.class);
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels,
                                 boolean isEmpty) {
        if (isEmpty) {
            viewDataBinding.refreshLayout.finishRefresh(false);
        } else {
            viewDataBinding.refreshLayout.finishRefresh(true);
        }
        adapter.setNewData(viewModels);
        showContent();
    }
}
