package MyPanels;
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
    
import javax.swing.*;  
import javax.swing.event.*;  
public class MyComboBox_TextFieldPane extends JTextField{
	
	private ArrayList<String> items;
	public MyComboBox_TextFieldPane(ArrayList<String> items,int columns){
		this.items = items;
        setupAutoComplete(this, this.items);  
        this.setColumns(columns); 
	}
	
     private static boolean isAdjusting(JComboBox<String> cbInput) {  
         if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {  
             return (Boolean) cbInput.getClientProperty("is_adjusting");  
         }  
         return false;  
     }  
  
     private static void setAdjusting(JComboBox<String> cbInput, boolean adjusting) {  
         cbInput.putClientProperty("is_adjusting", adjusting);  
     }  
  
     private  void setupAutoComplete(final JTextField txtInput, final ArrayList<String> items) {  
    	 final DefaultComboBoxModel<String>   model = new DefaultComboBoxModel<>();  
         final JComboBox<String> cbInput = new JComboBox<String>(model) {  
            public Dimension getPreferredSize() {  
                return new Dimension(super.getPreferredSize().width , 0); 
             }  
         };  
         setAdjusting(cbInput, false); 
         for (String item : items) {  
             model.addElement(item);  
         }  
         cbInput.setSelectedItem(null);  
         cbInput.addActionListener(new ActionListener() {  
             @Override  
             public void actionPerformed(ActionEvent e) {  
                 if (!isAdjusting(cbInput)) {  
                     if (cbInput.getSelectedItem() != null) {  
                         txtInput.setText(cbInput.getSelectedItem().toString());  
                     }  
                 }  
             }  
         });  
  
         txtInput.addKeyListener(new KeyAdapter() {  
  
             @Override  
             public void keyPressed(KeyEvent e) {  
                 setAdjusting(cbInput, true);  
                 if (e.getKeyCode() == KeyEvent.VK_SPACE) {  
                     if (cbInput.isPopupVisible()) {  
                         e.setKeyCode(KeyEvent.VK_ENTER);  
                     }  
                 }  
                 if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {  
                     e.setSource(cbInput);  
                     cbInput.dispatchEvent(e);  
                     if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                         txtInput.setText(cbInput.getSelectedItem().toString());  
                         cbInput.setPopupVisible(false);  
                     }  
                 }  
                 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  
                     cbInput.setPopupVisible(false);  
                 }  
                 setAdjusting(cbInput, false);  
             }  
         });  
         txtInput.getDocument().addDocumentListener(new DocumentListener() {  
             public void insertUpdate(DocumentEvent e) {  
                 updateList();  
             }  
  
             public void removeUpdate(DocumentEvent e) {  
                 updateList();  
             }  
  
             public void changedUpdate(DocumentEvent e) {  
                 updateList();  
             }  
  
             private void updateList() {  
                 setAdjusting(cbInput, true);  
                 model.removeAllElements();  
                 String input = txtInput.getText();  
                 if (!input.isEmpty()) {  
                     for (String item : items) {  
                         if (item.toLowerCase().startsWith(input.toLowerCase())) {  
                             model.addElement(item);  
                         }  
                     }  
                 }  
                 cbInput.setPopupVisible(model.getSize() > 0);  
                 setAdjusting(cbInput, false);  
             }  
         });  
         txtInput.setLayout(new BorderLayout());  
         txtInput.add(cbInput, BorderLayout.SOUTH);  
      
    }




}

