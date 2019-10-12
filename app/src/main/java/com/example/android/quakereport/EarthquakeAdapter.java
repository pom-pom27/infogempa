package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = "of";
    private static final String REGEX_SPLITTER = "(?<=f )";

    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String place1, place2;
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_earthquake,
                    parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magTv = listItemView.findViewById(R.id.mag);
        TextView placeTv = listItemView.findViewById(R.id.place);
        TextView dateTv = listItemView.findViewById(R.id.date);
        TextView timeTv = listItemView.findViewById(R.id.time);
        TextView placeTv2 = listItemView.findViewById(R.id.place2);

        Date dateObject = new Date(currentEarthquake.getTimeInMiliSec());

        magTv.setText(currentEarthquake.getmMag());

        String place = currentEarthquake.getmPlace();

        if (place.contains(LOCATION_SEPERATOR)) {
            String[] parts = place.split(REGEX_SPLITTER);
            place1 = parts[0];
            place2 = parts[1];
        } else {
            place1 = getContext().getString(R.string.near_the);
            place2 = place;
        }

//        cara ke-2 split place
//        if (place.contains(LOCATION_SEPERATOR)) {
//        String parts = place.split(LOCATION_SEPERATOR);
//            place1 = parts[0] + LOCATION_SEPERATOR;
//            place2 = parts[1];
//        } else {
//            place1 = getContext().getString(R.string.near_the);
//            place2 = place;
//        }

        placeTv.setText(place1);
        placeTv2.setText(place2);

        String formatDate = formatDate(dateObject);
        dateTv.setText(formatDate);

        String formatTime = formatTime(dateObject);
        timeTv.setText(formatTime);


        return listItemView;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return timeFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY");
        return dateFormat.format(dateObject);
    }


}
