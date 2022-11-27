package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.DisplayInvoicesInTable;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.ItemsTableModel;
import view.AddInvoiceDialog;
import view.AddItemDialog;
import view.SVGFrame1;
public class Controller implements ActionListener,ListSelectionListener{
     private SVGFrame1 sVGFrame1;
     private AddInvoiceDialog addInvoiceDialog;
     AddItemDialog addItemDialog;
    public Controller(SVGFrame1 sVGFrame1) {
        this.sVGFrame1=sVGFrame1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand=e.getActionCommand();
        switch(actionCommand){
            case "Load File":
                loadFile();
                break;
            case "Save File":
               saveFile();
                break;
            case "Create New Invoice":
                CreateNewInvoice();
                break;
            case "Delete Invoice":
                deletInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                System.out.println("Item has been deleted");
                break;
                
            case "createInvoiceOkBtn":
                createInvoiceOkBtn();
                break;
            case "createInvoiceCancelBtn":
                createInvoiceCancelBtn();
                break;
            case "createItem":
                createItemOk();
                break;
            case "cancelItem":
                cancelItem();
                break;
        }
     }   

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectRow=sVGFrame1.getInvoiceHeaders().getSelectedRow();
        if(selectRow!=-1){
        System.out.println("you have selected row "+selectRow);
        InvoiceHeader invoiceHeader=sVGFrame1.getInvoices().get(selectRow);
        sVGFrame1.getjLabel1().setText(""+invoiceHeader.getInvoiceNumber());
        sVGFrame1.getjLabel5().setText(invoiceHeader.getInvoiceDate());
        sVGFrame1.getjLabel6().setText(invoiceHeader.getCustomerName());
        sVGFrame1.getjLabel7().setText(""+invoiceHeader.getTheTotalOfTheIvoice());
        ItemsTableModel itemsTableModel=new ItemsTableModel(invoiceHeader.getItems());
        sVGFrame1.getjTable2().setModel(itemsTableModel);
        itemsTableModel.fireTableDataChanged();
        }
    }
    private void loadFile() {
        
         JFileChooser jFileChooser=new JFileChooser();
        
            ArrayList<InvoiceHeader> invoicesHeaders = new ArrayList<>();
        int res = jFileChooser.showOpenDialog(sVGFrame1);
        try{
        if(res==JFileChooser.APPROVE_OPTION){
            File invoiceFile=jFileChooser.getSelectedFile();
            Path invoiceFilePath=Paths.get(invoiceFile.getAbsolutePath());
            
            java.util.List<String> headers = Files.readAllLines(invoiceFilePath);
            for(String header:headers){
                try{
                String []headerpeices=header.split(",");
                int invoiceNumber=Integer.parseInt(headerpeices[0]);
                String invoiceDate=headerpeices[1];
                String customerName=headerpeices[2];
                InvoiceHeader invoiceHeader=new InvoiceHeader(invoiceNumber, invoiceDate, customerName);
                invoicesHeaders.add(invoiceHeader);
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(sVGFrame1, "Invalid file format", "File Format", JOptionPane.ERROR_MESSAGE);
                }
            }   
        }
         }catch(IOException ex){ex.printStackTrace();
       }
    res = jFileChooser.showOpenDialog(sVGFrame1);
    try{
        if(res==JFileChooser.APPROVE_OPTION){
            File lineFile=jFileChooser.getSelectedFile();
            Path lineFilePath=Paths.get(lineFile.getAbsolutePath());            
            java.util.List<String> lines = Files.readAllLines(lineFilePath);
            //ArrayList<InvoiceHeader> invoicesItems = new ArrayList<>();
            for(String line:lines){
                try{
                String []headerpeices=line.split(",");
                int invoiceNumber=Integer.parseInt(headerpeices[0]);
                String itemName=headerpeices[1];
                double itemPrice=Double.parseDouble(headerpeices[2]);
                int itemCount=Integer.parseInt(headerpeices[3]);    
                InvoiceHeader inv=null;
                for(InvoiceHeader inv2:invoicesHeaders){
                    if(inv2.getInvoiceNumber()==invoiceNumber){
                        inv=inv2;
                        break;
                    }
                }
                InvoiceLine invoiceItem=new InvoiceLine( itemName, itemPrice, itemCount, inv);
                inv.getItems().add(invoiceItem);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(sVGFrame1, "Invalid file format", "File Format", JOptionPane.ERROR_MESSAGE);
                }
            }
            sVGFrame1.setInvoices(invoicesHeaders);
            DisplayInvoicesInTable displayInvoicesInTable=new DisplayInvoicesInTable(invoicesHeaders);
            sVGFrame1.setDisplayInvoicesInTable(displayInvoicesInTable);
            sVGFrame1.getInvoiceHeaders().setModel(displayInvoicesInTable);
            sVGFrame1.getInvoicesTableModel().fireTableDataChanged();       
        } 
        }catch(IOException ex){ex.printStackTrace();}
    }

    private void saveFile() {
        ArrayList<InvoiceHeader>invoiceHeaders=sVGFrame1.getInvoices();
        String invoices = "";
        String items = "";
        for (InvoiceHeader invoiceHeader : invoiceHeaders) {
            String invCSV = invoiceHeader.getDataAsCSV();
            invoices += invCSV;
            invoices += "\n";

            for (InvoiceLine invoiceLine : invoiceHeader.getItems()) {
                String lineCSV = invoiceLine.getAsCSV();
                items += lineCSV;
                items += "\n";
            }
        }
        
        
        System.out.println("Check point");
        
        
        try {
            JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showSaveDialog(sVGFrame1);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = jFileChooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(headerFile);
                fileWriter.write(invoices);
                fileWriter.flush();
                fileWriter.close();
                result = jFileChooser.showSaveDialog(sVGFrame1);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = jFileChooser.getSelectedFile();
                    FileWriter fileWriter1 = new FileWriter(lineFile);
                    fileWriter1.write(items);
                    fileWriter1.flush();
                    fileWriter1.close();
                }
            }
        } catch (Exception ex) {

        }
    }

    private void deletInvoice() {
    int selectedInvoice=sVGFrame1.getInvoiceHeaders().getSelectedRow();
    if(selectedInvoice!=-1){
         sVGFrame1.getInvoices().remove(selectedInvoice);
         sVGFrame1.getInvoicesTableModel().fireTableDataChanged();
    }
    }

    private void deleteItem() {
    System.out.println("Item has been deleted from method");
     int selectedInvoice = sVGFrame1.getjTable2().getSelectedRow();

        if (selectedInvoice != -1) {
            ItemsTableModel itemsTableModel = (ItemsTableModel) sVGFrame1.getjTable2().getModel();
            itemsTableModel.getLines().remove(selectedInvoice);
            itemsTableModel.fireTableDataChanged();
            sVGFrame1.getInvoicesTableModel().fireTableDataChanged();
        }
    
    }
    private void CreateNewInvoice() {
        addInvoiceDialog = new AddInvoiceDialog(sVGFrame1);
        addInvoiceDialog.setVisible(true);

    }
    private void createInvoiceOkBtn() {
        
       String invoiceDate=addInvoiceDialog.getInvoiceDateInputField().getText();
       String invoiceCustomerName=addInvoiceDialog.getcustomerNameInpuField().getText();
       int invoiceNumber = sVGFrame1.getTheNextInvoiceNumber();
       try {
            String[] dateParts = invoiceDate.split("-");  
            if (dateParts.length < 3) {
                JOptionPane.showMessageDialog(sVGFrame1, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(sVGFrame1, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
       
       InvoiceHeader invoiceHeader=new InvoiceHeader(invoiceNumber, invoiceDate, invoiceCustomerName);
       sVGFrame1.getInvoices().add(invoiceHeader);
       sVGFrame1.getInvoicesTableModel().fireTableDataChanged(); 
       addInvoiceDialog.setVisible(false);
       addInvoiceDialog.dispose();
       addInvoiceDialog=null;
                }
            }
       } catch (Exception ex) {
            JOptionPane.showMessageDialog(sVGFrame1, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
                
    }

    private void createInvoiceCancelBtn() {
        addInvoiceDialog.setVisible(false);
        addInvoiceDialog.dispose();
        addInvoiceDialog=null;
      
    }
   private void createNewItem(){
       addItemDialog=new AddItemDialog(sVGFrame1);
       addItemDialog.setVisible(true);
    }
    
    private void createItemOk() {
        String itemName = addItemDialog.getItemNameInputField().getText();
        double itemPrice = Double.parseDouble(addItemDialog.getItemPriceInputField().getText());
        int  itemCount= Integer.parseInt(addItemDialog.getItemCountInputField().getText());
        
        int selectedInvoiceIndex = sVGFrame1.getInvoiceHeaders().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            InvoiceHeader invoice = sVGFrame1.getInvoices().get(selectedInvoiceIndex);
            InvoiceLine item = new InvoiceLine(itemName, itemPrice, itemCount, invoice);
            invoice.getItems().add(item);
            ItemsTableModel itemsTableModel = (ItemsTableModel) sVGFrame1.getjTable2().getModel();
            //linesTableModel.getLines().add(line);
            itemsTableModel.fireTableDataChanged();
            sVGFrame1.getInvoicesTableModel().fireTableDataChanged();
        }
        addItemDialog.setVisible(false);
        addItemDialog.dispose();
        addItemDialog = null;
        
    }

    private void cancelItem() {
        addItemDialog.setVisible(false);
        addItemDialog.dispose();
        addItemDialog = null;
    }

    

}
