package com.qrgenerator;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.qrgenerator.customviews.CustomDialogFragment;
import com.qrgenerator.fragments.HospitalGuideFragment;
import com.qrgenerator.fragments.InstructionFragment;
import com.qrgenerator.fragments.QRCodeFragment;
import com.qrgenerator.fragments.VisitorListFragment;
import com.qrgenerator.models.Visitor;
import com.qrgenerator.utils.Constants;
import com.qrgenerator.utils.OnTaskCompleted;
import com.qrgeneratorapp.max.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnTaskCompleted ,CustomDialogFragment.DataPassListener  {

    private static final  String LOG_TAG= DashboardActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private QRCodeFragment qrCodeFragment;
    private VisitorListFragment visitorListFragment;
    private HospitalGuideFragment hospitalGuideFragment;
    private InstructionFragment instructionFragment;
    private ViewPagerAdapter adapter;
    private class OnPageChangeListener extends ViewPager.SimpleOnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
           switch (position){
               case 0:
                   Log.d(LOG_TAG ," position: "+position);
                   break;
               case 1:
                   Log.d(LOG_TAG ," position: "+position);
                   break;
               case 2:
                   Log.d(LOG_TAG ," position: "+position);
                   break;
               case 3:
                   Log.d(LOG_TAG ," position: "+position);
                   break;
           }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new OnPageChangeListener());
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
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // set Fragmentclass Arguments
        qrCodeFragment = new QRCodeFragment();
        visitorListFragment =new VisitorListFragment();
        hospitalGuideFragment= new HospitalGuideFragment();
        instructionFragment= new InstructionFragment();
        adapter.addFragment(instructionFragment, Constants.INSTRUCTION_TAB);
        adapter.addFragment(hospitalGuideFragment, Constants.GUIDE_TAB);
        adapter.addFragment(qrCodeFragment, Constants.QR_CODE_TAB);
        adapter.addFragment(visitorListFragment, Constants.VISITOR_TAB);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTaskCompleted(Bitmap bitmap) {
        qrCodeFragment.setQRCodeBitmap(bitmap);
        Log.d("Irshad", " inside onTaskCompleted ");
    }

    @Override
    public void passData(Visitor data) {
        visitorListFragment.addNewVisitor(data);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private int count;

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
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(LOG_TAG ," inside instantiateItem position: "+position);
            return super.instantiateItem(container, position);
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
