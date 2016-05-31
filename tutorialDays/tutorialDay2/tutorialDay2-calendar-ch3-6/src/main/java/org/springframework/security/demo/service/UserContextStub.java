package org.springframework.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.demo.dataaccess.CalendarUserDao;
import org.springframework.security.demo.domain.CalendarUser;
import org.springframework.stereotype.Component;

/**
 * Returns the same user for every call to {@link #getCurrentUser()}. This is used prior to adding security, so that the
 * rest of the application can be used.
 *
 * @author Rob Winch
 */
//@Component
public class UserContextStub implements UserContext {
    private final CalendarUserDao userService;
    /**
     * The {@link CalendarUser#getId()} for the user that is representing the currently logged in user. This can be
     * modified using {@link #setCurrentUser(CalendarUser)}
     */
    private int currentUserId = 0;

    @Autowired
    public UserContextStub(CalendarUserDao userService) {
        if (userService == null) {
            throw new IllegalArgumentException("userService cannot be null");
        }
        this.userService = userService;
    }

    @Override
    public CalendarUser getCurrentUser() {
        return userService.getUser(currentUserId);
    }

    @Override
    public void setCurrentUser(CalendarUser user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        Integer currentId = user.getId();
        if(currentId == null) {
            throw new IllegalArgumentException("user.getId() cannot be null");
        }
        this.currentUserId = currentId;
    }
}