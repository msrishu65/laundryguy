package com.laundryguy.model.entity;

import com.laundryguy.model.apiRequest.BookingAddressDetails;

import javax.persistence.*;

/**
 * Created by maninder on 9/7/16.
 */
@Entity
@Table(name = "booking_address")
public class BookingAddress {
    private String addressId;
    private String houseNo;
    private String society;
    private String city;
    private String state;
    private String longitude;
    private String latitude;

    @Id
    @Column(name = "addressId", nullable = false, insertable = true, updatable = true, length = 100)
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "house_no", nullable = true, insertable = true, updatable = true, length = 20)
    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    @Basic
    @Column(name = "society", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "longitude", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingAddress that = (BookingAddress) o;

        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (houseNo != null ? !houseNo.equals(that.houseNo) : that.houseNo != null) return false;
        if (society != null ? !society.equals(that.society) : that.society != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (houseNo != null ? houseNo.hashCode() : 0);
        result = 31 * result + (society != null ? society.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }

    public static BookingAddress build(String addressId, BookingAddressDetails addressDetails) {
        BookingAddress address=new BookingAddress();
        address.setAddressId(addressId);
        address.setCity(addressDetails.getCity());
        address.setHouseNo(addressDetails.getHouseNumber());
        address.setSociety(addressDetails.getSociety());
        address.setState(addressDetails.getState());
        return address;
    }
}
