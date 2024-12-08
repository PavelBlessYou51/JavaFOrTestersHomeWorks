package model;

import java.util.Objects;

public final class RecordData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String photo;
    private final String homePhone;
    private final String workPhone;
    private final String mobilePhone;
    private final String secondary;
    private final String email1;
    private final String email2;
    private final String email3;


    public RecordData(String id, String firstName, String lastName, String address, String photo, String homePhone, String workPhone, String mobilePhone, String secondary, String email1, String email2, String email3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.photo = photo;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.mobilePhone = mobilePhone;
        this.secondary = secondary;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
    }

    public RecordData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }


    @Override
    public String toString() {
        return "RecordData[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "address=" + address + ", " +
                "photo=" + photo + ']';
    }


    public RecordData withFirstName(String firstName) {
        return new RecordData(this.id, firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.id, this.firstName, lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withId(String id) {
        return new RecordData(id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withAddress(String address) {
        return new RecordData(this.id, this.firstName, this.lastName, address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withPhoto(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withHomePhone(String homePhone) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withWorkPhone(String workPhone) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withMobilePhone(String mobilePhone) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, mobilePhone, this.secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withSecondary(String secondary) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, secondary, this.email1, this.email2, this.email3);
    }

    public RecordData withEmail1(String email1) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, email1, this.email2, this.email3);
    }

    public RecordData withEmail2(String email2) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, email2, this.email3);
    }

    public RecordData withEmail3(String email3) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, this.photo, this.homePhone, this.workPhone, this.mobilePhone, this.secondary, this.email1, this.email2, email3);
    }

    public String id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String address() {
        return address;
    }

    public String photo() {
        return photo;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getSecondary() {
        return secondary;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RecordData) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
    }

}
