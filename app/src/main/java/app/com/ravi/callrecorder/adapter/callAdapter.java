package app.com.ravi.callrecorder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.model.EntryItem;
import app.com.ravi.callrecorder.model.Item;
import app.com.ravi.callrecorder.model.SectionItem;
import app.com.ravi.callrecorder.view.ViewCallDetail;


/**
 * Created by Moweb on 24-12-2015.
 */
public class callAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public callAdapter(ArrayList<Item> items, Context context) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Item i = items.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.header_item, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                TextView tv_date_time = (TextView) v.findViewById(R.id.tv_date_time);
                tv_date_time.setText(si.getTime());


            } else {


                EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.section_item, null);
                ImageView imageView = (ImageView) v.findViewById(R.id.imgUserProfile);
                final TextView tv_person_name = (TextView) v.findViewById(R.id.tv_person_name);
                final TextView tx_call_duration = (TextView) v.findViewById(R.id.tx_call_duration);
                final TextView tx_call_time = (TextView) v.findViewById(R.id.tx_call_time);
                LinearLayout rootView = (LinearLayout) v.findViewById(R.id.rootView);

                tv_person_name.setText(ei.getName());
                tx_call_duration.setText(ei.getCallDuration());
                tx_call_time.setText(ei.getIsCloud());
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, ViewCallDetail.class);
                        intent.putExtra("type", "inbox");
                        context.startActivity(intent);
                    }
                });


            }
        }
        return v;
    }


}

