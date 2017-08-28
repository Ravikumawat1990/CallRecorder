package app.com.ravi.callrecorder.adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


                final EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.section_item, null);
                ImageView imageView = (ImageView) v.findViewById(R.id.imgUserProfile);
                ImageView imgCallType = (ImageView) v.findViewById(R.id.imgCallType);


                try {
                    imageView.setImageBitmap(retrieveContactPhoto(context, ei.getName()));

                } catch (Exception e) {

                }

                final TextView tv_person_name = (TextView) v.findViewById(R.id.tv_person_name);
                final TextView tx_call_duration = (TextView) v.findViewById(R.id.tx_call_duration);
                final TextView tx_call_time = (TextView) v.findViewById(R.id.tx_call_time);
                CardView rootView = (CardView) v.findViewById(R.id.rootView);

                if (ei.getCallType() != null) {
                    if (ei.getCallType().equals("true")) {
                        imgCallType.setImageResource(R.mipmap.ic_call_received_black_24dp);
                    } else {
                        imgCallType.setImageResource(R.mipmap.ic_call_made_black_24dp);
                    }
                }

                tv_person_name.setText(ei.getName());
                Date date;
                if (ei.getCallDuration().toString().equals("0") || ei.getCallDuration().equals("")) {
                    //  date = new Date(ei.getCallDuration());
                } else {
                    date = new Date(Long.parseLong(ei.getCallDuration()));
                    DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
                    String dateFormatted = formatter.format(date);
                    tx_call_duration.setText(dateFormatted);
                }


                tx_call_time.setText(ei.getTime());
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Log.i("ADAPTER", "onClick: " + ei.getTempfilepath());
                        Log.i("ADAPTER", "onClick: " + ei.getName());
                        Log.i("ADAPTER", "onClick: " + ei.getCallDuration());
                        Log.i("ADAPTER", "onClick: " + ei.getTime());


                        Intent intent = new Intent(context, ViewCallDetail.class);
                        intent.putExtra("type", "inbox");
                        intent.putExtra("number", ei.getName());
                        intent.putExtra("calldura", ei.getCallDuration());
                        intent.putExtra("time", ei.getTime());
                        intent.putExtra("tempfile", ei.getTempfilepath());
                        context.startActivity(intent);
                    }
                });


            }
        }
        return v;
    }


    public static Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_notifications_black_24dp);

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }


}

