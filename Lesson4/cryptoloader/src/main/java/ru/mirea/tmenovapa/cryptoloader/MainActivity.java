package ru.mirea.tmenovapa.cryptoloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import ru.mirea.tmenovapa.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private final String LOG_TAG = getClass().getSimpleName();
    private static int AesLoaderID = 100000;
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void OnSendMessageButtonPressed(View v) {
        final Bundle bundle = new Bundle();
        final SecretKey aes_key = generateKey();
        PrintLogText(aes_key.toString() + " | "+ binding.sendMessageText.getText().toString());
        final byte[] encrypted_text = encryptMsg(binding.sendMessageText.getText().toString(), aes_key);
        bundle.putByteArray(AESDecipherLoader.ARG_KEY, aes_key.getEncoded());
        bundle.putByteArray(AESDecipherLoader.ARG_TEXT, encrypted_text);
        LoaderManager.getInstance(this).initLoader(++AesLoaderID, bundle, this);
    }
    private void PrintLogText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        Log.d(LOG_TAG, text);
    }

    public static byte[] encryptMsg(String message, SecretKey secret) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
    public static SecretKey generateKey() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("dwferh4wrhetjertwesfu3w4y3ewhr2343g344".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256, sr);
            return new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(id != AesLoaderID)
            throw new InvalidParameterException("Invalid aes loader id");

        PrintLogText("Loader create: " + id);
        return new AESDecipherLoader(this, args);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (loader.getId() == AesLoaderID) {
            PrintLogText(String.format("Decipher result: %s", data));
            LoaderManager.getInstance(this).destroyLoader(AesLoaderID);
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        PrintLogText("onLoaderReset");
    }
}