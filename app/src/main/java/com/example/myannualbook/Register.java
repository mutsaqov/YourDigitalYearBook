package com.example.myannualbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Register extends AppCompatActivity {

  private EditText txt_Fullname, txt_pass, txt_email, txt_user, txt_alamat, txt_instagram, txt_whatsapp, txt_line;
  private Button btn_signUp;
  private ImageView fotoProfile, btn_tambahfoto;

  private boolean profileFoto = false;
  private String profile_Url = "null";

  public static final int REQUEST_CODE_GALERY = 1;

  private ByteArrayOutputStream baos;

  Uri imageUri;

   // FirebaseDatabase db;
    DatabaseReference refrence;
    StorageReference storage;

   // LoadingDialog loadingDialog = new LoadingDialog(Register.this);

    ProgressDialog progressDialog;

    public static final String Database_Path = "dataSiswa";
    private static final String Storage_Path = "fotoProfile/";

    private FirebaseAuth f_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        storage     = FirebaseStorage.getInstance().getReference();
        refrence    = FirebaseDatabase.getInstance().getReference(Database_Path);
        f_auth      = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Register.this);

        fotoProfile     = findViewById(R.id.profile);
        btn_tambahfoto  = findViewById(R.id.tambahfoto);

        txt_Fullname    = findViewById(R.id.full_name);
//        txt_user        = findViewById(R.id.username);
        txt_email       = findViewById(R.id.email);
        txt_pass        = findViewById(R.id.password);
  //      txt_alamat      = findViewById(R.id.alamat);
        txt_instagram   = findViewById(R.id.instagram);
        txt_whatsapp    = findViewById(R.id.whatsapp);
        txt_line        = findViewById(R.id.line);

        btn_tambahfoto  = findViewById(R.id.tambahfoto);
        btn_tambahfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery, REQUEST_CODE_GALERY);
            }
        });

        btn_signUp   = findViewById(R.id.signup);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDataToFirebase();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (f_auth.getCurrentUser() != null) {
            //handle the already user login
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        byte[] compImg;

        if (resultCode == RESULT_OK && data != null && data.getData() != null && ((requestCode==REQUEST_CODE_GALERY))) {

            imageUri = data.getData();

            try {
                Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                baos = new ByteArrayOutputStream();
                bit = getResizedBitmap(bit);
                bit.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                compImg = baos.toByteArray();
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(compImg, 0, compImg.length);

                requestCode = REQUEST_CODE_GALERY;
                fotoProfile.setImageBitmap(bitmap2);
                profileFoto = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Bitmap getResizedBitmap (Bitmap image){
        int width   = image.getWidth();
        int height  = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = 640;
            height = (int) (width / bitmapRatio);

        } else {
            height  = 640;
            width   = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getFileExtension (Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void UploadDataToFirebase() {

        final String getNama_full   = txt_Fullname.getText().toString().trim();
//                    final String getUsername    = txt_user.getText().toString().trim();
        final String getEmail       = txt_email.getText().toString().trim();
        final String getPassword    = txt_pass.getText().toString().trim();
//                    final String getAlamat      = txt_alamat.getText().toString().trim();
        final String getInsta       = txt_instagram.getText().toString().trim();
        final String getWhatsapp    = txt_whatsapp.getText().toString().trim();
        final String getLine        = txt_line.getText().toString().trim();

        if (getNama_full.isEmpty()) {
            txt_Fullname.setError(getString(R.string.input_error_name));
            txt_Fullname.requestFocus();
            return;
        }

        if (getEmail.isEmpty()) {
            txt_email.setError(getString(R.string.input_error_email));
            txt_email.requestFocus();
            return;
        }

        if (getNama_full.isEmpty()) {
            txt_Fullname.setError(getString(R.string.input_error_name));
            txt_Fullname.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {
            txt_email.setError(getString(R.string.input_error_email_invalid));
            txt_email.requestFocus();
            return;
        }

        if (getPassword.isEmpty()) {
            txt_pass.setError(getString(R.string.input_error_password));
            txt_pass.requestFocus();
            return;
        }

        if (getPassword.length() < 6) {
            txt_pass.setError(getString(R.string.input_error_password_length));
            txt_pass.requestFocus();
            return;
        }

                progressDialog.setTitle("Uploading Data . . .");
                progressDialog.show();

                Bitmap bit_profile = ((BitmapDrawable) fotoProfile.getDrawable()).getBitmap();
                ByteArrayOutputStream imageProfileBytes = new ByteArrayOutputStream();
                bit_profile.compress(Bitmap.CompressFormat.JPEG, 100, imageProfileBytes);
                byte[] data = imageProfileBytes.toByteArray();

                StorageReference filePositiion = storage.child(Storage_Path + System.currentTimeMillis() + "," + getFileExtension(imageUri));
                UploadTask uploadTask = filePositiion.putBytes(data);
                uploadTask.continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        throw Objects.requireNonNull(task.getException());
                    }
                    return filePositiion.getDownloadUrl();
                }).addOnCompleteListener(task -> {
                    Uri downloadUri = task.getResult();
                    profile_Url = downloadUri.toString();


                    f_auth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                final String uploadDataID = refrence.push().getKey();

                                dbHelper uploadDataSiswa = new dbHelper(uploadDataID, getNama_full,  getEmail, getPassword, getInsta, getWhatsapp, getLine, profile_Url);
                                refrence.child(uploadDataID).setValue(uploadDataSiswa);

                            }else {
                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    progressDialog.dismiss();
                    clear();
                    Toast.makeText(getApplicationContext(), "Upload Success", Toast.LENGTH_SHORT).show();

                    profileFoto = false;

                    try {
                        baos.flush();
                        baos.close();
                    }catch (IOException E) {
                        E.printStackTrace();
                    }

                }).addOnFailureListener(exception -> {
                    progressDialog.dismiss();
                    exception.getMessage();
                }).addOnCompleteListener(taskSnapshot -> progressDialog.setMessage("Proses ..."));
        }


    private void clear() {

        txt_Fullname.requestFocus();
        fotoProfile.setImageResource(R.drawable.profil_icon);
        txt_Fullname.setText("");
      //  txt_user.setText("");
        txt_email.setText("");
        txt_pass.setText("");
//        txt_alamat.setText("");
        txt_instagram.setText("");
        txt_whatsapp.setText("");
        txt_line.setText("");

    }
}
