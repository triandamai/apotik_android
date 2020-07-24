package com.tdn.apotik_kasir.core.callback;

import androidx.annotation.NonNull;

public interface AuthListener {
    void onStart();

    void onSuccess(@NonNull String message, UserModel data);

    void onError(@NonNull String message);
}
