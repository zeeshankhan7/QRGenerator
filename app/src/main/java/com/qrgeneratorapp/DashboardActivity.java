package com.qrgeneratorapp;

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

import com.qrgeneratorapp.fragments.HospitalGuideFragment;
import com.qrgeneratorapp.fragments.InstructionFragment;
import com.qrgeneratorapp.fragments.QRCodeFragment;
import com.qrgeneratorapp.fragments.VisitorListFragment;
import com.qrgeneratorapp.models.HospitalUser;
import com.qrgeneratorapp.utils.Constants;
import com.qrgeneratorapp.utils.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnTaskCompleted {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private QRCodeFragment qrCodeFragment;
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
        tabLayout.getTabAt(2).setIcon(Constants.tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(Constants.tabIcons[3]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        HospitalUser hospitalUser=(HospitalUser) getIntent().getSerializableExtra("HospitalUser");
        Bundle bundle = new Bundle();
        bundle.putSerializable("HospitalUser", hospitalUser);
        // set Fragmentclass Arguments
        qrCodeFragment = new QRCodeFragment();
        qrCodeFragment.setArguments(bundle);
        adapter.addFragment(new InstructionFragment(), Constants.INSTRUCTION_TAB);
        adapter.addFragment(new HospitalGuideFragment(), Constants.GUIDE_TAB);
        adapter.addFragment(qrCodeFragment, Constants.QR_CODE_TAB);
        adapter.addFragment(new VisitorListFragment(), Constants.VISITOR_TAB);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTaskCompleted(Bitmap bitmap) {
        qrCodeFragment.setQRCodeBitmap(bitmap);
        Log.d("Irshad", " inside onTaskCompleted ");
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
