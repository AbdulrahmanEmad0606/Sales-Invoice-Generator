package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddItemDialog extends JDialog{
    private JTextField itemNameInputField;
    private JTextField itemCountInputField;
    private JTextField itemPriceInputField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemPriceLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    public AddItemDialog(SVGFrame1 sVGFrame1) {
        itemNameInputField = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemCountInputField = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemPriceInputField = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price");
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createItem");
        cancelButton.setActionCommand("cancelItem");
        
        okButton.addActionListener(sVGFrame1.getController());
        cancelButton.addActionListener(sVGFrame1.getController());
        setLayout(new GridLayout(4, 2));
        add(itemNameLabel);
        add(itemNameInputField);
        add(itemCountLabel);
        add(itemCountInputField);
        add(itemPriceLabel);
        add(itemPriceInputField);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getItemNameInputField() {
        return itemNameInputField;
    }

    public JTextField getItemCountInputField() {
        return itemCountInputField;
    }

    public JTextField getItemPriceInputField() {
        return itemPriceInputField;
    }
    
}
