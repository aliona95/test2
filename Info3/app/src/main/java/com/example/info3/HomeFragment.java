package com.example.info3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView textView;
    public static String text = "AAA"; /// Defaultinis tekstas
    public static String placeName = "NULL";  /// Defaultinis tekstas
    public static String address = "NULL";
    public static String type = "NULL";
    public static double distance = 0;
    public static String icon = "NULL";
    public static ImageView image; /// paveiksliukui paimti is JSON
    public static RatingBar setRatingBar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //View view = inflater.inflate(R.layout.fragment_home, container, false);


        View myInflatedView = inflater.inflate(R.layout.fragment_home, container,false);

        // Set the Text to try this out
        TextView t = (TextView) myInflatedView.findViewById(R.id.textView3);
        //t.setText("Text to Display");

        t.setText(placeName);
        ////Reitinga uzsetinam
        //float curRate = 2.0;
       // setRatingBar.setRating(2);


        //ImageView imageView = (ImageView)myInflatedView.findViewById(R.id.imageView4);
        TextView t2 = (TextView) myInflatedView.findViewById(R.id.textView2);
        t2.setText(HomeFragment.address);


        TextView t4 = (TextView) myInflatedView.findViewById(R.id.textView5);
        String distance = "Atstumas: " + Double.toString(HomeFragment.distance)+ "km";
        t4.setText(distance);


        TextView t3 = (TextView) myInflatedView.findViewById(R.id.textView4);
        String temp = " ";
        type.substring(1,type.length() - 1); ///[]
        for (String retval: type.split(",")) {
            temp =  temp + retval.substring(1,retval.length() - 1);
            temp = temp + "|";
        }
        temp =  temp.substring(2,temp.length() - 2);
        t3.setText(temp.toUpperCase());

        WebView myWebView = (WebView) myInflatedView.findViewById(R.id.webView);
        myWebView.loadUrl(icon);

        return myInflatedView;
        //return inflater.inflate(R.layout.fragment_home, container, false);
        //return view;
    }


   /* public void setText(String text){
        TextView frv =(TextView) getView().findViewById(R.id.textView3);
        frv.setText("raton");
    }*/

}
