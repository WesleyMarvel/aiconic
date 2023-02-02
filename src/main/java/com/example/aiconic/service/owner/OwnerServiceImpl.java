package com.example.aiconic.service.owner;

import com.example.aiconic.domain.Owner;
import com.example.aiconic.repository.OwnerRepository;
import com.example.aiconic.service.AuthRequest;
import com.example.aiconic.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<Owner> getAll(){
        return ownerRepository.findAll();
    }

    @Override
    public OwnerDto getById(Long userId){
        Optional<Owner> optionalUser = ownerRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new NullPointerException("Record not found");
        }
        return OwnerDto.of(optionalUser.get());
    }

    @Override
    public OwnerDto getByWalletAddress(String walletAddress){
        Optional<Owner> optionalUser = ownerRepository.findByWalletAddress(walletAddress);
        if (optionalUser.isEmpty()){
            throw new NullPointerException("Record not found");
        }
        return OwnerDto.of(optionalUser.get());
    }

    @Override
    public AuthResponse createUser(OwnerRequest ownerRequest){
        var owner = Owner.builder()
                .userName(ownerRequest.getUserName())
                .email(ownerRequest.getEmail())
                .walletAddress(ownerRequest.getWalletAddress())
                .bio(ownerRequest.getBio())
                .socialMedia(ownerRequest.getSocialMedia())
                .profilePicture(ownerRequest.getProfilePicture())
                .profileBanner(ownerRequest.getProfileBanner())
                .role(ownerRequest.getRole())
                .password(passwordEncoder.encode(ownerRequest.getPassword()))
                .build();
        ownerRepository.save(owner);
        var jwtToken = jwtService.generateToken(owner);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getWalletAddress(),
                        request.getPassword()
                )
        );
        var owner = ownerRepository.findByWalletAddress(request.getWalletAddress())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(owner);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Override
    public OwnerDto updateUser(Long userId, OwnerRequest ownerRequest){
        Optional<Owner> optionalUser = ownerRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new NullPointerException("Record not found");
        }
        Owner owner = optionalUser.get();
        owner.setUserName(ownerRequest.getUserName());
        owner.setUserName(ownerRequest.getUserName());
        owner.setEmail(ownerRequest.getEmail());
        owner.setWalletAddress(ownerRequest.getWalletAddress());
        owner.setBio(ownerRequest.getBio());
        owner.setSocialMedia(ownerRequest.getSocialMedia());
        owner.setProfilePicture(ownerRequest.getProfilePicture());
        owner.setPassword(passwordEncoder.encode(ownerRequest.getPassword()));
        Owner updatedOwner = ownerRepository.save(owner);
        return OwnerDto.of(updatedOwner);
    }

    @Override
    public void delete(Long userId){
        if (userId == null){
            throw new NullPointerException("This record was not found");
        }
        ownerRepository.deleteById(userId);
    }
}

