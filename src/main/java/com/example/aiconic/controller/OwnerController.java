package com.example.aiconic.controller;

import com.example.aiconic.domain.Owner;
import com.example.aiconic.service.AuthRequest;
import com.example.aiconic.service.owner.AuthResponse;
import com.example.aiconic.service.owner.OwnerDto;
import com.example.aiconic.service.owner.OwnerRequest;
import com.example.aiconic.service.owner.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Owner> getAll(){
        return ownerService.getAll();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public OwnerDto getById(@PathVariable Long userId){
        return ownerService.getById(userId);
    }

    @GetMapping("/walletAddress/")
    @ResponseStatus(HttpStatus.OK)
    public OwnerDto getByWalletAddress(String walletAddress){
        return ownerService.getByWalletAddress(walletAddress);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse createUser(@RequestBody OwnerRequest ownerRequest){
        return ownerService.createUser(ownerRequest);
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest request){
        return ownerService.authenticate(request);
    }

    @PutMapping("/update/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public OwnerDto updateUser(@PathVariable Long userId, @RequestBody OwnerRequest ownerRequest){
        return ownerService.updateUser(userId, ownerRequest);
    }

    @DeleteMapping("/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long userId){
        ownerService.delete(userId);
    }
}

