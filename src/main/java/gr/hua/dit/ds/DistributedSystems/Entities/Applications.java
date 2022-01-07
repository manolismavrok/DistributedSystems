package gr.hua.dit.ds.DistributedSystems.Entities;

import javax.persistence.*;

@Entity
@Table(name = "applications")
public class Applications {

    @Id
    @Column(name = "id", nullable = false, length = 10)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "address1", nullable = false, length = 10)
    private String address1;

    @Column(name = "address2", nullable = false, length = 50)
    private String address2;

    @Column(name = "zip", nullable = false, length = 10)
    private String zip;

    @Column(name = "unempl_date", nullable = false)
    private String unemplDate;

    @Column(name = "photo", nullable = false, length = 50)
    private String photo;

    @Column(name = "validated", nullable = true, length = 5)
    private String validated;

    @Column(name = "confirmed", nullable = true, length = 5)
    private String confirmed;

    public Applications() {

    }

    public Applications(int id, String firstName, String lastName, String email, String phoneNumber, String city, String address1, String address2, String zip, String unemplDate, String photo, String validated, String confirmed) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.unemplDate = unemplDate;
        this.photo = photo;
        this.validated = validated;
        this.confirmed = confirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUnemplDate() {
        return unemplDate;
    }

    public void setUnemplDate(String unemplDate) {
        this.unemplDate = unemplDate;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValidated() {
        return validated;
    }

    public void setValidated(String validated) {
        this.validated = validated;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Applications{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", zip='" + zip + '\'' +
                ", unemplDate='" + unemplDate + '\'' +
                ", photo='" + photo + '\'' +
                ", validated='" + validated + '\'' +
                ", confirmed='" + confirmed + '\'' +
                '}';
    }
}
