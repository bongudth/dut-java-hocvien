/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hocvien;

/**
 *
 * @author HuynhThiKhanhLinh
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class HocVien {

    /**
     * @param args the command line arguments
     */
    
    private static String empMaHocVien;
    private static String empTenHocVien;
    private static Date empNgaySinh;
    private static String empGioiTinh;
    private static Float empDiemThi;
    
    public static void readData(){
        try(Scanner input = new Scanner(new File("src/hocvien/input.txt"))){
            while(input.hasNextLine()){
                String line;
                line = input.nextLine();
                empMaHocVien = line.substring(0,10);
                empTenHocVien = line.substring(10,60).trim();
                empNgaySinh = Date.valueOf(line.substring(60,70));
                empGioiTinh = line.substring(70,73).trim();
                empDiemThi = Float.parseFloat(line.substring(73,76));
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void writeData(){
        readData();
        
        try (Connection conn = getJDBCConnection();
            PreparedStatement pstat = conn.prepareStatement("insert into HOCVIEN values (?, ?, ?, ?, ?)")){
            
            pstat.setString(1, empMaHocVien);
            pstat.setString(2, empTenHocVien);
            pstat.setDate(3, empNgaySinh);
            pstat.setString(4, empGioiTinh);
            pstat.setFloat(5, empDiemThi);
            
            System.out.println(empMaHocVien + empTenHocVien + empNgaySinh + empGioiTinh + empDiemThi);
            
            pstat.executeUpdate();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static Connection getJDBCConnection() {
        final String url = "jdbc:mysql://localhost:3306/JAVA_CK";
        final String user = "root";
        final String password = "huynhthikhanhlinh";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {}
        
        return null;
    }
    
    public static List<HocVienModel> getAllHocViens() {
        List<HocVienModel> hvs = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = getJDBCConnection();
            Statement stmt = conn.createStatement();
            
            String query = "select * from JAVA_CK.HOCVIEN";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                HocVienModel hv = new HocVienModel(rs.getString("MaHocVien"), 
                        rs.getString("TenHocVien"), rs.getDate("NgaySinh"), 
                        rs.getString("GioiTinh"), rs.getFloat("DiemThi"));
                hvs.add(hv);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            if (conn == null) System.out.println("Ket noi that bai!");
        }
        
        return hvs;
    }
    
    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            conn = getJDBCConnection();
            Statement stmt = conn.createStatement();
            
            writeData();
            
            String sql="select * from HOCVIEN";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String MaHocVien = rs.getString("MaHocVien");
                String TenHocVien = rs.getString("TenHocVien");
                Date NgaySinh = rs.getDate("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                Float DiemThi = rs.getFloat("DiemThi");
                System.out.print("MaHocVien=" + MaHocVien + 
                        " TenHocVien=" + TenHocVien + 
                        " NgaySinh=" + NgaySinh + 
                        " GioiTinh=" + GioiTinh + 
                        " DiemThi=" + DiemThi);
                System.out.println("\n");
            }
            
            rs.close();
            stmt.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    } 
}
