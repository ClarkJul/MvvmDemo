package com.clark.home.discover;


import com.clark.base.model.BaseModel;
import com.clark.base.model.IModelListener;
import com.clark.base.viewmodel.MvmBaseViewModel;
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
public class DiscoverViewModel extends MvmBaseViewModel<IDiscoverView, DiscoverModel>
        implements IModelListener<ArrayList<BaseCustomViewModel>> {

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<BaseCustomViewModel> data) {
        if (getPageView() != null) {
            if (data != null && data.size() > 0) {
                getPageView().onDataLoadFinish(data, false);
            } else {
                getPageView().showEmpty();
            }
        }
    }

    @Override
    public void onLoadFail(BaseModel model, String prompt) {
        if (getPageView() != null) {
            getPageView().showFailure(prompt);
        }
    }

    public void tryToRefresh() {
        model.load();
    }

    @Override
    protected void initModel() {
        model = new DiscoverModel();
        model.register(this);
        model.getCacheDataAndLoad();
    }

    @Override
    public void detachUi() {
        super.detachUi();
        if (model != null) {
            model.unRegister(this);
        }

    }
}
