package com.taxassist;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.demo.FullDemo;
import com.taxassist.driver.HibernateUtil;
import com.taxassist.entity.*;

import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserApplication extends JFrame{
    
	private static final long serialVersionUID = 9155634746945526309L;
	private JTable table;
    private JButton btnAdd;
    private DefaultTableModel tableModel;
    private JTextField invoiceNumberTxtFld;
    private JTextField orderIdTxtFld;
    private DateTimePicker invoiceDateTimePicker;
    private DateTimePicker orderDateTimePicker;
    private JTextArea shippingAddressTxtAreaFld;
    private JTextArea billingAddressTxtAreaFld;
    final DefaultComboBoxModel<String>  filingStatus = new DefaultComboBoxModel<String>();
    final JComboBox filingStatusCboBox  = new JComboBox(filingStatus);
    final DefaultComboBoxModel<String>  marketPlace = new DefaultComboBoxModel<String>();
    final JComboBox marketPlaceCboBox  = new JComboBox(marketPlace);
    
    InvoiceSequence invoiceSequence;
    
    private UserApplication() {
    	initialize();
    }

    private void initialize() {
		// TODO Auto-generated method stub
    	filingStatus.addElement("YET_TO_FILE");
		filingStatus.addElement("FILED");
		filingStatus.addElement("CANCELLED");
		
		marketPlace.addElement("FLIPKART");
		marketPlace.addElement("SNAP DEAL");
		
    	createGUI();
        setDefaultValues();
        loadTableData();
	}

	private void loadTableData() {
		// TODO Auto-generated method stub
    	tableModel = new DefaultTableModel(new Object[]{"Invoice Number","Invoice Date","Order Id","Order Date","Shipping Address"},0);
        
        table.setModel(tableModel);
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Bill");
		
		List<Bill> list = query.list();
		
		for(Bill bill:list){
			String invoiceNumber = bill.getInvoiceNumber();
			String invoiceDate = bill.getInvoiceDate();
			String orderId = bill.getOrderId();
			String orderDate = bill.getOrderDate();
			String shippingAddress = bill.getShippingAddress();
			
            tableModel.addRow(new Object[]{invoiceNumber, invoiceDate,orderId,orderDate,shippingAddress});
		}
		
		session.getTransaction().commit();
	}

	private void setDefaultValues() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from InvoiceSequence");
		
		invoiceSequence = (InvoiceSequence)query.list().get(0);
		
		session.getTransaction().commit();
		
    	invoiceNumberTxtFld.setText(invoiceSequence.getInvoiceSeqPrefix() + (invoiceSequence.getLastUsedNumber()+1));
    	
    	URL dateImageURL = FullDemo.class.getResource("/images/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
        // Create the date picker, and apply the image icon.
        
        JButton datePickerButton = invoiceDateTimePicker.getDatePicker().getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);
        invoiceDateTimePicker.datePicker.setDateToToday();
        
        URL timeIconURL = FullDemo.class.getResource("/images/timepickerbutton1.png");
        Image timeExampleImage = Toolkit.getDefaultToolkit().getImage(timeIconURL);
        ImageIcon timeExampleIcon = new ImageIcon(timeExampleImage);
        // Create the time picker, and apply the image icon.
        
        JButton timePickerButton = invoiceDateTimePicker.getTimePicker().getComponentToggleTimeMenuButton();
        timePickerButton.setText("");
        timePickerButton.setIcon(timeExampleIcon);
    	
    	invoiceDateTimePicker.timePicker.setTimeToNow();
		
    	orderIdTxtFld.setText("");
    	
    	datePickerButton = orderDateTimePicker.getDatePicker().getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);
    	
        orderDateTimePicker.datePicker.setDateToToday();
        
        timePickerButton = orderDateTimePicker.getTimePicker().getComponentToggleTimeMenuButton();
        timePickerButton.setText("");
        timePickerButton.setIcon(timeExampleIcon);
        
    	orderDateTimePicker.timePicker.setTimeToNow();
    	
		shippingAddressTxtAreaFld.setText("");
		billingAddressTxtAreaFld.setText("");
		
		filingStatusCboBox.setSelectedIndex(0);
		marketPlaceCboBox.setSelectedIndex(0);
	}

	private void createGUI() {
        setLayout(new BorderLayout());
        
        JScrollPane pane = new JScrollPane();
        
        table = new JTable();
        
        pane.setViewportView(table);
        
        JPanel eastPanel = new JPanel();
        
        btnAdd = new JButton("Add");
        eastPanel.add(btnAdd);
        
        JPanel northPanel = new JPanel();        
        northPanel.setLayout(new GridLayout(0,4));
        
        JLabel invoiceNumberLbl = new JLabel("Invoice Number ",SwingConstants.RIGHT);
        invoiceNumberTxtFld = new JTextField(30);
        
        JLabel orderIdLbl = new JLabel("Order Id ",SwingConstants.RIGHT);
        orderIdTxtFld = new JTextField(30);        
        
        JLabel invoiceDateLbl = new JLabel("Invoice Date ",SwingConstants.RIGHT);
        invoiceDateTimePicker = new DateTimePicker();
        
        JLabel orderDateLbl = new JLabel("Order Date ",SwingConstants.RIGHT);
        orderDateTimePicker = new DateTimePicker();
        
        JLabel shippingAddressLbl = new JLabel("Shipping Address ",SwingConstants.RIGHT);
        shippingAddressTxtAreaFld = new JTextArea(4,20);
        
        JLabel billingAddressLbl = new JLabel("Billing Address ",SwingConstants.RIGHT);
        billingAddressTxtAreaFld = new JTextArea(4,20);
        
        JLabel filingStatusLbl = new JLabel("Filing Status ",SwingConstants.RIGHT);

        JLabel marketPlaceLbl = new JLabel("Market Place ",SwingConstants.RIGHT);
        
        
        northPanel.add(invoiceNumberLbl);
        northPanel.add(invoiceNumberTxtFld);

        northPanel.add(orderIdLbl);
        northPanel.add(orderIdTxtFld);
        
        northPanel.add(invoiceDateLbl);
        northPanel.add(invoiceDateTimePicker);
        
        northPanel.add(orderDateLbl);
        northPanel.add(orderDateTimePicker);
        
        northPanel.add(filingStatusLbl);
        northPanel.add(filingStatusCboBox);
        
        northPanel.add(marketPlaceLbl);
        northPanel.add(marketPlaceCboBox);
        
        JPanel northPanelV2=new JPanel(new GridLayout(0,4));
        northPanelV2.add(shippingAddressLbl);
        northPanelV2.add(shippingAddressTxtAreaFld);
        
        northPanelV2.add(billingAddressLbl);
        northPanelV2.add(billingAddressTxtAreaFld);

        JPanel outer = new JPanel(new BorderLayout());
        outer.add(northPanel,BorderLayout.NORTH);
        outer.add(northPanelV2,BorderLayout.CENTER);
        
        add(outer, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(pane,BorderLayout.CENTER);
        
        btnAdd.addActionListener(new ActionListener(){
            @Override        
            public void actionPerformed(ActionEvent e) {
            	try{
	            	Session session = null;
	            	Bill bill;
	            	String currentTime;
	            	String invoiceTime;
	            	String orderTime;
	            	
	            	invoiceTime=invoiceDateTimePicker.datePicker + " " + invoiceDateTimePicker.timePicker;
	    			orderTime=orderDateTimePicker.datePicker + " " + orderDateTimePicker.timePicker;
	    			
	                int count = tableModel.getRowCount()+1;
	                tableModel.addRow(new Object[]{invoiceNumberTxtFld.getText(),invoiceTime,orderIdTxtFld.getText(),orderTime,shippingAddressTxtAreaFld.getText()});
	                
	    			session = HibernateUtil.getSessionFactory().openSession();
	
	    			session.beginTransaction();
	    			
	    			Date now = new Date();
	    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	    			currentTime = sdf.format(now);
	    			
	    			bill = new Bill();
	    			bill.setCreatedTime(currentTime);
	    			bill.setUpdatedTime(currentTime);
	    			
	    			bill.setOrderId(orderIdTxtFld.getText());
	    			bill.setOrderDate(orderTime);
	    			
	    			bill.setInvoiceNumber(invoiceNumberTxtFld.getText());
	    			bill.setInvoiceDate(invoiceTime);
	    			
	    			bill.setShippingAddress(shippingAddressTxtAreaFld.getText());
	    			bill.setBillingAddress(billingAddressTxtAreaFld.getText());
	    			
	    			bill.setIsReturn('N');
	    			
	    			bill.setBillStatus((String)filingStatusCboBox.getSelectedItem());
	    			
	    			bill.setActive('Y');
	    			
	    			bill.setItemCount(1);
	    			bill.setShippingCost(45.0);
	    			bill.setTotalAmount(399.5);
	    			
	    			bill.setMarketPlace((String)marketPlaceCboBox.getSelectedItem());
	    			
	    			session.save(bill);
	    			
	    			invoiceSequence.setLastUsedNumber(invoiceSequence.getLastUsedNumber()+1);
	    			session.update(invoiceSequence);
	    			
	    			session.getTransaction().commit();
	    	        JOptionPane.showMessageDialog(null, "Record Added", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
	    	        setDefaultValues();
	            }catch(Exception exception){
	            	exception.printStackTrace();
	            }
            }
        });
    }
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        	
            @Override
            public void run() {
                UserApplication u1 = new UserApplication();
                u1.setTitle("Mythili Apparels - Billing System");
                u1.setLocationByPlatform(true);
                u1.pack();
                u1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                u1.setVisible(true);
            }
        });
    }
} 