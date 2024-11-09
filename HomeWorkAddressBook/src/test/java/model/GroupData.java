package model;

public record GroupData(String id, String name, String header, String footer) {
    public GroupData() {
        this("", "", "", "");
    }


    @Override
    public String toString() {
        return "GroupData[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "header=" + header + ", " +
                "footer=" + footer + ']';
    }


    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer);
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header, this.footer);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.id, this.name, header, this.footer);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.name, this.header, footer);
    }



}
