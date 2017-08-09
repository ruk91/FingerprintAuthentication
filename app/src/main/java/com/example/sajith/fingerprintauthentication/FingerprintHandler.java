package com.example.sajith.fingerprintauthentication;

/**
 * Created by sajith on 8/2/2017.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.hardware.fingerprint.FingerprintManager;
        import android.Manifest;
        import android.os.CancellationSignal;
        import android.support.v4.app.ActivityCompat;
        import android.widget.Toast;


public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context context;

    public FingerprintHandler(Context mContext) {
        context = mContext;
    }

    //starting the fingerprint authentication process//
    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
       public void onAuthenticationError(int errMsgId, CharSequence errString) {
    Toast.makeText(context, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Authentication failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        //Toast.makeText(context, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {
            Toast.makeText(context, "Successfully Logged In!", Toast.LENGTH_LONG).show();

        context.startActivity(new Intent(context,
                QrScanner.class));
    }


}
