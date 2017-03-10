package com.tavrida.bve;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static List<Nomenclature> data = null;

    public static List<Nomenclature> getALL(){

        Database.connect();
        data = Database.getAllData();
        Database.close();
        return data;
    }

    public static List<Nomenclature> searchById(String id){
        Database.connect();
        data = Database.getNomenclatureById(id);
        Database.close();
        return data;
    }

    public static List<Nomenclature> searchByPartCode(String partCode){
        Database.connect();
        data = Database.getNomenclatureByPartCode(partCode);
        Database.close();
        return data;
    }

    public static List<Nomenclature> fromExceltoList(String filePath){
        //метод fromExceltoList нужен для вывода содержимого Excel файла в виде коллекции List на экран
        //DataFormatter нужен для принудительного форматирования ячейки QTY в String
        DataFormatter formatter = new DataFormatter();

        List<Nomenclature> data = new ArrayList<>();

        try{
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

            Nomenclature nom = new Nomenclature();
            nom.setProductCode(ProductCode);
            nom.setId(ID);
            nom.setPartCode(PartCode);
            nom.setName(Name);
            nom.setQty(QTY);
            nom.setUnit(Unit);
            nom.setCatalog(Catalog);
            nom.setEcName(ECname);
            nom.setCodeInCatalog(CodeInCatalog);
            nom.setLimitation(Limitation);
            data.add(nom);
        }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<String> getAllProducts(){
        //метод получение всех ProductCode из БД.
        data = getALL();
        if(data.isEmpty()){
            return null;
        }

        List<String> productsList = new ArrayList<>();
        //добавление в список ProductCode значения из первой строки всей БД
        productsList.add(data.get(0).getProductCode());
        String productNameBuffer = data.get(0).getProductCode();


        for(Nomenclature tempNom : data){

            if(!(tempNom.getProductCode().equals(productNameBuffer))){
               //если ProductCode не совпадает с ProductCode в строке ниже - добавление в итоговую коллекцию, иначе пропуск
                productsList.add(tempNom.getProductCode());
               productNameBuffer = tempNom.getProductCode();
            }
        }
        return productsList;
    }
}

