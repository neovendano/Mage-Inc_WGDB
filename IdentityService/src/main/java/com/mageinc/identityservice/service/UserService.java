package com.mageinc.identityservice.service;

import com.mageinc.identityservice.repo.UserRepo;
import com.mageinc.identityservice.repo.model.Status;
import com.mageinc.identityservice.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService{
    private final UserRepo userRepo;



    public List<User> fetchAll(){
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException {
        Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public long create(User newUser){
        final User savedUser = userRepo.save(newUser);
        return savedUser.getId();
    }

    public String fetchNameById(long id)throws IllegalArgumentException {
        Optional<User> maybeUser = userRepo.findById(id);
        String error = "User not found";
        if(maybeUser.isEmpty()) return error;
        else return maybeUser.get().getUsername();
    }


    public void update(long id, User newUser){
        String name = newUser.getUsername();
        String password = newUser.getPassword();
        String mail = newUser.getMail();
        int rate = newUser.getRate();
        Status status = newUser.getStatus();

        Optional<User> maybeUser = userRepo.findById(id);
        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        final User oldUser = maybeUser.get();

        if (name != null && !name.isBlank()) oldUser.setUsername(name);
        if (password != null && !password.isBlank()) oldUser.setPassword(password);
        if (mail != null && !mail.isBlank()) oldUser.setMail(mail);
        oldUser.setRate(rate);
        if (status != null) oldUser.setStatus(status);

        userRepo.save(oldUser);
    }


    public void delete(long id){
        userRepo.deleteById(id);
    }


}