package com.ijse.gdse72.dao;


import com.ijse.gdse72.model.Complaint;
import com.ijse.gdse72.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public String generateId(){
        String sql = "SELECT c_id FROM complaints ORDER BY c_id DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String lastId = rs.getString("c_id");
                int num = Integer.parseInt(lastId.substring(1));
                num++;
                return String.format("%s%03d", "C", num);
            } else {

                return "C001";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error generating user ID", e);
        }
    }

    public List<Complaint> getComplaints(){

        List<Complaint> complaints = new ArrayList<>();
        String sql =
                "SELECT c.c_id, u.username, c.title, c.description, " +
                        "c.status, c.created_at, c.updated_at, c.remarks " +
                        "FROM complaints c " +
                        "JOIN users u ON c.u_id = u.u_id " +
                        "ORDER BY c.created_at DESC";

        try (Connection connection = DBConnection.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();

                complaint.setC_id(rs.getString("c_id"));
                complaint.setU_id(rs.getString("username"));

                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setCreated_at(rs.getTimestamp("created_at"));
                complaint.setUpdated_at(rs.getTimestamp("updated_at"));
                complaint.setRemarks(rs.getString("remarks"));

                complaints.add(complaint);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complaints;
    }
    public List<Complaint> getComplaintsByUser(String uId) {

        List<Complaint> complaints = new ArrayList<>();

        String sql = "SELECT * FROM complaints WHERE u_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, uId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Complaint c = new Complaint();
                    c.setC_id(rs.getString("c_id"));
                    c.setU_id(rs.getString("u_id"));
                    c.setTitle(rs.getString("title"));
                    c.setDescription(rs.getString("description"));
                    c.setStatus(rs.getString("status"));
                    c.setCreated_at(rs.getTimestamp("created_at"));
                    c.setUpdated_at(rs.getTimestamp("updated_at"));
                    c.setRemarks(rs.getString("remarks"));
                    complaints.add(c);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching complaints for user: " + uId, e);
        }
        return complaints;
    }
    public Complaint findComplaintById(String cId) {

        String sql = "SELECT * FROM complaints WHERE c_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Complaint c = new Complaint();

                    c.setC_id(rs.getString("c_id"));
                    c.setU_id(rs.getString("u_id"));
                    c.setTitle(rs.getString("title"));
                    c.setDescription(rs.getString("description"));
                    c.setStatus(rs.getString("status"));
                    c.setCreated_at(rs.getTimestamp("created_at"));
                    c.setUpdated_at(rs.getTimestamp("updated_at"));
                    c.setRemarks(rs.getString("remarks"));
                    return c;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding complaint by ID: " + cId, e);
        }

        return null;
    }

    public boolean updateComplaintByAdmin(Complaint c) {

        String sql = "UPDATE complaints SET status = ? ,remarks = ?, updated_at = ? WHERE c_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, c.getStatus());
            ps.setString(2, c.getRemarks());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, c.getC_id());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating complaint by admin", e);
        }
    }
    public boolean updateComplaintByEmployee(Complaint c) {

        String sql = "UPDATE complaints SET title = ?, description = ?, updated_at = ? WHERE c_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, c.getTitle());
            ps.setString(2, c.getDescription());
            ps.setNull(3, java.sql.Types.TIMESTAMP);
            ps.setString(4, c.getC_id());

            return ps.executeUpdate() > 0 ;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating complaint: " + c.getC_id(), e);
        }
    }

    public boolean deleteComplaint(String complaintId) {

        String sql = "DELETE FROM complaints WHERE c_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, complaintId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Complaint", e);
        }
    }

    public boolean saveComplaint(Complaint c) {

        String sql = "INSERT INTO complaints "
                + "(c_id, u_id, title, description, status, remarks, updated_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, c.getC_id());
            ps.setString(2, c.getU_id());
            ps.setString(3, c.getTitle());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getStatus());
            ps.setString(6, c.getRemarks());
            ps.setNull(7, java.sql.Types.TIMESTAMP);

            return ps.executeUpdate() > 0 ;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM complaints";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM complaints WHERE status = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countAllByUser(String uId) {
        String sql = "SELECT COUNT(*) FROM complaints WHERE u_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countByStatusAndUser(String status, String uId) {
        String sql = "SELECT COUNT(*) FROM complaints WHERE status = ? AND u_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, uId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
