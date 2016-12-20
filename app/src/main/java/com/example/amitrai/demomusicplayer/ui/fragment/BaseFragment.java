package com.example.amitrai.demomusicplayer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.View;

import com.example.amitrai.demomusicplayer.R;
import com.example.amitrai.demomusicplayer.ui.activity.BaseActivity;
import com.example.amitrai.demomusicplayer.util.AppPreference;
import com.example.amitrai.demomusicplayer.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected Utils mUtils;
    protected AppPreference mPreference;
    protected DateUtils mDateUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
            mUtils = new Utils();
            mPreference = AppPreference.newInstance(context);
//            mDateUtils = DateUtils.getInstance();
        }
    }

    /**
     * Replace current view to given fragment from fragment
     *
     * @param fragment
     */
    protected void replaceFragment(BaseFragment fragment) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, R.id.content_frame);
    }


    protected void replaceFragment(BaseFragment fragment, Boolean storeInStack) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, R.id.content_frame, storeInStack);
    }

    protected void replaceFragment(BaseFragment fragment, boolean storeInStack, boolean isAdd) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, R.id.content_frame, storeInStack, isAdd);
    }

    /**
     * initialize all views of activity under this method
     */
    protected abstract void initViews(View view);

    /**
     * initialize all Listeners under this method
     */
    protected abstract void initListeners();





    @Override
    public void onStop() {
        super.onStop();

    }
}
