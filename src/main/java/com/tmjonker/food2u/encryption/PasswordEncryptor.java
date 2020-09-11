package com.tmjonker.food2u.encryption;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.config.TinkConfig;

import java.security.GeneralSecurityException;

public class PasswordEncryptor {

    public static byte[] encryptPassword(String password, String email) throws GeneralSecurityException {
        TinkConfig.register();

        KeysetHandle keysetHandle = KeysetHandle.generateNew(AesGcmKeyManager.aes128GcmTemplate());
        Aead aead = keysetHandle.getPrimitive(Aead.class);

        return aead.encrypt(password.getBytes(), email.getBytes()); // returns encrypted password.
    }
}
