package com.smi.android.booklist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Smily on 3/17/2017.
 */

public class BookAdapter extends ArrayAdapter<Book>{

    private String formatRating(double rating) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(rating);
    }

    public BookAdapter(Activity context, ArrayList Book){
        super(context, 0, Book);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Book current = getItem(position);

        TextView view1 = (TextView) listView.findViewById(R.id.title);
        view1.setText("Title : " + current.getTitle());

        TextView view2 = (TextView) listView.findViewById(R.id.author);
        view2.setText("Author : " + current.getAuthor());

        TextView view3 = (TextView) listView.findViewById(R.id.rating);
        String rating = formatRating(current.getRating());
        view3.setText("Rating : " + rating);

        return listView;
    }
}
