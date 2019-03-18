package com.example.earthquakecomplete;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    String  Server_url;
    RecyclerView recyclerView;
    ArrayList<MyEarthquakeData> myEarthquakeDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Server_url ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&limit=60";

        recyclerView = findViewById(R.id.myRecycler_id);
        myEarthquakeDataArrayList = new ArrayList<>();

        getResponse(Server_url);

    }

    public void getResponse(String server_url){
        final RequestQueue requestQueue  = Volley.newRequestQueue(MainActivity.this);

        StringRequest myFirstReq = new StringRequest(Request.Method.GET, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                MyEarthQuakeResponse earthQuakeResponse = gson.fromJson(response,MyEarthQuakeResponse.class);


                float mag;
                String place;
                long time;

                for(int i=0;i<earthQuakeResponse.getFeatures().size();i++){

                    mag = earthQuakeResponse.getFeatures().get(i).getProperties().getMag();
                    place = earthQuakeResponse.getFeatures().get(i).getProperties().getPlace();
                    time = earthQuakeResponse.getFeatures().get(i).getProperties().getTime();
                    myEarthquakeDataArrayList.add(new MyEarthquakeData(mag,place,time));

                }

                MyEarthquakeAdapter myEarthquakeAdapter = new MyEarthquakeAdapter(myEarthquakeDataArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(myEarthquakeAdapter);

                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (isInternetConnection()==false){

                    Toast.makeText(MainActivity.this, "There is no Internet Connection", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(MainActivity.this, "SomethingWent Wrong", Toast.LENGTH_SHORT).show();
                requestQueue.stop();

            }

        });

        requestQueue.add(myFirstReq);


    }

    public  boolean isInternetConnection()
    {

        ConnectivityManager connectivityManager =  (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.minimum_id: {
                Toast.makeText(this, "Minimum item Selected", Toast.LENGTH_SHORT).show();
                String Server_url ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=0&limit=50";
                myEarthquakeDataArrayList.clear();
                getResponse(Server_url);
                return true;
            }

            case R.id.maximum_id:{
                Toast.makeText(this, "Maximum item Selected", Toast.LENGTH_SHORT).show();
                String Server_url ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=50";
                myEarthquakeDataArrayList.clear();
                getResponse(Server_url);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
