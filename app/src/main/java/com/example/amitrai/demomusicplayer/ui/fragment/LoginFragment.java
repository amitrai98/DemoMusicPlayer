package com.example.amitrai.demomusicplayer.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.amitrai.demomusicplayer.R;
import com.example.amitrai.demomusicplayer.backend.ApiClient;
import com.example.amitrai.demomusicplayer.backend.ApiInterface;
import com.example.amitrai.demomusicplayer.backend.ApiName;
import com.example.amitrai.demomusicplayer.backend.ApiRequester;
import com.example.amitrai.demomusicplayer.backend.ApiResponseListener;
import com.example.amitrai.demomusicplayer.backend.RequestModal;
import com.example.amitrai.demomusicplayer.backend.RequestType;
import com.example.amitrai.demomusicplayer.modals.UserAuthenticaitonModel;
import com.example.amitrai.demomusicplayer.util.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = getClass().getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.edt_email)
    EditText edt_email;
    @Bind(R.id.edt_password)
    EditText edt_password;

    Call<ResponseBody> call;





    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.unbind(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.btn_login)
    void login(){
        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()){
            if (Utils.isValidEmail(email)){
//                RxJavaHandler handler = new RxJavaHandler();
//                handler.callApi();
//                MusiLibrary musiLibrary = new MusiLibrary(getActivity().getContentResolver());
//                ArrayList<Artist> artists = musiLibrary.getArtists();
//                ArrayList<Song> song = musiLibrary.getArtistSongs(artists.get(0).mArtistId);
//                Log.e(TAG, "music library found are"+song);

                ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                try {
                    call = service.loginCall("android1@evontech.com","123456");
                }catch (Exception exp){
                    exp.printStackTrace();
                }
                RequestModal requestModal = new RequestModal(RequestType.POST, ApiName.LOGIN, call);

                try {
                    ApiRequester.request(requestModal, new ApiResponseListener() {
                        @Override
                        public void onApiSuccess(String response) {
                            Log.e(TAG, "response received"+response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                UserAuthenticaitonModel authenticaitonModel =
                                        new Gson().fromJson(jsonObject.toString(), UserAuthenticaitonModel.class);
                                Log.e(TAG, ""+authenticaitonModel);
                                mPreference.setUserLoginTrue(authenticaitonModel);
                            }catch (Exception exp){
                                exp.printStackTrace();
                            }
                        }
                        @Override
                        public void onApiError(String error) {
                            Log.e(TAG, "an api error occured");
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else
                Log.e(TAG, "invalid email");
        }else
            Log.e(TAG, "email or password can not be left blank");
    }

    @OnClick(R.id.btn_register)
    void registerUser(){
        replaceFragment(new RegisterFragment(), true);
    }

}
