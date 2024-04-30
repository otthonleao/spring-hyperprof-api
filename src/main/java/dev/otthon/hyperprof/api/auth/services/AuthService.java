package dev.otthon.hyperprof.api.auth.services;

import dev.otthon.hyperprof.api.auth.dtos.LoginRequest;
import dev.otthon.hyperprof.api.auth.dtos.LoginResponse;
import dev.otthon.hyperprof.api.auth.dtos.RefreshRequest;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refresh(RefreshRequest refreshRequest);
}
