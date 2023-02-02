package com.example.aiconic.service.owner;

import com.example.aiconic.domain.Owner;
import com.example.aiconic.service.AuthRequest;

import java.util.List;

public interface OwnerService {

    List<Owner> getAll();

    OwnerDto getById(Long userId);

    OwnerDto getByWalletAddress(String walletAddress);

//    OwnerDto createUser(OwnerRequest ownerRequest);

    public AuthResponse createUser(OwnerRequest ownerRequest);

    AuthResponse authenticate(AuthRequest request);

    OwnerDto updateUser(Long userId, OwnerRequest ownerRequest);

    void delete(Long userId);
}
