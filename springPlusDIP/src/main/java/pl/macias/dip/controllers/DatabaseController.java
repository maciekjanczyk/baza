package pl.macias.dip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.macias.dip.utils.DatabaseConnection.connectToBase;

/**
 * Created by macias on 24.02.15.
 */

@Controller
public class DatabaseController {

    @RequestMapping("/users")
    public String listUsers (ModelMap model) throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection connection = connectToBase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Users;");
        while (resultSet.next()) {
            String name = null, pass = null;
            name = resultSet.getString(1);
            pass = resultSet.getString(2);
            list.add(name);
        }
        connection.close();
        model.addAttribute("user_list", list);

        return "users";
    }

    @RequestMapping("/register")
    public String registerUserForm () {
        return "register";
    }

    @RequestMapping("/add_user")
    public String registerNewUser (ModelMap model, HttpServletRequest request) throws SQLException {
        String name = null, pass = null;
        name = request.getParameter("username");
        pass = request.getParameter("pass");

        if (name == null || pass == null || name.equals("") || pass.equals("")) {
            model.addAttribute("komunikat", "Empty form inputs.");
            return "error";
        }

        Connection connection = connectToBase();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from Users;");
        while (resultSet.next()) {
            String name_tmp = null, pass_tmp = null;
            name_tmp = resultSet.getString(1);
            pass_tmp = resultSet.getString(2);
            if (name_tmp.equals(name)) {
                connection.close();
                model.addAttribute("komunikat", "This player exists in database.");
                return "error";
            }
        }

        statement.executeUpdate("insert into Users VALUES (\'"+name+"\',\'"+pass+"\');");
        connection.close();

        return "hello";
    }

}
