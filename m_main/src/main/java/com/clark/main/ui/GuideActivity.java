package com.clark.main.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.clark.base.utils.ToastUtil;
import com.clark.main.R;
import com.clark.main.bean.CustomBean;
import com.clark.main.viewholder.CustomPageViewHolder;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.TransformerStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuideActivity extends BaseDataActivity {
    private static final int ANIMATION_DURATION = 1300;
    BannerViewPager bvpGuideMain;
    TextView tvGuide;
    TextView tvStart;

    private String[] des = {"在这里\n你可以听到周围人的心声", "在这里\nTA会在下一秒遇见你", "在这里\n不再错过可以改变你一生的人"};
    private int[] transforms = {TransformerStyle.NONE, TransformerStyle.ACCORDION, TransformerStyle.STACK, TransformerStyle.DEPTH, TransformerStyle.ROTATE, TransformerStyle.SCALE_IN};

    private List<CustomBean> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_guide);
        initData();
        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.top_view))
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .init();
        setupViewPager();
        updateUI(0);
        bvpGuideMain.create(data);
    }

    private void initData(){
        for (int i = 0; i < mDrawableList.size(); i++) {
            CustomBean customBean = new CustomBean();
            customBean.setImageRes(mDrawableList.get(i));
            customBean.setImageDescription(des[i]);
            data.add(customBean);
        }
    }

    private void setupViewPager() {
        bvpGuideMain = findViewById(R.id.viewpager);
        bvpGuideMain.setAutoPlay(false)
                .setCanLoop(false)
                .setPageTransformerStyle(transforms[new Random().nextInt(6)])
                .setScrollDuration(ANIMATION_DURATION)
                .setIndicatorMargin(0, 0, 0, (int) (getResources().getDimension(R.dimen.main_dp_100)))
                .setIndicatorGap((int)(getResources().getDimension(R.dimen.main_dp_10)))
                .setIndicatorColor(ContextCompat.getColor(this, R.color.common_colorWhite),
                        ContextCompat.getColor(this, R.color.main_white_alpha_75))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorRadius((int)getResources().getDimension(R.dimen.main_dp_3), (int)getResources().getDimension(R.dimen.main_dp_4_5))
                .setOnPageChangeListener(new OnPageChangeListenerAdapter() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        BannerUtils.log("position="+position);
                        updateUI(position);
                    }
                })
                .setHolderCreator(new HolderCreator() {
                    @Override
                    public ViewHolder createViewHolder() {
                        CustomPageViewHolder customPageViewHolder = new CustomPageViewHolder();
                        customPageViewHolder.setOnSubViewClickListener(new CustomPageViewHolder.OnSubViewClickListener() {
                            @Override
                            public void onViewClick(View view, int position) {
                                ToastUtil.show(GuideActivity.this,"Logo Clicked,Item: "+position);
                            }
                        });

                        return customPageViewHolder;
                    }
                })
                .create(data);
    }

    private void updateUI(int position) {
        tvGuide.setText(des[position]);
        ObjectAnimator translationAnim = ObjectAnimator.ofFloat(tvGuide, "translationX", -120f, 0f);
        translationAnim.setDuration(ANIMATION_DURATION);
        translationAnim.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(tvGuide, "alpha", 0f, 1f);
        alphaAnimator1.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnim, alphaAnimator1);
        animatorSet.start();

        if (position == bvpGuideMain.getList().size() - 1 && tvStart.getVisibility()== View.GONE) {
            tvStart.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(tvStart, "alpha", 0f, 1f)
                    .setDuration(ANIMATION_DURATION)
                    .start();
        } else {
            tvStart.setVisibility(View.GONE);
        }
    }

    public void onClick(View view) {
        MainActivity.start(this);
        finish();
    }
}