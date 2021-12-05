package ca.mcgill.ecse321.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private String error = null;

    private void refreshErrorMessage(){
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    /***
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get elements from UI
        Button loginBtn = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.username_login);
        EditText password = findViewById(R.id.password_login);

        // check if librarian
        //CheckBox isLibrarian = findViewById(R.id.is_librarian);

        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    /**
                     * login
                     * @param view
                     */
                    public void onClick(View view) {
                        RequestParams params = new RequestParams();
                        params.put("password", password.getText().toString());
                        String url = "clients/login/" + username.getText().toString() ;

                        HttpUtils.post(url, params, new AsyncHttpResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                username.setText("");
                                password.setText("");
                                setContentView(R.layout.home_page_client);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                username.setText("");
                                password.setText("");

                            }
                        });
                    }
                }
        );


    }




}
