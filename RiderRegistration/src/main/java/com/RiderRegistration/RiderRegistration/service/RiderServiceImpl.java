package com.RiderRegistration.RiderRegistration.service;

import com.RiderRegistration.RiderRegistration.entity.Rider;
import com.RiderRegistration.RiderRegistration.repository.RiderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService{
    private RiderRepository riderRepository;

    public RiderServiceImpl(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }
    @Override
    public List<Rider> findAll() {
        return riderRepository.findAll();
    }

    @Override
    public Rider findById(int theId) {
        Optional<Rider> result = riderRepository.findById(theId);
        Rider theRider = null;

        if (result.isPresent()) {
            theRider = result.get();
        } else {
            throw new RuntimeException("Rider with id: " + theId + "not found");
        }

        return theRider;
    }

    @Override
    public Rider save(Rider rider) {
        return riderRepository.save(rider);
    }

    @Override
    public void delete(int theId) {
        riderRepository.deleteById(theId);
    }
}
