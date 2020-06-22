package com.opus_bd.lostandfound.Activity.PoliceInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.security.Permission;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {
    @BindView(R.id.tvList)
    TextView tvList;
  List<String> contact;
    Context context;
    private static final int REQUEST_RUNTIME_PERMISSION = 123;
    String[] permissons = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_main);
        ButterKnife.bind(this);
        context = this;

        if (CheckPermission(this, permissons[0])) {
            // you have permission go ahead
           getContactList();

        } else {
            // you do not have permission go request runtime permissions
            RequestPermission(this, permissons, REQUEST_RUNTIME_PERMISSION);
        }
    }

    private void getContactList () {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Utilities.showLogcatMessage("Name: " + name + "  Phone Number: " + phoneNo);
                        contact.add("Name: " + name + "  Phone Number: " + phoneNo+"\n");


                    }
                    pCur.close();
                }
            }

        }
        if (cur != null) {
            cur.close();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {

            case REQUEST_RUNTIME_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // you have permission go ahead
                    getContactList();
                } else {
                    // you do not have permission show toast.
                }
                return;
            }
        }

    }
        public void RequestPermission(Activity activity,String[] permissions,int Code) {
            if (ContextCompat.checkSelfPermission(activity,
                    permissions[0])
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        permissions[0])) {
                } else {
                    ActivityCompat.requestPermissions(activity, permissions,
                            Code);
                }
            }
        }

        public boolean CheckPermission(Context context, String Permission) {
            if (ContextCompat.checkSelfPermission(context,
                    Permission) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }


}