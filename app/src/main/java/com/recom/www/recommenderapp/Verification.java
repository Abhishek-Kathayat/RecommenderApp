package com.recom.www.recommenderapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.AccountKitLoginResult;

public class Verification extends AppCompatActivity {
    public static int APP_REQUEST_CODE = 99;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        phoneLogin();
    }
    public void phoneLogin() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage= "";
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                Log.i("Success",toastMessage);
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
                Log.i("Success",toastMessage);
            } else {
                Log.i("Success", String.valueOf(loginResult.getAccessToken()));
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    getAccount();
                    Log.i("Success",toastMessage);
                }
            }
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            getApplicationContext().startActivity(myIntent);
        }
    }

    /**
     * Gets current account from Facebook Account Kit which include user's phone number.
     */
    private void getAccount(){
        Toast.makeText(
                getApplicationContext(),
                "Hello Working Success",
                Toast.LENGTH_LONG)
                .show();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                String accountKitId = account.getId();

                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                String phoneNumberString = phoneNumber.toString();

                // Surface the result to your user in an appropriate way.
                Toast.makeText(
                        getApplicationContext(),
                        phoneNumberString,
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onError(final AccountKitError error) {
                Log.e("AccountKit",error.toString());
                // Handle Error
                Toast.makeText(
                        getApplicationContext(),
                        error.toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}