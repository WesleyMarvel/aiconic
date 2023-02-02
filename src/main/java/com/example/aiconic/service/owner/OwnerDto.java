package com.example.aiconic.service.owner;

import com.example.aiconic.domain.Owner;
import com.example.aiconic.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private String userName;
    private String email;
    private String walletAddress;
    private String bio;
    private String socialMedia;
    private String profilePicture;
    private String profileBanner;
    private String password;
    private Role role;

    public static OwnerDto of(Owner owner){
        Objects.requireNonNull(owner);
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setUserName(owner.getUserName());
        ownerDto.setEmail(owner.getEmail());
        ownerDto.setWalletAddress(owner.getWalletAddress());
        ownerDto.setBio(owner.getBio());
        ownerDto.setRole(owner.getRole());
        ownerDto.setSocialMedia(owner.getSocialMedia());
        ownerDto.setProfilePicture(owner.getProfilePicture());
        ownerDto.setProfileBanner(owner.getProfileBanner());
        return ownerDto;
    }
}
