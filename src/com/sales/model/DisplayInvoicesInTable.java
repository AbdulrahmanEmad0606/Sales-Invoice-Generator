/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class DisplayInvoicesInTable extends AbstractTableModel{
    private ArrayList<InvoiceHeader> displayedInvoices;
    public DisplayInvoicesInTable(ArrayList<InvoiceHeader> displayedInvoices) {
        this.displayedInvoices = displayedInvoices;
    }
    

    @Override
    public int getRowCount() {
       return displayedInvoices.size();
    }

    @Override
    public int getColumnCount() {
    return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "NO.";
            case 1:
                return "Date";
            case 2:
                return "Customer";
            case 3:
                return "Total";
            default:
                return "Invalid column";
                
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader=displayedInvoices.get(rowIndex);
        switch(columnIndex){
            case 0:
                return invoiceHeader.getInvoiceNumber();
            case 1:
                return invoiceHeader.getInvoiceDate();
            case 2:
                return invoiceHeader.getCustomerName();
            case 3:
                return invoiceHeader.getTheTotalOfTheIvoice();
            default:
                return "Invalid column index";
        }
              
        }
}
