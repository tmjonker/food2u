package com.tmjonker.food2u.encryption;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.config.TinkConfig;

import java.security.GeneralSecurityException;

public class PasswordEncryptor {

    private KeysetHandle keysetHandle;

    public byte[] encryptPassword(String password, String email) throws GeneralSecurityException {
        TinkConfig.register();

        keysetHandle = KeysetHandle.generateNew(AesGcmKeyManager.aes128GcmTemplate());
        Aead aead = keysetHandle.getPrimitive(Aead.class);

        return aead.encrypt(password.getBytes(), email.getBytes()); // returns encrypted password.
    }

    public KeysetHandle getKeysetHandle() {

        return keysetHandle;
    }
}
