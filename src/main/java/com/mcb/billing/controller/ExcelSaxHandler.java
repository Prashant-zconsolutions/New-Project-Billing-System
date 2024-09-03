package com.mcb.billing.controller;


import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class ExcelSaxHandler extends SheetHandler{
    @Override
    protected boolean processSheet(String sheetName) {
        //Decide which sheets to read; Return true for all sheets
        //return "Sheet 1".equals(sheetName);
        System.out.println("Processing start for sheet : " + sheetName);
        return true;
    }

    @Override
    protected void startSheet() {
        //Any custom logic when a new sheet starts
        System.out.println("Sheet starts");
    }

    @Override
    protected void endSheet() {
        //Any custom logic when sheet ends
        System.out.println("Sheet ends");
    }

    @Override
    protected void processRow() {
        if(rowNumber == 1 && !header.isEmpty()) {
            System.out.println("The header values are at line no. " + rowNumber + " " +
                    "are :" + header);

        }
        else if (rowNumber > 1 && !rowValues.isEmpty()) {

            //Get specific values here
      /*String a = rowValues.get("A");
      String b = rowValues.get("B");*/


            // convert numeric date into Date using cellId
            String date = rowValues.get("B");
            System.out.println(processCellValue(date));

            //Print whole row
            System.out.println("The row values are at line no. " + rowNumber + " are :" + rowValues);
        }
    }


    private String processCellValue(String value) {
        try {
            double numericValue = Double.parseDouble(value);
            String dateString = convertExcelDateToDateString(numericValue);
            return dateString;
        } catch (NumberFormatException e) {
            System.out.println("Cell value: " + value);
        }
        return null;
    }

    private String convertExcelDateToDateString(double serialDate) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(1899, java.util.Calendar.DECEMBER, 30); // Base date for Excel 1900 system
        calendar.add(java.util.Calendar.DATE, (int) serialDate);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


}