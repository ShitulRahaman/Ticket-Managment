package com.torpedolabs.ticketbackend.ticket.ServiceImpl;

import com.torpedolabs.ticketbackend.ticket.Dao.Location;
import com.torpedolabs.ticketbackend.ticket.Model.Request.LocationRequest;
import com.torpedolabs.ticketbackend.ticket.Repository.LocationRepository;
import com.torpedolabs.ticketbackend.ticket.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public ResponseEntity<?> Save(LocationRequest locationRequest) {
        Location location = new Location(locationRequest);
        locationRepository.save(location);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Update(LocationRequest locationRequest) {
        locationRepository.findById(locationRequest.getId()).ifPresent(location -> {
            location.setName(locationRequest.getName());
            location.setArea(locationRequest.getArea());
            location.setStreet(locationRequest.getStreet());
            location.setCity(locationRequest.getCity());
            location.setCountry(locationRequest.getCountry());
            location.setActive(locationRequest.isActive());
            locationRepository.save(location);
        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Get(Long id) {
        return ResponseEntity.ok(locationRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> Delete(Long id) {
        locationRepository.findById(id).ifPresent(location -> {
            location.setActive(false);
            locationRepository.save(location);

        });
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> Gets() {
        return ResponseEntity.ok(locationRepository.findAll());
    }
}
