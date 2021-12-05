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
    private List<String> rooms = new ArrayList<>();

    private ArrayAdapter<String> titleAdapter;
    private ArrayAdapter<String> roomAdapter;

    private String userId;


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

        // create library opening hours every single day
//        for (int day = 7 ; day < 29 ; day++) {
//            RequestParams paramsLibOpeningHour = new RequestParams();
//            String startHour = "8";
//            paramsLibOpeningHour.put("startMin", 0);
//            paramsLibOpeningHour.put("endMin", 0);
//            paramsLibOpeningHour.put("endHour", 17);
//            paramsLibOpeningHour.put("year", 2021);
//            paramsLibOpeningHour.put("month", 12);
//            paramsLibOpeningHour.put("day", day);
//            HttpUtils.post("libraryTimeslots/create/" + startHour, paramsLibOpeningHour, new JsonHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                    super.onSuccess(statusCode, headers, response);
//
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    super.onFailure(statusCode, headers, responseString, throwable);
//
//                }
//            });
//        }

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

        // load in the rooms from the database
        Spinner roomsSpinner = (Spinner) findViewById(R.id.rooms_spinner);
        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, rooms);
        roomsSpinner.setAdapter(roomAdapter);
        String urlGetRooms = "rooms/get";
        RequestParams paramsRoom = new RequestParams();

        HttpUtils.get(urlGetRooms, paramsRoom, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                // load in the titles from the database into the spinner
                for (int i = 0 ; i < responseBody.length() ; i++){
                    try {
                        String roomType = responseBody.getJSONObject(i).getString("roomType");
                        String roomId = responseBody.getJSONObject(i).getString("roomId");
                        String capacity = responseBody.getJSONObject(i).getString("capacity");

                        roomAdapter.add("Type: " + roomType + ", Capacity: " + capacity + ", Id: " + roomId);

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



        // get book room button
        Button bookRoomBtn = (Button) findViewById(R.id.book_room_button);

        bookRoomBtn.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View view) {
                    // book a room

                    // get name of the room we want to book
                    String roomInfo = roomsSpinner.getSelectedItem().toString();

                    // parse room info
                    String[] infoParsed = roomInfo.split(",");
                    String roomId = infoParsed[2].split(":")[1].replaceAll(" ","");


                    String urlBookRoom = "roomReservations/create/" + roomId;
                    RequestParams paramsBookRoom = new RequestParams();

                    // get userid
                    HttpUtils.get("clients/get", new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);
                            for (int i = 0 ; i < response.length() ; i++){
                                try {
                                    String username = response.getJSONObject(i).getString("username");
                                    String ID = response.getJSONObject(i).getString("userId");

                                    if (username.equals(Login.USERNAME)){
                                        userId = ID;
                                        break;
                                    }

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

                    final String[] day = new String[1];
                    final String[] month = new String[1];
                    final String[] year = new String[1];
                    final String[] startMin = new String[1];
                    final String[] startHour = new String[1];
                    final String[] endHour = new String[1];
                    final String[] endMin = new String[1];

                    // get next available timeslot
                    HttpUtils.get("roomReservations/get/" + roomId, new RequestParams(), new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);
                            // retrieve next available timeslot information
                            try {

                                for (int i = 0 ; i < response.length() ; i++) {

                                    String username = response.getJSONObject(0).getString("username");
                                    if (username.equals("null")) {

                                        // if the timeslot  is available
                                        day[0] = response.getJSONObject(i).getString("day");
                                        month[0] = response.getJSONObject(i).getString("month");
                                        year[0] = response.getJSONObject(i).getString("year");
                                        startMin[0] = response.getJSONObject(i).getString("startMin");
                                        startHour[0] = response.getJSONObject(i).getString("startHour");
                                        endHour[0] = response.getJSONObject(i).getString("endHour");
                                        endMin[0] = response.getJSONObject(i).getString("endMin");

                                        break;
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);

                        }
                    });



                    paramsBookRoom.put("userId", userId);
                    paramsBookRoom.put("startMin", startMin[0]);
                    paramsBookRoom.put("startHour", startHour[0]);
                    paramsBookRoom.put("endMin", endMin[0]);
                    paramsBookRoom.put("endHour", endHour[0]);
                    paramsBookRoom.put("year", year[0]);
                    paramsBookRoom.put("month", month[0]);
                    paramsBookRoom.put("day", day[0]);

                    HttpUtils.post(urlBookRoom, paramsBookRoom, new JsonHttpResponseHandler(){
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
            }
        );
    }

}
