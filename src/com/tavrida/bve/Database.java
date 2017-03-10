package com.tavrida.bve;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

        private static final String URL = "jdbc:mysql://localhost:3306/testposts?useSSL=false";
        private static final String PASS = "root";
        private static final String NAME = "root";
        private static final String GET_ALL = "SELECT * FROM units";
        private static final String GET_BY_ID = "SELECT * FROM units WHERE ID=?";
        private static final String GET_BY_PARTCODE = "SELECT * FROM units WHERE PartCode=?";
        private static final String ADD_TO_DB = "INSERT INTO units VALUES(?,?,?,?,?,?,?,?,?,?)";
        private static final String DELETE_BY_PARTCODE = "DELETE FROM units WHERE ProductCode=?";

        private static Connection con = null;
        private static PreparedStatement ps = null;
        private static ResultSet rs;


        public static void connect() {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver not found");
                e.printStackTrace();
            }

            try {
                con = DriverManager.getConnection(URL, NAME, PASS);
            } catch (SQLException e) {
                System.out.println("Connection failed.");
                e.printStackTrace();
            }
        }

        public static List<Nomenclature> getAllData() {
            try {
                ps = con.prepareStatement(GET_ALL);
                rs = ps.executeQuery();
            } catch (SQLException e) {
                System.out.println("Fail to get all query.");
                e.printStackTrace();
            }
            return rsToList();
        }

        public static List<Nomenclature> getNomenclatureById(String id){
            try {
                ps = con.prepareStatement(GET_BY_ID);
                ps.setString(1,id);
                rs = ps.executeQuery();
            } catch (SQLException e) {
                System.out.println("Fail to get query by ID.");
                e.printStackTrace();
            }
            return rsToList();
        }

        public static void deleteByProductCode(String productCode){
            try {
                ps = con.prepareStatement(DELETE_BY_PARTCODE);
                ps.setString(1,productCode);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static List<Nomenclature> getNomenclatureByPartCode(String partCode){
            try {
                ps = con.prepareStatement(GET_BY_PARTCODE);
                ps.setString(1,partCode);
                rs = ps.executeQuery();
            } catch (SQLException e) {
                System.out.println("Fail to get query by TXT.");
                e.printStackTrace();
            }
            return rsToList();
        }

        public static void addExcelToDB(String filePath){
            //DataFormatter нужен для принудительного форматирования ячейки QTY в String
            DataFormatter formatter = new DataFormatter();
            try {
            con.setAutoCommit(false);
            FileInputStream input   = new FileInputStream(filePath);
            POIFSFileSystem fs      = new POIFSFileSystem(input);
            HSSFWorkbook wb         = new HSSFWorkbook(fs);
            HSSFSheet sheet         = wb.getSheetAt(0);
            HSSFRow row;

            //построчная выборка ячеек Excel файла
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                row = sheet.getRow(i);

                String ProductCode =    row.getCell(0).getStringCellValue();
                String ID =             row.getCell(1).getStringCellValue();
                String PartCode =       row.getCell(2).getStringCellValue();
                String Name =           row.getCell(3).getStringCellValue();
                String QTY =            formatter.formatCellValue(row.getCell(4));
                String Unit =           row.getCell(5).getStringCellValue();
                String Catalog =        row.getCell(6).getStringCellValue();
                String ECname =         row.getCell(7).getStringCellValue();
                String CodeInCatalog =  row.getCell(8).getStringCellValue();
                String Limitation =     row.getCell(9).getStringCellValue();

                ps = con.prepareStatement(ADD_TO_DB);
                ps.setString(1,ProductCode);
                ps.setString(2,ID);
                ps.setString(3,PartCode);
                ps.setString(4,Name);
                ps.setString(5,QTY);
                ps.setString(6,Unit);
                ps.setString(7,Catalog);
                ps.setString(8,ECname);
                ps.setString(9,CodeInCatalog);
                ps.setString(10,Limitation);

                ps.execute();
                System.out.println("Import rows " + i);
            }
            con.commit();
            ps.close();
            con.close();
            input.close();
            System.out.println("Success import excel to mysql table");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

        private static List<Nomenclature> rsToList(){

            //метод rsToList нужен для перевода полученных результатов из БД - ResultSet в коллекцию List.

            List<Nomenclature> data = new ArrayList<Nomenclature>();
            try {
                while (rs.next()) {
                    Nomenclature nom = new Nomenclature();
                    nom.setProductCode(rs.getString("Productcode"));
                    nom.setId(rs.getString("ID"));
                    nom.setPartCode(rs.getString("PartCode"));
                    nom.setName(rs.getString("Name"));
                    nom.setQty(rs.getString("QTY"));
                    nom.setUnit(rs.getString("Unit"));
                    nom.setCatalog(rs.getString("Catalog"));
                    nom.setEcName(rs.getString("ECname"));
                    nom.setCodeInCatalog(rs.getString("CodeInCatalog"));
                    nom.setLimitation(rs.getString("Limitation"));
                    data.add(nom);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }

        public static void close(){
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Fail to close DB.");
                    e.printStackTrace();
                }
            }
        }

    }


