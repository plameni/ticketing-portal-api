package com.ticketing.portal.repositories;

import com.ticketing.portal.DBUtil;
import com.ticketing.portal.models.Project;
import com.ticketing.portal.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

            ps.close();
            conn.close();
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

    public Project getProjectById(int projectId){
        Connection conn = null;
        Project result = null;

        try {
            conn = DBUtil.open();
            String sql = "SELECT * FROM projekat WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            result = new Project(rs.getInt("id"), rs.getString("naziv"), rs.getString("opis"));
            ps.close();
            conn.close();
        }
        catch (Exception ex){
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

        return result;
    }

    public DBOperationResponse addProject(Project pr) {
        Connection conn = null;
        DBOperationResponse result = null;

        try {
            conn = DBUtil.open();
            String sql = "INSERT INTO projekat (naziv, opis) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pr.getName());
            ps.setString(2, pr.getDescription());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Neuspjesno unijet projekat!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()){
                int projectId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, projectId, "Uspjesno kreiran projekat");
            }
            else {
                throw new SQLException("Nije moguce bilo dobiti ID za projekat");
            }

            ps.close();
            conn.close();
        }
        catch (Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
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

    public DBOperationResponse editProject(int projectId, Project p){
        Connection conn = null;
        DBOperationResponse result = null;

        try {
            conn = DBUtil.open();
            String sql = "UPDATE projekat SET naziv = ?, opis = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, projectId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0){
                throw new SQLException("Nije moguce update-ovati projekat");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izmijenjen projekat");
        }
        catch (Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
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

    public DBOperationResponse deleteProject(int projectId){
        Connection conn = null;
        DBOperationResponse result = null;

        try {
            conn = DBUtil.open();
            String sql = "DELETE FROM projekat WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0){
                throw new SQLException("Nije moguce izbrisati projekat");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izbrisan projekat");
        }
        catch (Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
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
