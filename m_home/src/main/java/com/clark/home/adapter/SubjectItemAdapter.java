package com.clark.home.adapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.clark.home.databinding.HomeItemCategoryItemSubjectCardViewBinding;
import com.clark.home.discover.bean.SquareCard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 应用模块:
 * <p>
 * 类描述: 专题adapter
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-15
 */
public class SubjectItemAdapter extends BaseQuickAdapter<SquareCard, BaseViewHolder> {

    public SubjectItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable SquareCard squareCard) {
        if (squareCard == null) {
            return;
        }
        HomeItemCategoryItemSubjectCardViewBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setViewModel(squareCard);
            binding.executePendingBindings();
        }
    }
}
