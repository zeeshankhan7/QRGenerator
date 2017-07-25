package com.qrgenerator;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.qrgenerator.customviews.CustomDialogFragment;
import com.qrgenerator.fragments.HospitalGuideFragment;
import com.qrgenerator.fragments.InstructionFragment;
import com.qrgenerator.fragments.QRCodeFragment;
import com.qrgenerator.fragments.VisitorListFragment;
import com.qrgenerator.models.Attendant;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.utils.AppSharedPreferenceHelper;
import com.qrgenerator.utils.Constants;
import com.qrgenerator.utils.OnTaskCompleted;
import com.qrgeneratorapp.max.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnTaskCompleted ,CustomDialogFragment.DataPassListener  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private QRCodeFragment qrCodeFragment;
    private VisitorListFragment mVisitorListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(Constants.tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(Constants.tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(Constants.tabIcons[2]);
        tabLayout.getTabAt(2).setIcon(Constants.tabIcons[3]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // set Fragmentclass Arguments
//        qrCodeFragment = new QRCodeFragment();
        mVisitorListFragment=new VisitorListFragment();
        adapter.addFragment(new InstructionFragment(), Constants.INSTRUCTION_TAB);
        adapter.addFragment(new HospitalGuideFragment(), Constants.GUIDE_TAB);
//        adapter.addFragment(qrCodeFragment, Constants.QR_CODE_TAB);
        adapter.addFragment(mVisitorListFragment, Constants.VISITOR_TAB);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTaskCompleted(Bitmap bitmap) {
//        qrCodeFragment.setQRCodeBitmap(bitmap);
        Log.d("Irshad", " inside onTaskCompleted ");
    }

    @Override
    public void passData(Visitor data) {
        mVisitorListFragment.addNewVisitor(data);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
