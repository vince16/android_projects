package vince.zebapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;

public class OTPFragment extends Fragment implements View.OnClickListener{
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ot, container, false);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        DigitsAuthButton authButton = (DigitsAuthButton) view.findViewById(R.id.auth_button);
        authButton.setBackgroundResource(R.drawable.transparent_button);
        final TextView otpTitle = (TextView) view.findViewById(R.id.otpTitle);
        final Button continueOtp = (Button) view.findViewById(R.id.continueOtp);
        continueOtp.setOnClickListener(this);

        final ViewGroup.LayoutParams buttonlp= (ViewGroup.LayoutParams)continueOtp.getLayoutParams();


        authButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                //Toast.makeText(getContext(), "Authentication successful for "
                //      + phoneNumber, Toast.LENGTH_LONG).show();
                buttonlp.height = 100;
                continueOtp.setLayoutParams(buttonlp);
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueOtp: ((InitializationActivity)getActivity()).setFragment(3);
                break;

        }
    }
}
