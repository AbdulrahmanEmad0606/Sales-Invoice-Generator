package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddInvoiceDialog extends JDialog{
  private JTextField customerNameInpuField;
    private JTextField invoiceDateInputField;
    private JLabel customerNameLabel;
    private JLabel invDateLabel;
    private JButton okButton;
    private JButton cancelbutton;

    public AddInvoiceDialog(SVGFrame1 sVGFrame1) {
        customerNameLabel = new JLabel("  Customer Name:");
        customerNameInpuField = new JTextField(20);
        invDateLabel = new JLabel("  Invoice Date:");
        invoiceDateInputField = new JTextField(20);
        okButton = new JButton("OK");
        cancelbutton = new JButton("Cancel");
        
        okButton.setActionCommand("createInvoiceOkBtn");
        cancelbutton.setActionCommand("createInvoiceCancelBtn");
        
        okButton.addActionListener(sVGFrame1.getController());
        cancelbutton.addActionListener(sVGFrame1.getController());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLabel);
        add(invoiceDateInputField);
        add(customerNameLabel);
        add(customerNameInpuField);
        add(okButton);
        add(cancelbutton);
        
        pack();
        
    }

    public JTextField getcustomerNameInpuField() {
        return customerNameInpuField;
    }

    public JTextField getInvoiceDateInputField() {
        return invoiceDateInputField;
    }   
}
