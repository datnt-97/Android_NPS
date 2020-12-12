package base.core.mudules.files_management;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.nps.R;

import base.core.lib.constant;
import base.core.lib.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.FileUtils.copy;
import static base.core.lib.constant.IO_BUFFER_SIZE;

public class FileBase {
    //    Directory
    public static final String DIRECTORY_FILE_DOWNLOAD = "/App";

    public static void pickImage(Context context, boolean isMutil) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, "Access denied from camera", null);
            return;
        }
        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_INFOR, util.ALERT_TYPE.ALERT_TYPE_ORTHER, "",
                new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                    @Override
                    public Boolean apply(AlertDialog.Builder builder, View view) {
                        Activity activity = (Activity) context;
                        TextView title = view.findViewById(R.id.alert_title);
                        Button btnClose = view.findViewById(R.id.btn_close);
                        LinearLayout layout = view.findViewById(R.id.layout_mess);
                        layout.removeViewAt(0);
                        title.setText("Select image");
                        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LinearLayout v = (LinearLayout) inflater.inflate(R.layout.select_img, null);

                        //TODO : create choosen file
                        ImageButton btnCamera = v.findViewById(R.id.btn_camera);
                        btnCamera.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                activity.startActivityForResult(takePicture, constant.TASK_REQUEST_CODE);
                            }
                        });
                        ImageButton btnGallery = v.findViewById(R.id.btn_gallery);
                        btnGallery.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                pickPhoto.putExtra(Intent.EXTRA_MIME_TYPES, constant.MIME_TYPE_IMAGE);
                                pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, isMutil);
                                pickPhoto.setAction(Intent.ACTION_GET_CONTENT);
                                activity.startActivityForResult(pickPhoto, constant.GALLERY_REQUEST_CODE);
                            }
                        });
                        layout.addView(v);
                        builder.setView(view);

                        final AlertDialog dialog = builder.show();
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        return true;
                    }
                });

    }

    public static void saveFile(Context context, String fileName, byte[] data) throws IOException {
        File apkStorage = new File(context.getFilesDir() + DIRECTORY_FILE_DOWNLOAD);
        if (!apkStorage.exists()) {
            apkStorage.mkdir();
            Log.e("Directory", "Directory Created.");
        }
        File outputFile = new File(apkStorage, fileName);
        try {
            outputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location
            long lenghtOfFile = data.length;
            InputStream input = new ByteArrayInputStream(data);
            byte dataPush[] = new byte[1024];

            long total = 0;
            int count = 0;
            while ((count = input.read(dataPush)) != -1) {
                total += count;
                fos.write(data, 0, count);
            }
            fos.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public static void downloadAndSaveFile(Context context, String url, String fileName) {
        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_INFOR, util.ALERT_TYPE.ALERT_TYPE_ORTHER, "",
                new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public Boolean apply(AlertDialog.Builder builder, View view) {
                        Activity activity = (Activity) context;
                        TextView title = view.findViewById(R.id.alert_title);
                        title.setText("Progress");
                        Button btnClose = view.findViewById(R.id.btn_close);
                        LinearLayout layout = view.findViewById(R.id.layout_mess);
                        layout.removeViewAt(0);
                        btnClose.setVisibility(View.GONE);
                        //set progress bar
                        layout.setOrientation(LinearLayout.HORIZONTAL);
                        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
                        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                        progressBar.setProgress(0);
                        progressBar.setIndeterminate(false);
                        progressBar.setPadding(0, 0, 15, 0);
                        layout.addView(progressBar);
                        //text view
                        TextView textView = new TextView(context);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView.setText("0");
                        layout.addView(textView);

                        builder.setView(view);
                        final AlertDialog dialog = builder.show();
                        return true;
                    }
                });
    }

    public static Bitmap loadBitmap(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }


}
