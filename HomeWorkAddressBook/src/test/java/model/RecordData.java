package model;

public record RecordData(String firstName, String lastName) {

    public RecordData() {
        this("", "");
    }


    @Override
    public String toString() {
        return "RecordData[" +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ']';
    }


    public RecordData withFirstName(String firstName) {
        return new RecordData(firstName, this.lastName);
    }

    public RecordData withLastName(String lastName) {
        return new RecordData(this.firstName, lastName);
    }
}
