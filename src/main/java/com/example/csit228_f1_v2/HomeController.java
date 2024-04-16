package com.example.csit228_f1_v2;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeController {

    public ToggleButton tbNight;
    public ProgressIndicator piProgress;
    public Slider slSlider;
    public ProgressBar pbProgress;
    public TextArea txtArea;
    public Text txtReal;

    public void onClickTextDisplay() {
        StringBuilder sb = new StringBuilder();
        try (
                Connection c = MySQLConnection.getConnection();
                Statement statement = c.createStatement();
        ) {
            String query = "SELECT * FROM appusers";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("id");
                String username = res.getString("username");
                int password = res.getString("password").length();
                String pass = "*".repeat(password);
                sb.append("ID: " + id + "\nUsername: " + username + "\nPassword: " + pass + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtArea.setText(sb.toString());
    }

    public void onSliderChange() {
        double val = slSlider.getValue();
        System.out.println(val);
        piProgress.setProgress(val/100);
        pbProgress.setProgress(val/100);
        if (val == 100) {
            System.exit(0);
        }
    }

    public void onNightModeClick() {
        if (tbNight.isSelected()) {
            tbNight.getParent().setStyle("-fx-background-color: BLACK");
            tbNight.setText("DAY");
        } else {
            tbNight.getParent().setStyle("-fx-background-color: WHITE");
            tbNight.setText("NIGHT");
        }
    }
}
