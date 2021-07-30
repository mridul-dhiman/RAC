package com.example.rac.ui;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rac.R;
import com.example.rac.viewModels.UsersViewModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static com.example.rac.utils.Constants.SHARED_PREFS;
import static com.example.rac.utils.Constants.SHARED_PREFS_USER_EMAIL;

public class FinalActivity extends AppCompatActivity {
    private static final String TAG = "FinalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        TextView tvMessage = findViewById(R.id.tV_final_text);
        ImageView ivQRCode = findViewById(R.id.iv_qr_code);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, "The one who shouldn't be named");

        UsersViewModel usersViewModel = new UsersViewModel(this);

        usersViewModel.getUserName(email).observe(this, s -> {
            if (s != null && !s.isEmpty()) {
                tvMessage.setText(String.format(getResources().getString(R.string.str_final_message), s));
            }
        });

        usersViewModel.getTravelPlans(email).observe(this, travelPlan -> {
            if (travelPlan != null) {
                String date = travelPlan.getDate();
                int carSelected = travelPlan.getCarSelected();
                String adhaar = travelPlan.getAdhaar();
                String license = travelPlan.getLicense();

                String text = "Email: " + email + "\nDate: " + date + "\nCar: " + carSelected + "\nAdhaar: " + adhaar + "\nLicence: " + license;
                Log.d(TAG, "onCreate: " + text);

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivQRCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}