package com.example.danielzhou.pennapps;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by guanr on 1/21/2018.
 */

public class ListPlayersAdapter extends ArrayAdapter {
    private final Activity context;
    private final String[] nameArray;
    private final String[] betArray;
    private double totalBet;

    public ListPlayersAdapter(@NonNull Activity context, String[] names, String[] bets) {
        super(context, R.layout.individual_game_row, names);
        this.context = context;
        nameArray = names;
        betArray = bets;
        totalBet = 0;
        for(String bet : betArray) {
            totalBet = totalBet + Double.parseDouble(bet);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.individual_game_row, null,true);
        TextView nameTextField = (TextView) rowView.findViewById(R.id.player_name);
        TextView betAmountTextField = (TextView) rowView.findViewById(R.id.bet_amount);
        TextView percentageWinTextField = (TextView) rowView.findViewById(R.id.percentage_win);

        nameTextField.setText(nameArray[position]);
        betAmountTextField.setText("$" + betArray[position]);
        int percentage = (int) Math.ceil(100 * (Double.parseDouble(betArray[position])/ totalBet));
        percentageWinTextField.setText("odds: " + Integer.toString(percentage) + "%");
        return rowView;
    }
}
