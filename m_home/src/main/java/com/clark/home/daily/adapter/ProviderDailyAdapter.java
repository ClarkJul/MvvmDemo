package com.clark.home.daily.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.nominate.adapter.provider.FollowCardProvider;
import com.clark.home.nominate.adapter.provider.NominateItemType;
import com.clark.home.nominate.adapter.provider.SingleTitleProvider;
import com.clark.home.nominate.bean.viewmodel.FollowCardViewModel;
import com.clark.home.nominate.bean.viewmodel.SingleTitleViewModel;


import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * 应用模块:
 * <p>
 * 类描述: adapter 提供者
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-14
 */
public class ProviderDailyAdapter extends BaseProviderMultiAdapter<BaseCustomViewModel> {

    public ProviderDailyAdapter() {
        super();
        addItemProvider(new FollowCardProvider());
        addItemProvider(new SingleTitleProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseCustomViewModel> data, int position) {
        if (data.get(position) instanceof FollowCardViewModel) {
            return NominateItemType.FOLLOW_CARD_VIEW;
        } else if (data.get(position) instanceof SingleTitleViewModel) {
            return NominateItemType.SINGLE_TITLE_VIEW;
        }
        return -1;
    }
}