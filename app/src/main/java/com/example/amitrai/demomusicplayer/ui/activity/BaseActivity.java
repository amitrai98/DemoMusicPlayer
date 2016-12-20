package com.example.amitrai.demomusicplayer.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amitrai.demomusicplayer.R;
import com.example.amitrai.demomusicplayer.ui.fragment.BaseFragment;

public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Toast toast;
    private int mLayoutId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(toast==null){
            toast=new Toast(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeFragment/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * opens a new fragment.
     */
    public void openFragment(BaseFragment fragment, boolean addToBackStack){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_frame, fragment);

            if (addToBackStack)
                ft.addToBackStack(backStateName);

            ft.commit();
        }
    }

    public void replaceFragment(BaseFragment fragment, int layoutToReplace) {
        replaceFragment(fragment, layoutToReplace, true);
    }


    @Override
    public void onBackPressed() {

        if(toast!=null) {
            toast.cancel();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }
        super.onBackPressed();
    }

    public void replaceFragment(BaseFragment fragment, int layoutToReplace, Boolean storeInStack) {

        try {
            View view = findViewById(layoutToReplace);
            if (mLayoutId == 0) {
                if (view != null) {
                    mLayoutId = layoutToReplace;
                }
            }
            replaceFragment(fragment, storeInStack);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(mLayoutId, fragment);
//            if (storeInStack) {
//                transaction.addToBackStack(fragment.getClass().getCanonicalName());
//            }
//            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceFragment(BaseFragment fragment, int layoutToReplace, boolean storeInStack, boolean isAdd) {

        try {
            View view = findViewById(layoutToReplace);
            if (mLayoutId == 0) {
                if (view != null) {
                    mLayoutId = layoutToReplace;
                }
            }
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            if (isAdd) {
////                transaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, 0, R.anim.slide_in_up);
////                transaction.add(mLayoutId, fragment);
//            } else {
//                transaction.replace(mLayoutId, fragment);
//            }
//
//            if (storeInStack) {
//                transaction.addToBackStack(fragment.getClass().getCanonicalName());
//            }
//
//            transaction.commit();

            replaceFragment(fragment, storeInStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  Toast getToast(){
        if(toast!=null){
            return  toast;
        }
        return null;
    }


    public void replaceFragment (BaseFragment fragment, boolean saveInBackStack){
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_frame, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (saveInBackStack)
                ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

//    private void replaceFragment (Fragment fragment){
//
//    }
}
