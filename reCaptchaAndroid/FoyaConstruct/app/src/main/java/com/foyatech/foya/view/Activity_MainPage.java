package com.foyatech.foya.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.foyatech.foya.R;
import com.foyatech.foyalibrary.AppDataSharePreferences;
import com.foyatech.zxing.MainActivity;
import com.foyatech.zxing.Scan;

public class Activity_MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppDataSharePreferences mAppData;
    private final int QRCODE_EVENT_CODE = 7788;
    public Toolbar mToolbar ;
    public TextView mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("豐揚科技");
        setSupportActionBar(mToolbar);
        mAppData = new AppDataSharePreferences(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 右下角 Buton
                mToolbar.setTitle("Page2");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View slideView =  navigationView.getHeaderView(0);
        //可利用SlideView設定選單文字
        mName = (TextView)slideView.findViewById(R.id.nav_header_name);
        String Name = mAppData.GetAppData("Name","NoSet");
        mName.setText( Name );

        // 設定起始Fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainPage_contentFragment , new FragmentGoogleCalendar() ).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_page, menu);
        return true;
    }

    // 右上選單列表
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 左方Menu選單
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        // 於此處控制Fragment的切換
        int id = item.getItemId();

        if (id == R.id.nav_google_calendar) {
            fragment = new FragmentGoogleCalendar();
        } else if (id == R.id.nav_forums) {
            fragment = new Fragment_Forum();
        } else if (id == R.id.nav_gallery) {
            fragment = new Fragment_reCaptcha();
        }
        else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent openCameraIntent = new Intent(Activity_MainPage.this, Scan.class);
            startActivityForResult(openCameraIntent, QRCODE_EVENT_CODE );
        }

        // 利用 FragmentManager 去改變  R.id.content_frame 這個Fragment , 此Fragment 在 Content_main (內文主頁)
        //有一個佔據整個畫面的Fragment元件 < FrameLayout > 叫做 content_frame
        if (fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainPage_contentFragment , fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == QRCODE_EVENT_CODE) {
            if (resultCode == RESULT_OK) {

                Bundle bundle = data.getExtras();
            /*  決定回傳資料的 key value,可設定於 zxing.activity.CaptureActivity中 */
                String scanResult = bundle.getString("result");

                if (scanResult != null) {
                    Log.d("Goto", scanResult.toString());
                } else
                    Log.d("Goto", "Empty");
            }
        }
    }

    //region Self Funcation
    public void setMyToolbarTitle(String titleName)
    {
        mToolbar.setTitle(titleName);
    }
    //endregion
}
