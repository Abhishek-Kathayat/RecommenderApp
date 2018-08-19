package com.recom.www.recommenderapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.recom.www.recommenderapp.Util.PrefUtil;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    CallbackManager callbackManager;
    LoginResult loginResult;
    JSONObject object;
    PrefUtil pf;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        callbackManager = CallbackManager.Factory.create();

        final String EMAIL = "email";
        Button fbButton = findViewById(R.id.button_1);

        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));
        // If you are using in a fragment, call loginButton.setFragment(this);
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loginButton.performClick();
            }
        });
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "You are logged in !!!",
                        Toast.LENGTH_LONG).show();


                String accessToken = loginResult.getAccessToken().getToken();
              //  PrefUtil prefUtil=null;
                // save accessToken to SharedPreference

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject jsonObject,
                                                    GraphResponse response) {

                                // Getting FB User Data
                                Bundle facebookData = getFacebookData(jsonObject);
                                Log.i("FB:",":"+jsonObject);
                                try {
                                    String first_name = jsonObject.getString("first_name");
                                    String last_name = jsonObject.getString("last_name");
                                    String email = jsonObject.getString("email");
                                    String gender = jsonObject.getString("gender");
                                   // pf.saveFacebookUserInfo(first_name,last_name,email,gender,"");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Log.d("",":"+facebookData);
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();

                Intent myIntent = new Intent(getApplicationContext(), Verification.class);
                getApplicationContext().startActivity(myIntent);

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "You'r log in failed !!!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Something went wrong",
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    private Bundle getFacebookData(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,email,gender");
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
