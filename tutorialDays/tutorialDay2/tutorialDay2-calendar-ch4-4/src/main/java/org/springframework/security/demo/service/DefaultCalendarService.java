package org.springframework.security.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.demo.dataaccess.CalendarUserDao;
import org.springframework.security.demo.dataaccess.EventDao;
import org.springframework.security.demo.domain.CalendarUser;
import org.springframework.security.demo.domain.Event; 
import org.springframework.stereotype.Repository;

/**
 * A default implementation of {@link CalendarService} that delegates to {@link EventDao} and {@link CalendarUserDao}.
 *
 * @author Rob Winch
 *
 */

/**
 * A default implementation of {@link CalendarService} that delegates to {@link EventDao} and {@link CalendarUserDao}.
 *
 * @author Rob Winch
 *
 */
@Repository
public class DefaultCalendarService implements CalendarService {
    private final EventDao eventDao;
    private final CalendarUserDao userDao;
    private final JdbcOperations jdbcOperations;
    private final PasswordEncoder passwordEncoder ;
    

    @Autowired
    public DefaultCalendarService(EventDao eventDao, CalendarUserDao userDao, JdbcOperations jdbcOperations,
    		PasswordEncoder passwordEncoder) {
        if (eventDao == null) {
            throw new IllegalArgumentException("eventDao cannot be null");
        }
        if (userDao == null) {
            throw new IllegalArgumentException("userDao cannot be null");
        }
        if (jdbcOperations == null) {
            throw new IllegalArgumentException("jdbcOperations cannot be null");
        }
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder =passwordEncoder ;
    }

    public Event getEvent(int eventId) {
        return eventDao.getEvent(eventId);
    }

    public int createEvent(Event event) {
        return eventDao.createEvent(event);
    }

    public List<Event> findForUser(int userId) {
        return eventDao.findForUser(userId);
    }

    public List<Event> getEvents() {
        return eventDao.getEvents();
    }

    public CalendarUser getUser(int id) {
        return userDao.getUser(id);
    }

    public CalendarUser findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public List<CalendarUser> findUsersByEmail(String partialEmail) {
        return userDao.findUsersByEmail(partialEmail);
    }

    public int createUser(CalendarUser user) {
    	String encodedPassword =  this.passwordEncoder.encodePassword(user.getPassword(), null);
    	user.setPassword(encodedPassword);    	
        int userId = userDao.createUser(user);
        jdbcOperations.update("insert into calendar_user_authorities(calendar_user,authority) values (?,?)", userId,
                "ROLE_USER");
        return userId;
    }
}