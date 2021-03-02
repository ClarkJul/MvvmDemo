package com.clark.home.adapter.provider;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.R;
import com.clark.home.databinding.HomeItemTitleLeftRightViewBinding;
import com.clark.home.nominate.bean.viewmodel.TitleViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-15
 */
public class TitleProvider extends BaseItemProvider<BaseCustomViewModel> {
    @Override
    public int getItemViewType() {
        return IDiscoverItemType.TITLE_VIEW;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_item_title_left_right_view;
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder,
                                    int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder,
                        @Nullable BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        HomeItemTitleLeftRightViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel((TitleViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}