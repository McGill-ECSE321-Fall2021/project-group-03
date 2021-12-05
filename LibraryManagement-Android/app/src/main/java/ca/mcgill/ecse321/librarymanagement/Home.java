package ca.mcgill.ecse321.librarymanagement;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private String error = null;
    // APPEND NEW CONTENT STARTING FROM HERE
    private List<String> titles = new ArrayList<>();
    private ArrayAdapter<String> titleAdapter;

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
     * this method will create the list of titles, rooms and library hours upon
     * loading the home page for the client
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_client);

        // elements from UI
        Spinner titlesSpinner = (Spinner) findViewById(R.id.titles_spinner);
        titleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, titles);
        titlesSpinner.setAdapter(titleAdapter);

        // reserve button
        Button reserveBtn = (Button) findViewById(R.id.reserve_button);

        String url = "titles/get";
        RequestParams params = new RequestParams();

        HttpUtils.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                // load in the titles from the database into the spinner
                Log.d("length", String.valueOf(responseBody.length()));
                for (int i = 0 ; i < responseBody.length() ; i++){
                    try {
                        String titleName = responseBody.getJSONObject(i).getString("name");
                        titleAdapter.add(titleName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        // reservation functionality
        reserveBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        // get selected title name
                        Spinner titlesSpinner = (Spinner) findViewById(R.id.titles_spinner);
                        String titleName = titlesSpinner.getSelectedItem().toString();

                        //get reservation url
                        String urlReserve = "titles/reserve/" + titleName;
                        RequestParams params2 = new RequestParams();
                        params2.put("clientUsername", Login.USERNAME);
                        HttpUtils.post(urlReserve, params2, new JsonHttpResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                super.onSuccess(statusCode, headers, response);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                            }
                        });
                    }

                });
    }

}
