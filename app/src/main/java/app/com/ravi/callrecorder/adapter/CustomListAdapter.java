package app.com.ravi.callrecorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.database.NotiModel;
import app.com.ravi.callrecorder.model.Pojocallhist;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by NetSupport on 04-09-2017.
 */

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<NotiModel> items; //data source of the list adapter

    //public constructor
    public CustomListAdapter(Context context, ArrayList<NotiModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.callhistory, parent, false);
        }

        // get current item to be displayed
        NotiModel currentItem = (NotiModel) getItem(position);

        // get the TextView for item name and item description
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView callDuration = (TextView) convertView.findViewById(R.id.callDuration);
        TextView dateTime = (TextView) convertView.findViewById(R.id.tv_date_time);
        CircleImageView circleImageView = (CircleImageView) convertView.findViewById(R.id.imgUserProfile);
        //sets the text for item name and item description from the current item object
        name.setText(currentItem.getName());
        callDuration.setText(currentItem.getCallDuration());
        dateTime.setText(currentItem.getTime());

        // returns the view for the current row
        return convertView;
    }
}
