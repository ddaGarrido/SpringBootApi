package com.study.api.service;

import com.study.api.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> userStore = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    public User create(User user) {
        Long id = counter.incrementAndGet();
        user.setId(id);
        userStore.put(id, user);
        return user;
    }

    public Optional<User> update(Long id, User updatedUser) {
        if (userStore.containsKey(id)) {
            updatedUser.setId(id);
            userStore.put(id, updatedUser);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return userStore.remove(id) != null;
    }
}
