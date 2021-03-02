package com.clark.home.adapter.provider;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.common.router.RouterActivityPath;
import com.clark.home.R;
import com.clark.home.databinding.HomeItemBriefCardViewBinding;
import com.clark.home.discover.bean.viewmodel.BriefCardViewModel;

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
public class ThemeProvider extends BaseItemProvider<BaseCustomViewModel> {
    @Override
    public int getItemViewType() {
        return IDiscoverItemType.THEME_CARD_VIEW;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_item_brief_card_view;
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        HomeItemBriefCardViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.btnAction.setOnClickListener(v -> {
                ARouter.getInstance()
                        .build(RouterActivityPath.User.PAGER_ATTENTION)
                        .navigation();
            });
            binding.setViewModel((BriefCardViewModel) baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}
