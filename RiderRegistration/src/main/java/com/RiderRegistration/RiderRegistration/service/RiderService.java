package com.RiderRegistration.RiderRegistration.service;

import com.RiderRegistration.RiderRegistration.entity.Rider;

import java.util.List;

public interface RiderService {
    List<Rider> findAll();

    Rider findById(int theId);

    Rider save(Rider rider);

    void delete(int theId);
}
