package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Config.SecurityUtils;
import com.torpedolabs.ticketbackend.ticket.Dao.User;
import com.torpedolabs.ticketbackend.ticket.Dao.UserAuthority;
import com.torpedolabs.ticketbackend.ticket.Model.Request.UserRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.AuthorityRepository;
import com.torpedolabs.ticketbackend.ticket.Repository.UserAuthorityRepository;
import com.torpedolabs.ticketbackend.ticket.Repository.UserRepository;
import com.torpedolabs.ticketbackend.ticket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserWithAuthorities() {
        // System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUserName);
    }

    @Override
    public ResponseEntity<?> Save(UserRequest userRequest) {
        String password=userRequest.getPassword();
        userRequest.setPassword(passwordEncoder.encode(password));
        User user=new User(userRequest);
        user=userRepository.save(user);
        //User SetRole
        userAuthorityRepository.save(new UserAuthority(user, authorityRepository.findById(userRequest.getRole()).get()));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        userRepository.findById(id).ifPresent(user -> {user.setActive(false);
        userRepository.save(user);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
