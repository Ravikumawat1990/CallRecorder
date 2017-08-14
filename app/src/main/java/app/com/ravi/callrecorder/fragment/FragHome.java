package app.com.ravi.callrecorder.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.adapter.callAdapter;
import app.com.ravi.callrecorder.model.CallDetailPojo;
import app.com.ravi.callrecorder.model.EntryItem;
import app.com.ravi.callrecorder.model.Item;
import app.com.ravi.callrecorder.model.SectionItem;


public class FragHome extends Fragment {


    Activity thisActivity;
    private callAdapter mAdapter;
    ArrayList<Item> items = new ArrayList<Item>();

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
        View rootView = inflater.inflate(R.layout.frag_home, container, false);
        thisActivity = getActivity();


        ListView listView = (ListView) rootView.findViewById(R.id.listview);


        ArrayList<CallDetailPojo> detailPojos = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            CallDetailPojo callDetailPojo = new CallDetailPojo();
            callDetailPojo.setName("Ravi kumawat");
            callDetailPojo.setCallDuration("02:02");
            callDetailPojo.setCallType("Incomming");
            callDetailPojo.setPerPic("");
            callDetailPojo.setIsCloud("0");
            callDetailPojo.setTime("Monday   02:02");
            detailPojos.add(callDetailPojo);
        }

        for (int i = 0; i < detailPojos.size(); i++) {

            items.add(new SectionItem("", detailPojos.get(i).getTime()));
            for (int j = 0; j < 5; j++) {
                items.add(new EntryItem(detailPojos.get(i).getName(), detailPojos.get(i).getPerPic(), detailPojos.get(i).getTime(), detailPojos.get(i).getCallDuration(), detailPojos.get(i).getIsCloud()));
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


}
