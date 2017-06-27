package com.foyatech.foyalibrary.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import com.foyatech.foyalibrary.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentPagerAdapter {

    // 此為 上圖下字 TabLayout  →  foya_tab_layout

    private Context mContext;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    private List<Integer> mImageResId = new ArrayList<Integer>();;

    public TabFragmentAdapter(FragmentManager manager , Context context) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title , int ImageResource) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        mImageResId.add(ImageResource);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public View getTabView(int position) {
        // 先取得該TABLAYOUT的樣式元素
        View view = LayoutInflater.from(mContext).inflate(R.layout.foya_tab_layout, null);
        ImageView iv_tab = (ImageView) view.findViewById(R.id.tab_ImageView);
        TextView tv_tab = (TextView) view.findViewById(R.id.tab_TextView);
        // 在設定樣式元素的圖形與文字
        iv_tab.setImageResource(mImageResId.get(position) );
        tv_tab.setText(mFragmentTitleList.get(position).toString());
        return view;
    }

    public void SetCustomView(TabLayout tabLayout)
    {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //獲得對應位置的Tab
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //設置自訂TAB的樣式
            tab.setCustomView(getTabView(i));
        }
    }

}