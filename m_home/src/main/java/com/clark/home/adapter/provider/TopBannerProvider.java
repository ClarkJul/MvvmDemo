package com.clark.home.adapter.provider;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.R;
import com.clark.home.databinding.HomeItemTopBannerViewBinding;
import com.clark.home.discover.bean.viewmodel.TopBannerViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 应用模块:
 * <p>
 * 类描述: 顶部广告Banner
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-15
 */
public class TopBannerProvider extends BaseItemProvider<BaseCustomViewModel> {
    @Override
    public int getItemViewType() {
        return IDiscoverItemType.TOP_BANNER_VIEW;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_item_top_banner_view;
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder,
                        @Nullable BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        HomeItemTopBannerViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel((TopBannerViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}
