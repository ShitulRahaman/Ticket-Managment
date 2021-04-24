package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Authority;
import com.torpedolabs.ticketbackend.ticket.Model.Request.AuthorityRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.AuthorityRepository;
import com.torpedolabs.ticketbackend.ticket.Service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public ResponseEntity<?> Save(AuthorityRequest authorityRequest) {
        Authority authority=new Authority(authorityRequest);
        authorityRepository.save(authority);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(AuthorityRequest authorityRequest) {
        authorityRepository.findById(authorityRequest.getId()).ifPresent(authority -> {
            authority.setName(authorityRequest.getName());
            authorityRepository.save(authority);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(authorityRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(authorityRepository.findAll());
    }
}
