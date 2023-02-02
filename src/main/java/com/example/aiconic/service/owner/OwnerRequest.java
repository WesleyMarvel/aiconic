package com.example.aiconic.service.owner;

import com.example.aiconic.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {
    private String userName;
    private String email;
    private String walletAddress;
    private String bio;
    private Role role;
    private String socialMedia;
    private String profilePicture;
    private String profileBanner;
    private String password;
}
