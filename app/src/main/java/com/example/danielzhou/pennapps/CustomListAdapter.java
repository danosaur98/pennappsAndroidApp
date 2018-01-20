package com.example.danielzhou.pennapps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danielzhou on 1/20/18.
 */

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    private final ArrayList<String>  nameArray;

    private final ArrayList<String>  amountArray;

    private final ArrayList<String>  charityArray;

    private final ArrayList<String>  endDateArray;



    public CustomListAdapter(Activity context, ArrayList<String> nameArrayParam, ArrayList<String>  amountArrayParam, ArrayList<String>  charityArrayParam, ArrayList<String>  endDateArrayParam) {

        super(context, R.layout.listview_row, nameArrayParam);
        this.context = context;
        this.nameArray = nameArrayParam;
        this.amountArray = amountArrayParam;
        this.charityArray=charityArrayParam;
        this.endDateArray = endDateArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        //this code gets references to objects in the listview_row.xml file
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView amount = (TextView) rowView.findViewById(R.id.amount);
        TextView charity = (TextView) rowView.findViewById(R.id.charity);
        TextView endDate = (TextView) rowView.findViewById(R.id.endDate);


        //this code sets the values of the objects to values from the arrays
        name.setText(nameArray.get(position));
        amount.setText(amountArray.get(position));
        charity.setText(charityArray.get(position));
        endDate.setText(endDateArray.get(position));

        return rowView;

    }

    ;
}