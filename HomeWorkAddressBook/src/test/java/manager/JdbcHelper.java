package manager;

import model.GroupData;

import java.sql.*;
import java.util.ArrayList;

public class JdbcHelper extends HelperBase {


    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }


    public ArrayList<GroupData> getGroupList() {
        ArrayList<GroupData> groups = new ArrayList<GroupData>();
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
        ) {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;


    }

    public void checkConsistency() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             Statement statment = connection.createStatement();
             ResultSet result = statment.executeQuery(
                     "SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL");)
        {
            if (result.next()) {
                throw new IllegalStateException("DB is corrapted!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
