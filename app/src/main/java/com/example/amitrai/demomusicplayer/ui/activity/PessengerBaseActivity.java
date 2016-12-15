//package com.example.amitrai.demomusicplayer.ui.activity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.content.res.TypedArray;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.amitrai.demomusicplayer.R;
//import com.example.amitrai.demomusicplayer.util.AppPreference;
//
//
///**
// */
//public abstract class PessengerBaseActivity extends AppCompatActivity {
//
//    private FrameLayout activityContent = null;
//    private Toolbar mToolbar = null;
//    private TextView mTitleText;
//    private int mLayoutId = 0;
//    protected AppPreference mPref;
////    public OnEditProfile editProfile;
////    private LocationData mlocationData;
//    public static Toast toast;
//    int  mActionBarSize;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
//        if(toast==null){
//            toast=new Toast(this);
//        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mTitleText = (TextView) findViewById(R.id.tv_toolbar);
//        activityContent = (FrameLayout) this.findViewById(R.id.activity_content);
//        mPref = AppPreference.newInstance(this);
//
//        // find the actionbarSize
//        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
//                new int[] { android.R.attr.actionBarSize });
//        mActionBarSize = (int) styledAttributes.getDimension(0, 0);
//        styledAttributes.recycle();
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                break;
//            case R.id.action_edit:
//                editProfile.onedit();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * initialize Toolbar
//     *
//     * @param title        : title to set to Toolbar
//     * @param isHomeEnable : check is Back Button Enable or not
//     */
//    public void initToolBar(String title, boolean isHomeEnable) {
//        mToolbar.setVisibility(View.VISIBLE);
//        mTitleText.setText(title);
//        mTitleText.setTypeface(Typeface.DEFAULT_BOLD);
//        setSupportActionBar(mToolbar);
//        if (isHomeEnable) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setTitle(null);
//
//        }else{
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowHomeEnabled(false);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setTitle(null);
//        }
//
//
//    }
//
//
//    protected FrameLayout getParentView() {
//        return activityContent;
//    }
//
//    /**
//     * replace given fragment to layout
//     *
//     * @param fragment        fragment to replace
//     * @param layoutToReplace layout which hold fragment/
//     */
//    public void replaceFragment(Fragment fragment, int layoutToReplace) {
//        replaceFragment(fragment, layoutToReplace, true);
//    }
//
//    /**
//     * initialize common methods and utilities
//     */
//    /**
//     * initialize all views of activity under this method
//     */
//    protected abstract void initViews();
//
//    /**
//     * initialize all Listeners under this method
//     */
//    protected abstract void initListeners();
//
//    /**
//     * initialize manager classes and user program under this method
//     */
//    protected abstract void initManagers();
//
//
//    /**
//     * override onBackPressed to maintain Fragment Stack OnBackPress
//     */
//    @Override
//    public void onBackPressed() {
//
//         if(toast!=null) {
//             toast.cancel();
//         }
//        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//            finish();
//            return;
//        }
//        super.onBackPressed();
//    }
//
//    public void setText(TextView view, String data) {
//        if (view != null && data != null) {
//            view.setText(data);
//        }
//    }
//
//    public String getViewText(TextView view) {
//        return view.getText().toString();
//    }
//
//    protected Toolbar getToolBar() {
//        if (mToolbar == null) {
//            initToolBar("", true);
//        }
//        return mToolbar;
//    }
//
//    /**
//     * logout from facebook if user any access token is stored in app.
//     */
//    public void logOutFb() {
//        AccessToken token = AccessToken.getCurrentAccessToken();
//        if (token != null) {
//            LoginManager manager = LoginManager.getInstance();
//            manager.logOut();
//        }
//    }
//
//
//    public void replaceFragment(Fragment fragment, int layoutToReplace, Boolean storeInStack) {
//
//        try {
//            View view = findViewById(layoutToReplace);
//            if (mLayoutId == 0) {
//                if (view != null) {
//                    mLayoutId = layoutToReplace;
//                }
//            }
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(mLayoutId, fragment);
//            if (storeInStack) {
//                transaction.addToBackStack(fragment.getClass().getCanonicalName());
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void replaceFragment(Fragment fragment, int layoutToReplace, boolean storeInStack, boolean isAdd) {
//
//        try {
//            View view = findViewById(layoutToReplace);
//            if (mLayoutId == 0) {
//                if (view != null) {
//                    mLayoutId = layoutToReplace;
//                }
//            }
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            if (isAdd) {
//                transaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, 0, R.anim.slide_in_up);
//                transaction.add(mLayoutId, fragment);
//            } else {
//                transaction.replace(mLayoutId, fragment);
//            }
//
//            if (storeInStack) {
//                transaction.addToBackStack(fragment.getClass().getCanonicalName());
//            }
//
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Set ediprofile listners
//     *
//     * @param onEditProfile
//     */
//    public void setEditListener(OnEditProfile onEditProfile) {
//        editProfile = onEditProfile;
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        switch (requestCode) {
//            case Constants.REQUEST_CHECK_SETTINGS:
//                switch (resultCode) {
//                    case Activity.RESULT_OK:
//                       /* mlocationData.setonLocationListener(null);
//                        mlocationData.disconnect();
//                        initiateLocation();*/
//                        break;
//                    case Activity.RESULT_CANCELED:
//
//                        break;
//                    default:
//                        break;
//                }
//                break;
//        }
//    }
//
//
//    public void initiateLocation(Context context,LocationInterface locationInterface){
//        mlocationData=new LocationData(context);
//        mlocationData.setonLocationListener(locationInterface);
//        mlocationData.callbacksResults();
//    }
//
//    /**
//     *
//     * @param message
//     * @param lenth TSnackbar.LengthShort ,TSnackbar.LengthLong
//     */
//    public void showSnackbar(String title,String message,int lenth) {
//
//
//
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.custom_toast,
//                (ViewGroup) findViewById(R.id.toast_layout_root));
//
//        TextView text = (TextView) layout.findViewById(R.id.text_error_subtitle);
//        text.setText(message);
//        TextView textTitle = (TextView) layout.findViewById(R.id.text_error_title);
//        text.setText(message);
//        textTitle.setText(title);
//        if(title.isEmpty()){
//            textTitle.setVisibility(View.GONE);
//        }else{
//            textTitle.setVisibility(View.VISIBLE);
//        }
//        if(toast!=null) {
//            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, mActionBarSize);
//            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setView(layout);
//            toast.show();
//        }
//
//       /* AppUtility.hideKeyboard(this);
//        Snackbar snackbar=  Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
//        View view=snackbar.getView();
//        view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
//        TextView textView = (TextView) view.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//
//
//        snackbar.show();
//*/
//
//       /* TSnackbar snackbar = TSnackbar.make(findViewById(R.id.activity_content), message, TSnackbar.LENGTH_SHORT);
//        snackbar.setActionTextColor(Color.WHITE);
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
//        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        snackbar.show();*/
//
//    }
//    public  Toast getToast(){
//        if(toast!=null){
//            return  toast;
//        }
//        return null;
//    }
//}
