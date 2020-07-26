package com.tdn.apotik_kasir.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.apotik_kasir.R;
import com.tdn.apotik_kasir.core.VMFactory;
import com.tdn.apotik_kasir.core.callback.AuthListener;
import com.tdn.apotik_kasir.databinding.ActivityLoginBinding;
import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this, new VMFactory(LoginActivity.this, authListener)).get(LoginViewModel.class);

        binding.login.setOnClickListener(view -> {
            viewModel.masuk(binding.username.getText().toString(), binding.password.getText().toString());
        });

    }

    private AuthListener authListener = new AuthListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
            binding.login.setVisibility(View.GONE);
            binding.loading.setVisibility(View.VISIBLE);
        }

        @Override
        public void onSuccess(@NonNull String message, UserModel data) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            MyUser.getInstance(LoginActivity.this).setUser(data);
            binding.login.setVisibility(View.VISIBLE);
            binding.loading.setVisibility(View.GONE);
        }

        @Override
        public void onError(@NonNull String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            binding.login.setVisibility(View.VISIBLE);
            binding.loading.setVisibility(View.GONE);
        }
    };
}