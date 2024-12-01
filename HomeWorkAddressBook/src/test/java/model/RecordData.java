package model;

import java.util.Objects;

public final class RecordData {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String photo;

    public RecordData(String id, String firstName, String lastName, String address, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.photo = photo;
    }

    public RecordData() {
        this("", "", "", "", "");
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
        return new RecordData(this.id, firstName, this.lastName, this.address, this.photo);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.id, this.firstName, lastName, this.address, this.photo);
    }

    public RecordData withId(String id) {
        return new RecordData(id, this.firstName, this.lastName, this.address, this.photo);
    }

    public RecordData withAddress(String id) {
        return new RecordData(this.id, this.firstName, this.lastName, address, this.photo);
    }

    public RecordData withPhoto(String photo) {
        return new RecordData(this.id, this.firstName, this.lastName, this.address, photo);
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
