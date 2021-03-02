package com.clark.home.adapter.provider;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.common.recyclerview.RecyclerItemDecoration;
import com.clark.common.utils.DensityUtils;
import com.clark.home.R;
import com.clark.home.adapter.SubjectItemAdapter;
import com.clark.home.databinding.HomeItemSubjectCardViewBinding;
import com.clark.home.discover.bean.SquareCard;
import com.clark.home.discover.bean.SubjectCardBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-15
 */
public class SubjectCardProvider extends BaseItemProvider<BaseCustomViewModel> {
    @Override
    public int getItemViewType() {
        return IDiscoverItemType.SUBJECT_CARD_VIEW;
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        HomeItemSubjectCardViewBinding binding = DataBindingUtil.bind(viewHolder.itemView);

        binding.rvCategoryView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvCategoryView.setLayoutManager(gridLayoutManager);
        binding.rvCategoryView.addItemDecoration(new RecyclerItemDecoration(0, 0, DensityUtils.dip2px(getContext(), 5), DensityUtils.dip2px(getContext(), 5)));
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_item_subject_card_view;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseCustomViewModel baseCustomViewModel) {
        if (baseCustomViewModel == null) {
            return;
        }
        HomeItemSubjectCardViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            SubjectCardBean cardBean = (SubjectCardBean) baseCustomViewModel;
            binding.tvTitle.setText(cardBean.getData().getHeader().getTitle());
            binding.tvActionTitle.setText(cardBean.getData().getHeader().getRightText());
            ArrayList<SquareCard> dataList = new ArrayList<>();
            for (int i = 0; i < cardBean.getData().getItemList().size(); i++) {
                dataList.add(cardBean.getData().getItemList().get(i).getData());
            }
            SubjectItemAdapter adapter = new SubjectItemAdapter(R.layout.home_item_category_item_subject_card_view);
            binding.rvCategoryView.setAdapter(adapter);
            adapter.setNewData(dataList);
        }
    }
}
