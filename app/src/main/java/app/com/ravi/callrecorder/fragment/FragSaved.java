package app.com.ravi.callrecorder.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.adapter.callAdapter;
import app.com.ravi.callrecorder.database.NotiModel;
import app.com.ravi.callrecorder.database.tbl_notification;
import app.com.ravi.callrecorder.model.EntryItem;
import app.com.ravi.callrecorder.model.Item;
import app.com.ravi.callrecorder.model.SectionItem;


public class FragSaved extends Fragment {


    Activity thisActivity;
    private callAdapter mAdapter;
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<NotiModel> models;

    public void onAttach(Context context) {
        super.onAttach(context);
        try {

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_saved, container, false);
        thisActivity = getActivity();


        ListView listView = (ListView) rootView.findViewById(R.id.listview1);


      /*  ArrayList<CallDetailPojo> detailPojos = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            CallDetailPojo callDetailPojo = new CallDetailPojo();
            callDetailPojo.setName("Ravi kumawat");
            callDetailPojo.setCallDuration("02:02");
            callDetailPojo.setCallType("Incomming");
            callDetailPojo.setPerPic("");
            callDetailPojo.setIsCloud("0");
            callDetailPojo.setTime("Monday   02:02");
            detailPojos.add(callDetailPojo);
        }*/
       /* models = tbl_notification.getAllData();
        for (int i = 0; i < models.size(); i++) {

            items.add(new SectionItem("", models.get(i).getDatetime()));
            for (int j = 0; j < 5; j++) {
                items.add(new EntryItem(getContactName(thisActivity, models.get(i).getName()), models.get(i).getPerPic(), models.get(i).getDatetime(), models.get(i).getCallDuration(), models.get(i).getIsCloud()));
            }
        }
        mAdapter = new callAdapter(items, thisActivity);


        listView.setAdapter(mAdapter);*/
        //  listView.invalidate();
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {


    }


    @Override
    public void onResume() {
        super.onResume();
       /* if (models != null) {
            models.clear();
        }
        models = tbl_notification.getSortDate();
        if (models != null) {
            models.size();
            mAdapter.notifyDataSetChanged();
        }*/
    }

    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }
}
