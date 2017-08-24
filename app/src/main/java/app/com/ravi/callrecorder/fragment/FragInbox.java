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


public class FragInbox extends Fragment {


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
        View rootView = inflater.inflate(R.layout.frag_inbox, container, false);
        thisActivity = getActivity();


        ListView listView = (ListView) rootView.findViewById(R.id.listview);


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
        models = tbl_notification.getSortDate();

        if (models != null) {
            for (int i = 0; i < models.size(); i++) {

                items.add(new SectionItem("", models.get(i).getDatetime()));
                ArrayList<NotiModel> notiModels = tbl_notification.getAllData(models.get(i).getDatetime());
                for (int j = 0; j < notiModels.size(); j++) {
                    items.add(new EntryItem(getContactName(thisActivity, notiModels.get(i).getName()), notiModels.get(i).getPerPic(), notiModels.get(i).getDatetime(), notiModels.get(i).getCallDuration(), notiModels.get(i).getIsCloud()));
                }
            }
        }
        mAdapter = new callAdapter(items, thisActivity);


        listView.setAdapter(mAdapter);
        listView.invalidate();
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {


    }


    @Override
    public void onResume() {
        super.onResume();

        if (models != null) {
            models.clear();
            items.clear();
        }
        models = tbl_notification.getSortDate();
        if (models != null) {
            for (int i = 0; i < models.size(); i++) {

                items.add(new SectionItem("", models.get(i).getDatetime()));
                ArrayList<NotiModel> notiModels = tbl_notification.getAllData(models.get(i).getDatetime());
                for (int j = 0; j < notiModels.size(); j++) {
                    items.add(new EntryItem(getContactName(thisActivity, notiModels.get(i).getName()), notiModels.get(i).getPerPic(), notiModels.get(i).getDatetime(), notiModels.get(i).getCallDuration(), notiModels.get(i).getIsCloud()));
                }
            }
        }
        if (models != null) {
            models.size();
            mAdapter.notifyDataSetChanged();
        }

    }

    public String getContactName(Context context, final String phoneNumber) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName = "";
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }
}
