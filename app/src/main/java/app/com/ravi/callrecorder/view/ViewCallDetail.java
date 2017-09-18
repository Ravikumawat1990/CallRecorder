package app.com.ravi.callrecorder.view;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.database.tbl_notification;
import app.com.ravi.callrecorder.util.CM;
import app.com.ravi.callrecorder.util.Utils;

public class ViewCallDetail extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    LinearLayout layoutsSave, layoutedtCallNote, layoutsdelete, layoutsshare, layoutscall, layoutscontact_hist, layoutscontact_detail, layoutsdont_rec;
    TextView txt_call;
    String number;
    int PERMISSION_ALL = 1;
    String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
    };
    String filePath;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_view_call_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (checkPermissions()) {
            Log.d("TAG", "onCreate: ALL PERMISSIONS  GRANTED");
        }
        ImageView imageView = (ImageView) findViewById(R.id.imgPlay);


        layoutsSave = (LinearLayout) findViewById(R.id.saveLayout);
        layoutedtCallNote = (LinearLayout) findViewById(R.id.layoutEditCallNot);
        layoutsdelete = (LinearLayout) findViewById(R.id.deleteLayout);
        layoutsshare = (LinearLayout) findViewById(R.id.share_layout);
        layoutscall = (LinearLayout) findViewById(R.id.call_layout);
        layoutscontact_hist = (LinearLayout) findViewById(R.id.contact_layout);
        layoutscontact_detail = (LinearLayout) findViewById(R.id.contactDtl_layout);
        layoutsdont_rec = (LinearLayout) findViewById(R.id.dontRec_layout);
        txt_call = (TextView) findViewById(R.id.txt_call);
        layoutsSave.setOnClickListener(this);
        layoutedtCallNote.setOnClickListener(this);
        layoutsdelete.setOnClickListener(this);
        layoutsshare.setOnClickListener(this);
        layoutscall.setOnClickListener(this);
        layoutscontact_hist.setOnClickListener(this);
        layoutscontact_detail.setOnClickListener(this);
        layoutsdont_rec.setOnClickListener(this);
        txt_call.setOnClickListener(this);


        String intent = getIntent().getStringExtra("type");


        number = getIntent().getStringExtra("number");
        String calldura = getIntent().getStringExtra("calldura");
        String time = getIntent().getStringExtra("time");
        filePath = getIntent().getStringExtra("tempfile");
        id = getIntent().getStringExtra("id");

        setTitle(CM.getContactName(ViewCallDetail.this, number));

        TextView fileType = (TextView) findViewById(R.id.fileType);
        TextView dateTime = (TextView) findViewById(R.id.tv_date_time);
        TextView callDus = (TextView) findViewById(R.id.callDuration);
        ImageView pic = (ImageView) findViewById(R.id.imgUserProfile);
        dateTime.setText(time);
        callDus.setText(calldura);


        try {
            if (retrieveContactPhoto(ViewCallDetail.this, number) != null) {
                pic.setImageBitmap(retrieveContactPhoto(ViewCallDetail.this, number));
            } else {
                pic.setImageResource(R.mipmap.ic_person_black_48dp);
            }
        } catch (Exception e) {
            pic.setImageResource(R.mipmap.ic_person_black_48dp);

        }

        String someFilepath = filePath;
        String extension = someFilepath.substring(someFilepath.lastIndexOf("."));
        fileType.setText(extension);

        if (intent.equals("inbox")) {
            layoutsSave.setVisibility(View.VISIBLE);
        } else {
            layoutsSave.setVisibility(View.GONE);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewCallDetail.this, ViewAudioPlay.class);
                intent.putExtra("file", filePath);
                CM.startActivity(intent, ViewCallDetail.this);

            }
        });


        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CM.finishActivity(ViewCallDetail.this);

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    public static Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        number.replace("+91", "");
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

            if (contactId != null) {
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));
                if (inputStream != null) {
                    photo = BitmapFactory.decodeStream(inputStream);
                }

                assert inputStream != null;
                inputStream.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveLayout:
                showPopup(ViewCallDetail.this, "save", "Are you sure you want to save?");
                break;
            case R.id.layoutEditCallNot:

                Intent intent3 = new Intent(ViewCallDetail.this, ViewNotes.class);
                intent3.putExtra("id", id);
                CM.startActivity(intent3, ViewCallDetail.this);
                break;
            case R.id.deleteLayout:
                showPopup(ViewCallDetail.this, "delete", "Are you sure you want to delete?");
                break;
            case R.id.share_layout:
                CM.shareData(this, "Contact Number : " + filePath);
                break;
            case R.id.call_layout:
                break;
            case R.id.contact_layout:
                Intent intent1 = new Intent(this, ViewContactHistory.class);
                intent1.putExtra("number", number);
                CM.startActivity(intent1, this);
                break;
            case R.id.contactDtl_layout:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(getContactIDFromNumber(number, ViewCallDetail.this)));
                intent.setData(uri);
                startActivity(intent);
                break;
            case R.id.dontRec_layout:
                break;
            case R.id.txt_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+" + number.toString().trim()));
                startActivity(callIntent);
                break;


        }

    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_ALL);
            return false;
        }
        return true;
    }

    public void showPopup(Context context, final String key, String value) {
        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.app_name))
                .setMessage(value)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (key.equals("save")) {
                            tbl_notification.updateIsSave(id, "true");
                        } else if (key.equals("delete")) {

                            File isPathExist = new File(filePath);
                            try {
                                if (isPathExist.exists()) {
                                    delete(isPathExist);
                                    boolean isDelete = tbl_notification.deleteItem(id);
                                    Log.i("TAG", "onClick: " + isDelete);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setIcon(R.drawable.ic_menu_share).show();
    }

    void delete(File f) throws IOException {

        try {
            boolean deleted = f.delete();
            Log.i("TAG", "delete: " + deleted);
        } catch (Exception e) {
            Log.i("TAG", "delete: " + e.getMessage());

        }

    }

    public static int getContactIDFromNumber(String contactNumber, Context context) {
        contactNumber = Uri.encode(contactNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, contactNumber), new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();
        return phoneContactID;
    }
}
