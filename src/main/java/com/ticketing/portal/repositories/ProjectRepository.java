package com.ticketing.portal.repositories;

import com.ticketing.portal.DBUtil;
import com.ticketing.portal.models.Project;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

    public List<Project> getProjects(){
        // UZMI KONEKCIJU, IZVRSI UPIT, i VRATI REZULTAT
        Connection conn = null;
        List<Project> result = null;

        try {
            conn = DBUtil.open();
            result = new ArrayList<Project>();

            String sql = "SELECT * FROM projekat";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Napravi projekat (objekat tipa Project). I dodaj ga u listu.
                Project p = new Project(rs.getInt("id"), rs.getString("naziv"), rs.getString("opis"));
                result.add(p);
            }

            return result;
        }
        catch (Exception ex) {
            result = null;
            System.out.println(ex);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }

        return  result;
    }
}
