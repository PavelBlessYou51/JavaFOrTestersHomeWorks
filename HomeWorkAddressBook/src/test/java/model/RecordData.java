package model;

public record RecordData(String id, String firstName, String lastName) {

    public RecordData() {
        this("", "", "");
    }


    @Override
    public String toString() {
        return "RecordData[" +
                "id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }


    public RecordData withFirstName(String firstName) {
        return new RecordData(this.id, firstName, this.lastName);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.id, this.firstName, lastName);
    }

    public RecordData withId(String id) {
        return new RecordData(id, this.firstName, this.lastName);
    }
}
