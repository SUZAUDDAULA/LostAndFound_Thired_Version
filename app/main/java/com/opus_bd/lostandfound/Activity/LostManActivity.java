package com.opus_bd.lostandfound.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LostManActivity extends AppCompatActivity {
    @BindView(R.id.tvModel)
    TextView tvModel;

    @BindView(R.id.tvRegNoName)
    TextView tvRegNoName;

    @BindView(R.id.tvEngineNo)
    TextView etEngineNo;

    @BindView(R.id.etChesisNo)
    TextView etChesisNo;

    @BindView(R.id.etCCNo)
    TextView etCCNo;

    @BindView(R.id.etMadeIn)
    TextView etMadeIn;

    @BindView(R.id.etMadeDate)
    TextView etMadeDate;

    @BindView(R.id.etIdentitySign)
    TextView etIdentitySign;

    @BindView(R.id.etAddressDetails)
    TextView etAddressDetails;

    @BindView(R.id.etVehicleDate)
    TextView etVehicleDate;

    @BindView(R.id.etVehicleTime)
    TextView etVehicleTime;

    boolean isllVehicleEntryChecked = true;
    @BindView(R.id.llVehicleInfromation)
    LinearLayout llVehicleInfromation;

    @BindView(R.id.ivVehicleInformation)
    ImageView ivVehicleInformation;

    boolean isllVehicleIdentificationChecked = true;
    @BindView(R.id.llVIdentityInfo)
    LinearLayout llVIdentityInfo;

    @BindView(R.id.ivVIdentityInfo)
    ImageView ivVIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llVPATInfo)
    LinearLayout llVPATInfo;

    @BindView(R.id.ivVPATInfo)
    ImageView ivVPATInfo;

    boolean isllVehicleAttachment = true;
    @BindView(R.id.llVehicleAttachment)
    LinearLayout llVehicleAttachment;

    @BindView(R.id.ivVehicleAttachment)
    ImageView ivVehicleAttachment;

    //TextView

    @BindView(R.id.spnSPDivision)
    TextView spnSPDivision;

    @BindView(R.id.spnSPDistrict)
    TextView spnSPDistrict;

    @BindView(R.id.spnSPThana)
    TextView spnSPThana;

    @BindView(R.id.spnDocumentType)
    TextView spnDocumentType;

    @BindView(R.id.spnVehicleType)
    TextView spnVehicleType;

    @BindView(R.id.spnMadeBy)
    TextView spnMadeBy;

    @BindView(R.id.spnRegNoName1)
    TextView spnRegNoName1;

    @BindView(R.id.spnRegNoName2)
    TextView spnRegNoName2;

    @BindView(R.id.spnColor)
    TextView spnColor;


    @BindView(R.id.etBlueBook)
    TextView etBlueBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_man);
        ButterKnife.bind(this);
    }
    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InformationEntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }
    //DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }




    //File Upload

 /*   public void FileUpload() {

        //on upload button Click
        if (selectedFilePath != null) {
            dialog = ProgressDialog.show(VehicleEntryActivity.this, "", "Uploading File...", true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //creating new thread to handle Http Operations
                    uploadFile(selectedFilePath);
                }
            }).start();
        } else {
            Toast.makeText(VehicleEntryActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
        }

    }*/

/*    @OnClick(R.id.etBlueBook)
    public void etBlueBook() {
        try {
            showFileChooser();
        } catch (Exception e) {
            Utilities.showLogcatMessage(" " + e.toString());

        }
    }*/

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        //starts new activity to select file and return data
        startActivityForResult(intent, 1);
        Utilities.showLogcatMessage(" File Choose");
    }
/*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (resultCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        String path = data.getData().getPath();
                        etBlueBook.setText(path);
                    }


                    break;

            }
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());

        }


    }
*/


    /*    @RequiresApi(AppAPI = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:


                break;

        }

       *//* super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
          *//**//*  if (requestCode == PICK_FILE_REQUEST) {
                try {
                    if (data == null) {
                        //no data present
                        Utilities.showLogcatMessage(" no data present");
                        return;
                    }


                    Uri selectedFileUri = data.getData();
                    String path=data.getData().getPath();
               *//**//**//**//* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    selectedFilePath = FilePath.getPath(this, selectedFileUri);
                }*//**//**//**//*
                    etBlueBook.setText(path);
                    Utilities.showLogcatMessage("Selected File Path:" + path);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        etBlueBook.setText(selectedFilePath);
                    } else {
                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Utilities.showLogcatMessage(" "+e.toString());
                }

            }*//**//*
        }*//*
    }*/

/*    //android upload file to server
    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        if (!selectedFile.isFile()) {
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etBlueBook.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL("");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Utilities.showLogcatMessage("Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etBlueBook.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/" + fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(VehicleEntryActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(VehicleEntryActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(VehicleEntryActivity.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }*/

    //Multiple image
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utilities.showLogcatMessage("requestCode "+requestCode);
        Utilities.showLogcatMessage("resultCode "+resultCode);
        Utilities.showLogcatMessage("data "+data);
      *//*  try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                    gvGallery1.setAdapter(galleryAdapter);
                    gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                            .getLayoutParams();
                    mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);
                    tvPhoto.setText("Selected Images " + mArrayUri.size());

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                            gvGallery1.setAdapter(galleryAdapter);
                            gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                                    .getLayoutParams();
                            mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        tvPhoto.setText("Selected Images " + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG)
                    .show();
            Utilities.showLogcatMessage(" "+e.getLocalizedMessage());
        }*//*


    }*/


   /* // Multipole Image picker
public void ImagePicker(){
    Options options = Options.init()
            .setRequestCode(200)                                           //Request code for activity results
            .setCount(3)                                                   //Number of images to restict selection count
            .setFrontfacing(false)                                         //Front Facing camera on start
           *//* .setPreSelectedUrls(returnValue)                               //Pre selected Image Urls*//*
            .setExcludeVideos(false)                                       //Option to exclude videos
            .setVideoDurationLimitinSeconds(30)                            //Duration for video recording
            .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
            .setPath("/pix/images");                                       //Custom Path For media Storage

    Pix.start(VehicleEntryActivity.this, options);


}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 200) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
        }
    }*/
}
