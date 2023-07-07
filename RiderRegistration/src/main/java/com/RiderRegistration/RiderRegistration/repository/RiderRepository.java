package com.RiderRegistration.RiderRegistration.repository;

import com.RiderRegistration.RiderRegistration.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Integer> { }
