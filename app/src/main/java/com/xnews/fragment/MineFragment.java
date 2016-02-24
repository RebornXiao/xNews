package com.xnews.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.activity.LoginActivity;
import com.xnews.activity.PersonActivity;
import com.xnews.base.BaseFragment;
import com.xnews.utils.TVUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我
 * Created by xiao on 2016/2/16.
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.iv_myhead_logo)
    ImageView ivMyheadLogo;
    @Bind(R.id.iv_sex_mine)
    ImageView ivSexMine;
    @Bind(R.id.tv_info_name)
    TextView tvInfoName;
    @Bind(R.id.tv_my_menu_to)
    TextView tvMyMenuTo;
    @Bind(R.id.rl_item_info)
    RelativeLayout rlItemInfo;
    @Bind(R.id.tv_info_money)
    TextView tvInfoMoney;
    @Bind(R.id.tv_vitality_nums)
    TextView tvVitalityNums;
    @Bind(R.id.rl_vitality_nums)
    RelativeLayout rlVitalityNums;
    @Bind(R.id.tv_integral_nums)
    TextView tvIntegralNums;
    @Bind(R.id.rl_integral_nums)
    RelativeLayout rlIntegralNums;
    @Bind(R.id.tv_des_money)
    TextView tvDesMoney;
    @Bind(R.id.ll_mine_money_detail)
    LinearLayout llMineMoneyDetail;
    @Bind(R.id.view1)
    View view1;
    @Bind(R.id.iv_money_recharge_in)
    ImageView ivMoneyRechargeIn;
    @Bind(R.id.tv_enterprist_tips)
    TextView tvEnterpristTips;
    @Bind(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @Bind(R.id.rl_info_recharge_in)
    RelativeLayout rlInfoRechargeIn;
    @Bind(R.id.iv_money_recharge_deposit)
    ImageView ivMoneyRechargeDeposit;
    @Bind(R.id.tv_enterprist_tips_deposit)
    TextView tvEnterpristTipsDeposit;
    @Bind(R.id.rl_info_recharge_deposit)
    RelativeLayout rlInfoRechargeDeposit;
    @Bind(R.id.view_2)
    View view2;
    @Bind(R.id.iv_goods_treasure_logo)
    ImageView ivGoodsTreasureLogo;
    @Bind(R.id.item_myshop_treasure)
    RelativeLayout itemMyshopTreasure;
    @Bind(R.id.view_3)
    View view3;
    @Bind(R.id.iv_ep_manager_logo)
    ImageView ivEpManagerLogo;
    @Bind(R.id.tv_ep_manager)
    TextView tvEpManager;
    @Bind(R.id.item_ep_manager)
    RelativeLayout itemEpManager;
    @Bind(R.id.view_4)
    View view4;
    @Bind(R.id.iv_mine_settings_logo)
    ImageView ivMineSettingsLogo;
    @Bind(R.id.item_mine_settings)
    RelativeLayout itemMineSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        view.setOnClickListener(null);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        if (app.person != null) {
            if (!TextUtils.isEmpty(app.person.getSex())) {
                if (app.person.getSex().equals("男")) {
                    ivSexMine.setImageResource(R.drawable.my_sex_man);
                } else if (app.person.getSex().equals("女")) {
                    ivSexMine.setImageResource(R.drawable.my_sex_man);
                }
            }
            TVUtils.setText(tvInfoName, app.person.getUserName());
            TVUtils.setText(tvMyMenuTo, app.person.getEquipmentId());
            TVUtils.setText(tvInfoMoney, app.person.getBalance());
            TVUtils.setText(tvVitalityNums, app.person.getVitality());
            TVUtils.setText(tvIntegralNums, app.person.getUserIntegral());
            TVUtils.setText(tvDesMoney, app.person.getDeposit());
        } else {
            ivSexMine.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.rl_item_info)
    void checkPersonInfo() {
        if (app.isLogin) {
            gotoNextActivity(null, PersonActivity.class);
        } else {
            gotoNextActivity(null, LoginActivity.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
