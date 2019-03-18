package com.example.earthquakecomplete;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyEarthquakeAdapter extends RecyclerView.Adapter<MyEarthquakeAdapter.MyViewHolder> {

    ArrayList<MyEarthquakeData> myEarthquakeDataArrayList;

    public MyEarthquakeAdapter(ArrayList<MyEarthquakeData> myEarthquakeDataArrayList) {
        this.myEarthquakeDataArrayList = myEarthquakeDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.earthquake_row,viewGroup,false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.Time.setText(getTime(myEarthquakeDataArrayList.get(i).getTime()));
        myViewHolder.Place.setText(myEarthquakeDataArrayList.get(i).getPlace());
        myViewHolder.Mag.setText(myEarthquakeDataArrayList.get(i).getMag()+"");

    }

    @Override
    public int getItemCount() {
        return myEarthquakeDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Mag,Place,Time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Mag = itemView.findViewById(R.id.Mag_tv);
            Place = itemView.findViewById(R.id.Place_tv);
            Time = itemView.findViewById(R.id.Time_tv);
        }
    }

    public  String getTime(Long l){

        Date date = new Date(l);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return   timeFormat.format(date);

    }

}
