package com.order.order.service;

import com.order.order.entity.Role;
import com.order.order.entity.User;

public interface UserService {
    void save (User theUser);

    void save (Role theRole);
}
