package com.RiderRegistration.RiderRegistration.service;

import com.RiderRegistration.RiderRegistration.entity.Role;
import com.RiderRegistration.RiderRegistration.entity.User;

public interface UserService {
    void save (User theUser);

    void save (Role theRole);
}
