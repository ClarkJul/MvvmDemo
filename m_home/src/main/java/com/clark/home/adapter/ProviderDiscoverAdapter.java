package com.clark.home.adapter;


import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.clark.common.contract.BaseCustomViewModel;
import com.clark.home.adapter.provider.CategoryCardProvider;
import com.clark.home.adapter.provider.ContentBannerProvider;
import com.clark.home.adapter.provider.IDiscoverItemType;
import com.clark.home.adapter.provider.SubjectCardProvider;
import com.clark.home.adapter.provider.ThemeProvider;
import com.clark.home.adapter.provider.TitleProvider;
import com.clark.home.adapter.provider.TopBannerProvider;
import com.clark.home.adapter.provider.VideoCardProvider;
import com.clark.home.discover.bean.CategoryCardBean;
import com.clark.home.discover.bean.SubjectCardBean;
import com.clark.home.discover.bean.viewmodel.BriefCardViewModel;
import com.clark.home.discover.bean.viewmodel.ContentBannerViewModel;
import com.clark.home.discover.bean.viewmodel.TopBannerViewModel;
import com.clark.home.nominate.bean.viewmodel.TitleViewModel;
import com.clark.home.nominate.bean.viewmodel.VideoCardViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * 应用模块:
 * <p>
 * 类描述: 首页-发现 数据控制adapter
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-15
 */
public class ProviderDiscoverAdapter extends BaseProviderMultiAdapter<BaseCustomViewModel> {

    public ProviderDiscoverAdapter() {
        super();
        // 注册具体业务提供者
        addItemProvider(new TopBannerProvider());
        addItemProvider(new CategoryCardProvider());
        addItemProvider(new SubjectCardProvider());
        addItemProvider(new ContentBannerProvider());
        addItemProvider(new TitleProvider());
        addItemProvider(new VideoCardProvider());
        addItemProvider(new ThemeProvider());

    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseCustomViewModel> data, int position) {
        if (data.get(position) instanceof TopBannerViewModel) {
            return IDiscoverItemType.TOP_BANNER_VIEW;
        } else if (data.get(position) instanceof CategoryCardBean) {
            return IDiscoverItemType.CATEGORY_CARD_VIEW;
        } else if (data.get(position) instanceof SubjectCardBean) {
            return IDiscoverItemType.SUBJECT_CARD_VIEW;
        } else if (data.get(position) instanceof TitleViewModel) {
            return IDiscoverItemType.TITLE_VIEW;
        } else if (data.get(position) instanceof ContentBannerViewModel) {
            return IDiscoverItemType.CONTENT_BANNER_VIEW;
        } else if (data.get(position) instanceof VideoCardViewModel) {
            return IDiscoverItemType.VIDEO_CARD_VIEW;
        } else if (data.get(position) instanceof BriefCardViewModel) {
            return IDiscoverItemType.THEME_CARD_VIEW;
        }
        return -1;
    }
}
