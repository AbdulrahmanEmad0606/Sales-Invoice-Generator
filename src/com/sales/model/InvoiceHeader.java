package model;
import java.util.ArrayList;

public class InvoiceHeader {
    private int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> items;
  public InvoiceHeader(int invoiceNumber, String invoiceDate, String customerName){
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
  
    }
    public InvoiceHeader(int invoiceNumber, String invoiceDate, String customerName,ArrayList<InvoiceLine> items) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.items=items;
  
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", customerName=" + customerName + '}';
    }

    public ArrayList<InvoiceLine> getItems() {
        if(items==null){
            items=new ArrayList<>();
        }
        return items;
    }  
    public double getTheTotalOfTheIvoice(){
        double invoiceTotal=0.0;
        for(InvoiceLine invoiceLine:getItems()){
            invoiceTotal+=invoiceLine.getItemTotal();
    }
    return invoiceTotal;
    }
    
    public String getDataAsCSV() {
        
        return invoiceNumber + "," + invoiceDate + "," + customerName;
    }
    
}

