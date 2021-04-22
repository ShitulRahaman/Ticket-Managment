package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Arrangement;
import com.torpedolabs.ticketbackend.ticket.Model.Request.ArrangementRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.ArrangementRepository;
import com.torpedolabs.ticketbackend.ticket.Service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Autowired
    ArrangementRepository arrangementRepository;

    @Override
    public void Save(ArrangementRequest arrangementRequest) {

    }

    @Override
    public void Update(ArrangementRequest arrangementRequest) {

    }

    @Override
    public Optional<Arrangement> Get(Long id) {
        return arrangementRepository.findById(id);
    }

    @Override
    public void Delete(Long id) {

    }

    @Override
    public List<Arrangement> Gets() {
        return arrangementRepository.findAll();
    }
}
