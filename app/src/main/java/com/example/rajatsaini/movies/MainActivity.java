 package com.example.rajatsaini.movies;

//import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


 public class MainActivity extends AppCompatActivity {
     RequestQueue requestQueue;
     String url = "https://api.themoviedb.org/3/movie/popular?api_key=4c0bdf8660cc7abd4a82a2e12713cd04&language=en-US&page=1";
     private ListView listView;


     String[] NAMES = {"Rajat Saini","Arijit Sing", "Rohit Saini", "Guru", "Kapil Saini","Bhanu Saini","Nepaali", "Bhanwari","Rakesh","Kuku","Thulla"};
     String[] titles;
     String[] poster_path;
     String[] overview;
     Double[] average_vote;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);

         requestQueue = Volley.newRequestQueue(getApplicationContext());
         StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>(){
             @Override
             public void onResponse(String response){
                 try{
                     JSONObject resp = new JSONObject(response);
                     JSONArray movies = resp.getJSONArray("results");
                     titles = new String[movies.length()];
                     poster_path = new String[movies.length()];
                     overview = new String[movies.length()];
                     average_vote = new Double[movies.length()];
                     for (int i =0 ;i<movies.length();i++){
                         JSONObject data = movies.getJSONObject(i);
                         String title_name = data.getString("original_title");
                         String oview = data.getString("overview");
                         String ppath = data.getString("poster_path");
                         Double avote = data.getDouble("vote_average");
                         titles[i] = title_name;
                         poster_path[i] = ppath;
                         overview[i] = oview;
                         average_vote[i] = avote;
                     }
                     CustomAdapter customAdapter = new CustomAdapter();
                     listView.setAdapter(customAdapter);
                     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         @Override
                         public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                             Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
                             intent.putExtra("MOVIE_TITLE", titles[position]);
                             intent.putExtra("MOVIE_IMAGE_PATH",poster_path[position]);
                             intent.putExtra("MOVIE_OVERVIEW",overview[position]);
                             intent.putExtra("MOVIE_VOTE",average_vote[position].toString());
                             startActivity(intent);
                         }
                     });
//                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,titles);
//                     listView.setAdapter(adapter);
                 }catch(JSONException e){
                     e.printStackTrace();
                 }
             }
         },new Response.ErrorListener(){
             @Override
             public void onErrorResponse(VolleyError e){
                 e.printStackTrace();
             }
         });
         requestQueue.add(stringRequest);
}
class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView movie_name = (TextView)view.findViewById(R.id.tvtitle);
            TextView movie_vote = (TextView)view.findViewById(R.id.tvVoteAverage);

            movie_name.setText(titles[position]);
            movie_vote.setText(average_vote[position].toString());
            Glide.with(view).load("https://image.tmdb.org/t/p/w500/"+poster_path[position]).into(imageView);

            return view;
        }
    }
    }





