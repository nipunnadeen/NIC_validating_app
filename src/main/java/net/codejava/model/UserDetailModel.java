package net.codejava.model;

import net.codejava.database.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserDetailModel {
    private String NICNumber;
    private int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private boolean isNewFormat = false;

    public int sendUserDetails(String fName, String address, String nationality, String nicNumber)
            throws ClassNotFoundException {
        DbConnection dbConnection = new DbConnection();
        String insertUserData = "INSERT INTO users" +
                "  (fullname, address, nationality, nicnumber) VALUES " +
                " (?, ?, ?, ?);";
        try {
            if (getUserData(nicNumber).size() == 0) {
                PreparedStatement preparedStatement = dbConnection.createConnection().
                        prepareStatement(insertUserData);

                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, nationality);
                preparedStatement.setString(4, nicNumber);

                return preparedStatement.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateUserDetails(int id, String fName, String address, String nationality,
                                 String nicNumber) throws ClassNotFoundException {
        DbConnection dbConnection = new DbConnection();
        PreparedStatement preparedStatement;
        try {
            if (getUserData(nicNumber).size() == 0) {
                String insertUserData = "update users set fullname=?, address=?, nationality=?, nicnumber=?" +
                        "where id=?";
                preparedStatement = dbConnection.createConnection().
                        prepareStatement(insertUserData);
                preparedStatement.setString(4, nicNumber);
                preparedStatement.setInt(5, id);
            } else {
                String insertUserData = "update users set fullname=?, address=?, nationality=?" +
                        "where id=?";
                preparedStatement = dbConnection.createConnection().
                        prepareStatement(insertUserData);
                preparedStatement.setInt(4, id);
            }
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, nationality);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<String> getUserData(String NIC) throws ClassNotFoundException {
        DbConnection dbConnection = new DbConnection();
        String getUserData = "SELECT * FROM users WHERE nicnumber = '" + NIC + "'";
        ArrayList<String> results = new ArrayList<String>();
        try {
            PreparedStatement preparedStatement = dbConnection.createConnection().
                    prepareStatement(getUserData);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                results.add(String.valueOf(rs.getInt("id")));
                results.add(rs.getString("fullname"));
                results.add(rs.getString("address"));
                results.add(rs.getString("nationality"));
                results.add(rs.getString("nicnumber"));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return results;
        }
    }

    public boolean validateNIC(String NIC) {
        return (NIC.length() == 12 || NIC.length() == 10) &&
                NIC.matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
    }

    public ArrayList<String> getNICDetails(String NIC) {

        if (NIC.contains("v") || NIC.contains("x")) {
            NICNumber = NIC.substring(0, NIC.length() - 1);
        } else {
            NICNumber = NIC;
        }
        if (NIC.length() == 12) {
            isNewFormat = true;
        }

        String birthday = getBirthDay();
        String gender = getSex();
        String age = String.valueOf(getAge());

        ArrayList<String> userData = new ArrayList<String>();
        userData.add(birthday);
        userData.add(gender);
        userData.add(age);

        return userData;
    }

    private int getAge() {
        int bornYear = getYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - bornYear;
    }

    private String getBirthDay() {
        int year = getYear();
        String monthAndDate = getMonthAndDate();

        return year + monthAndDate;
    }

    private int getYear() {
        if (isNewFormat) {
            return (Integer.parseInt(NICNumber.substring(0, 4)));
        }
        return (1900 + Integer.parseInt(NICNumber.substring(0, 2)));
    }

    private int getDays() {
        int d;
        if (isNewFormat) {
            d = Integer.parseInt(NICNumber.substring(4, 7));
        } else {
            d = Integer.parseInt(NICNumber.substring(2, 5));
        }
        if (d > 500) {
            return (d - 500);
        } else {
            return d;
        }
    }

    private String getMonthAndDate() {
        int mo = 0, da = 0;
        int days = getDays();

        for (int i = 0; i < month.length; i++) {
            if (days < month[i]) {
                mo = i + 1;
                da = days;
                break;
            } else {
                days = days - month[i];
            }
        }
        return ("/" + mo + "/" + da);

    }

    private String getSex() {
        String M = "Male", F = "Female";
        int d;
        if (isNewFormat) {
            d = Integer.parseInt(NICNumber.substring(4, 7));
        } else {
            d = Integer.parseInt(NICNumber.substring(2, 5));
        }
        if (d > 500) {
            return F;
        } else {
            return M;
        }
    }

}
