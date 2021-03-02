package com.clark.home.discover;


import com.clark.base.activity.IBaseView;
import com.clark.common.contract.BaseCustomViewModel;

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
public interface IDiscoverView extends IBaseView {

    /**
     * 数据加载完成
     *
     * @param viewModels data
     */
    void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels, boolean isEmpty);

}
