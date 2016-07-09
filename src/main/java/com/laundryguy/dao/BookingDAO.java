package com.laundryguy.dao;

import com.laundryguy.model.entity.Booking;
import com.laundryguy.model.entity.BookingAddress;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maninder on 9/7/16.
 */
@Repository
public class BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Booking booking) {
        getCurrentSession().save(booking);
    }

    public void saveAddress(BookingAddress address) {
        getCurrentSession().save(address);
    }

    public List<Booking> getBookings(String email) {
        String hql = "from Booking where email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        List<Booking> bookingList = query.list();
        return bookingList;
    }

    public BookingAddress getBookingAddress(String addressId) {
        String hql = "from BookingAddress where addressId=:addressId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("addressId", addressId);
        List<BookingAddress> bookingList = query.list();
        return bookingList == null ? null : bookingList.get(0);
    }
}
