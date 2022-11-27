package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemsTableModel extends AbstractTableModel {
    private ArrayList<InvoiceLine>items;
    private String []itemsHeader={"NO.","Item Name","Item Price","Count","Item Total"};

    public ItemsTableModel(ArrayList<InvoiceLine> items) {
        this.items = items;
    }
    public ArrayList<InvoiceLine> getLines() {
        return items;
    }
    
    
    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return itemsHeader.length;
    }

    @Override
    public String getColumnName(int x) {
        return itemsHeader[x];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine item = items.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return item.getInvoiceHeader().getInvoiceNumber();
            case 1: return item.getItemName();
            case 2: return item.getItemPrice();
            case 3: return item.getItemCount();
            case 4: return item.getItemTotal();
            default : return "Invalid data";
        }
    }
}
