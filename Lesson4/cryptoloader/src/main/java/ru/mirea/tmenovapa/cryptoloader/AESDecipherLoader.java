package ru.mirea.tmenovapa.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESDecipherLoader extends AsyncTaskLoader<String> {
    public static final String ARG_TEXT = "text";
    public static final String ARG_KEY = "key";
    private final byte[] cipher_text;
    private final SecretKey aes_key;

    public AESDecipherLoader(@NonNull Context context, @Nullable Bundle args) {
        super(context);
        if(args != null) {
            final byte[] decoded_key = args.getByteArray(ARG_KEY);
            aes_key = new SecretKeySpec(decoded_key, 0, decoded_key.length, "AES");
            cipher_text = args.getByteArray(ARG_TEXT);
        } else {
            cipher_text = null;
            aes_key = null;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        return decryptMsg(cipher_text, aes_key);
    }
    public static String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
