package model;
public class InvoiceLine {
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private InvoiceHeader invoiceHeader;

    public InvoiceLine(String itemName, double itemPrice, int itemCount,InvoiceHeader invoiceHeader) {
      
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.invoiceHeader=invoiceHeader;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public double getItemTotal(){
        return itemCount*itemPrice;
           
    }
    @Override
    public String toString() {
        return "InvoiceLine{" + "invoiceNumber=" + invoiceHeader.getInvoiceNumber() + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount + '}';
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }
    
     public String getAsCSV() {
        return invoiceHeader.getInvoiceNumber()+ "," + itemName + "," + itemPrice + "," + itemCount;
    }
    
}
