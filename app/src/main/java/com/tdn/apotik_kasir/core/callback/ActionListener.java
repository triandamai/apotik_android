package com.tdn.apotik_kasir.core.callback;

import androidx.annotation.NonNull;

public interface ActionListener {
    void onStart();
    void onSuccess(@NonNull String message);
    void onError(@NonNull String message);
}
