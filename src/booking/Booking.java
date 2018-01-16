package booking;

import java.sql.*;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.text.*;
public class Booking extends javax.swing.JFrame {

    /**
     * Creates new form ProjectclassXII
     */
    public Booking() {


        initComponents();

    }
    public class MainMenu extends javax.swing.JFrame {
        /** Constructor*/
    public MainMenu(){
              }}
    public class SData extends javax.swing.JFrame {
       public SData()
       {EnterNewModel.setVisible(false);
       EnterNewPrices.setVisible(false);
       EnterModelDetails.setVisible(false);
       ShowroomData.setVisible(false);
       }
    }    
    public class BookCancel extends javax.swing.JFrame {
    /**Global Variables*/
    int delaytime;
    int bMonth,dMonth;
    int dDay;
    //Constructor
    public BookCancel()
    {BookingDetails2.setVisible(false);
    EnterBookingDetails.setVisible(false);
    BookingCancellations.setVisible(false);
    String m = "0";
    for(int i = 1;i<=12;i++)
    {if(i>9)
        m = "";
    cbMonthb.addItem(m + i);
    cbMonthd.addItem(m+i);
    }
    cmdContinue.setVisible(false);
    }
    }
    //user-defined method
    private int getmax(int month1,int month2,int d2)
    {if(month1 == month2)
        return d2;
    else if(month1 == 2)
        return 28;
    else if(month1 == 4 || month1 == 6 ||month1 == 9||month1 == 11)
        return 30;
    else
        return 31;
    }
    private int search(int monthb,int monthd,int dayb,int dayd,String Tcode)
    {String avail;
    boolean found = false;
    int mdlno = 0;
    int max = getmax(monthb,monthd,dayd);
    try
    {Class.forName("java.sql.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
            String sql = "SELECT * from Status" + monthb + "where TypeCode = '" + Tcode + "';";
        try (ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next())
            {int i;
            for(i = dayb;i<=max;i++)
            {avail = rs.getString(i+2);
            if(avail.equals("b")||avail.equals("o"))
                break;
            }
            if(i== max + 1)
            {mdlno = rs.getInt(1);
            if(monthb != monthd)
                found = search2(monthb +1 ,monthd,1,dayd,Tcode,mdlno);
            else
                break;
            if(found)
                break;
            }
            }
        }
        }
}catch(ClassNotFoundException | SQLException e){lblReport.setText("Incorrect Entry" + e);
    }
    return mdlno;
    }
    //user defined method
    private boolean search2(int monthb,int monthd,int dayb,int dayd,String Tcode,int MDLNO)
    {String avail ;
    boolean found = false;
    int mdlno = 0;
    int max = getmax(monthb,monthd,dayd);
    try
    {Class.forName("java.sql.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan")) {
            JOptionPane.showMessageDialog(null,"Done");
        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT * from Status" + monthb + "where TypeCode = '" + Tcode + "' And ModelNo = " + mdlno + ";";
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    rs.next();
                    int i;
                    for(i = dayb;i<=max;i++)
                    {avail = rs.getString(i+2);
                    if(avail.equals("b")||avail.equals("o"))
                        break;
                    }
                    if(i== max + 1)
                    {if(monthb != monthd)
                        found = search2(monthb + 1,monthd,1,dayd,Tcode,mdlno);
                    else
                        return true;
                    }   }
        }
        }
}catch(ClassNotFoundException | SQLException | HeadlessException e)
{lblReport.setText("Incorrect Entry");
}
    return found;
    }
    public class Report extends javax.swing.JFrame {
        /**Constructor*/
        public Report() {
        ReportCheck.setVisible(true);
        }}
        //user defined method
public boolean SEARCH(int bookingno){
try
{Class.forName("java.sql.Driver");

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan");
Statement stmt = conn.createStatement();
String sql = "SELECT * from Boooking where BookingNo = " + bookingno + ";";
ResultSet rs = stmt.executeQuery(sql);
if(rs.next())
{return true;
}
else
    return false;
}catch(ClassNotFoundException | SQLException e)
{JOptionPane.showMessageDialog(null,"" + e.getMessage());
return false;
}
}

    public class Status extends javax.swing.JFrame {
        //Global variable
        int month;
        /**Constructor*/
        public Status(){}
public Status(int m)
{month = m;
int SNo = 1;
//lblDate.setText(month + "/2012");
try
{Class.forName("java.sql.Driver");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "SELECT * from Status;" + m;
    try (ResultSet rs = stmt.executeQuery(sql)) {
        Object[] newrow = new Object[34];
        while(rs.next())
        {newrow[0] = SNo + "";
        int max;
        if(m == 2)
            max = 28;
        else if(m == 4 || m == 6 || m == 9 || m == 11)
            max = 30;
        else
            max = 31;
        for(int i = 1; i< max;i++)
            newrow[i] = rs.getObject(i);
//DefaultTableModel tm = (DefaultTableModel)TblStatus.getModel();
//tm.addRow(newrow);
        SNo++;
        }
    }
    }
}
catch(ClassNotFoundException | SQLException e)
{JOptionPane.showMessageDialog(null,"" + e.getMessage());
}
}
    }
    public class BookingSlip extends javax.swing.JFrame {
    //Global Variables
        int bookingNo;
        /**Constructor*/
        public BookingSlip(int bno)
        {bookingNo = bno;
        txtBno.setText(" " + bookingNo);
        java.util.Date D = new java.util.Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
//        lblDate.setText(df.format(D));
        try
{Class.forName("java.sql.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
                String sql = "SELECT * from Booking where BookingNo = " + bookingNo + ";";
    try (ResultSet rs = stmt.executeQuery(sql)) {
        if(rs.next())
        {txtModel.setText(rs.getString("ModelNo"));
        txtN.setText(rs.getString("CustName"));
        txtAdd.setText(rs.getString("Address"));
        txtBdate.setText(df.format(rs.getDate("BookDate")));
        txtDdate.setText(df.format(rs.getDate("DelDate")));
        txtDate.setText(df.format(rs.getDate("BookDate")));
        String s = rs.getString("Sex");
        if(s.equals("m"))
            s = "Male";
        else
            s = "Female";
        txtS.setText(s);
        txtage.setText(rs.getString("Age"));
        txtAdv.setText(rs.getString("Advance"));
        }
    }
            }
}
catch(  ClassNotFoundException | SQLException e)
{JOptionPane.showMessageDialog(null,"" + e);
}
        }
    }
    public class CancellationSlip extends javax.swing.JFrame {
    //Global Variables
        int bookingNo;
      
        public CancellationSlip(int bno)
        {bookingNo = bno;
        txtBno.setText(" " + bookingNo);
        java.util.Date D = new java.util.Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
//        lblDate.setText(df.format(D));
        try
{Class.forName("java.sql.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
                String sql = "SELECT * from Booking where BookingNo = " + bookingNo + ";";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                {txtMno.setText(rs.getString("ModelNo"));
                txtName.setText(rs.getString("CustName"));
                txtAdd.setText(rs.getString("Address"));
                txtBdate.setText(df.format(rs.getDate("BookD")));
                txtDdate.setText(df.format(rs.getDate("DeliveryD")));
                String s = rs.getString("Sex");
                if(s.equals("m"))
                    s = "Male";
                else
                    s = "Female";
                txtS.setText(s);
                txtage.setText(rs.getString("Age"));
                txtAdv.setText(rs.getString("Advance"));
                }
                String sqld = "DELETE FROM Booking where BookingNo = " + bookingNo + ";";
                stmt.executeUpdate(sqld);
            }
}
catch(  ClassNotFoundException | SQLException e)
{JOptionPane.showMessageDialog(null,"" + e);
}
        }
    }
    public class Bill extends javax.swing.JFrame {
    //Global Variables
       public int bookingNo;
        /**Constructor*/
        public Bill(int bno)
        {bookingNo = bno;
        txtBno.setText(" " + bookingNo);
        java.util.Date D = new java.util.Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
txtDateBill.setText(df.format(D));
        try
{Class.forName("java.sql.Driver");

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan");
Statement stmt = conn.createStatement();
String sql = "SELECT * from Booking where BookingNo = " + bookingNo + ";";
ResultSet rs = stmt.executeQuery(sql);
Object[] newrow = new Object[8];
if(rs.next())
{newrow[0] = "1";
newrow[1] = "" + bookingNo;
int mdlno = rs.getInt("ModelNo");
newrow[2] = "" + mdlno;
newrow[3] = rs.getString("BookDate");
newrow[4] = rs.getString("DelDate");
newrow[5] = rs.getString("Advance");
int Total = rs.getInt("Total");
//newrow[7] = "" + Total;
lblPriceFinal.setText("Rs. " + Total + "/-" );
String sql2 = "Select Total from Booking where BookingNo = 1;";
ResultSet rs2 = stmt.executeQuery(sql2);
rs2.next();
newrow[6] = rs2.getString(1);
DefaultTableModel tm = (DefaultTableModel)TblBill.getModel();
tm.addRow(newrow);
}}
catch(  ClassNotFoundException | SQLException e)
{JOptionPane.showMessageDialog(null,"" + e);
}
}}

    public class login
    {

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenu = new javax.swing.JDialog();
        rdShowroom = new javax.swing.JRadioButton();
        rdBORC = new javax.swing.JRadioButton();
        rdReport = new javax.swing.JRadioButton();
        rdExit = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cmdNext = new javax.swing.JButton();
        ShowroomData = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        cmdPrices = new javax.swing.JButton();
        cmdAddModel = new javax.swing.JButton();
        cmdModelDetails = new javax.swing.JButton();
        cmdBack1 = new javax.swing.JButton();
        BookingCancellations = new javax.swing.JDialog();
        jLabel14 = new javax.swing.JLabel();
        cmdMakeBook = new javax.swing.JButton();
        cmdBack2 = new javax.swing.JButton();
        ReportCheck = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cmdBill = new javax.swing.JButton();
        cmdBookingSlip = new javax.swing.JButton();
        cmdBack3 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtRepBookingNo = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        EnterNewPrices = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboTcode2 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        lblcurPrice = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNewPrice = new javax.swing.JTextField();
        lblError = new javax.swing.JLabel();
        cmdChange = new javax.swing.JButton();
        EnterNewModel = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtModelno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboTcode = new javax.swing.JComboBox();
        lblError1 = new javax.swing.JLabel();
        cmdAddModelDetails = new javax.swing.JButton();
        EnterModelDetails = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        txtMno = new javax.swing.JTextField();
        cmdSearchModel = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taModelDetails = new javax.swing.JTextArea();
        lblError2 = new javax.swing.JLabel();
        EnterBookingDetails = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboTcode3 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        cbDateb = new javax.swing.JComboBox();
        cbMonthb = new javax.swing.JComboBox();
        cbYearb = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        cbDated = new javax.swing.JComboBox();
        cbMonthd = new javax.swing.JComboBox();
        cbYeard = new javax.swing.JComboBox();
        cmdSearch1 = new javax.swing.JButton();
        lblReport = new javax.swing.JLabel();
        cmdContinue = new javax.swing.JButton();
        BookingDetails2 = new javax.swing.JDialog();
        jLabel19 = new javax.swing.JLabel();
        txtModelNo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPrice1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        rdMale = new javax.swing.JRadioButton();
        rdFemale = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtDatebook = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtDatedelivery = new javax.swing.JTextField();
        cmdBOOK = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtAdvance = new javax.swing.JTextField();
        buttongrpSEX = new javax.swing.ButtonGroup();
        BookingSlip = new javax.swing.JDialog();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cmdGetBill = new javax.swing.JButton();
        txtBno = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtN = new javax.swing.JTextField();
        txtAdd = new javax.swing.JTextField();
        txtAdv = new javax.swing.JTextField();
        txtBdate = new javax.swing.JTextField();
        txtDdate = new javax.swing.JTextField();
        txtS = new javax.swing.JTextField();
        txtage = new javax.swing.JTextField();
        Bill = new javax.swing.JDialog();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblBill = new javax.swing.JTable();
        jLabel65 = new javax.swing.JLabel();
        lblPriceFinal = new javax.swing.JLabel();
        cmdBack6 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        txtDateBill = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdLogIN = new javax.swing.JButton();

        buttonGroup1.add(rdShowroom);
        rdShowroom.setText("Showroom Data");
        rdShowroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdShowroomActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdBORC);
        rdBORC.setText("Booking / Cancellations");
        rdBORC.setName("rdBORC"); // NOI18N
        rdBORC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBORCActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdReport);
        rdReport.setText("Report Check");

        buttonGroup1.add(rdExit);
        rdExit.setText("Exit");
        rdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdExitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Monotype Corsiva", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Main Menu");

        cmdNext.setText("Next>>");
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainMenuLayout = new javax.swing.GroupLayout(MainMenu.getContentPane());
        MainMenu.getContentPane().setLayout(MainMenuLayout);
        MainMenuLayout.setHorizontalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainMenuLayout.createSequentialGroup()
                        .addContainerGap(253, Short.MAX_VALUE)
                        .addComponent(cmdNext))
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MainMenuLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdBORC)
                                    .addComponent(rdShowroom)
                                    .addComponent(rdReport)
                                    .addComponent(rdExit)))
                            .addGroup(MainMenuLayout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel3)))
                        .addGap(0, 90, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MainMenuLayout.setVerticalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdShowroom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdBORC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdNext)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 102));
        jLabel4.setText("SHOWROOM DATA");

        cmdPrices.setText("Change Prices");
        cmdPrices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPricesActionPerformed(evt);
            }
        });

        cmdAddModel.setText("Add New Model");
        cmdAddModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddModelActionPerformed(evt);
            }
        });

        cmdModelDetails.setText("Model Details");
        cmdModelDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdModelDetailsActionPerformed(evt);
            }
        });

        cmdBack1.setText("<<Back");
        cmdBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowroomDataLayout = new javax.swing.GroupLayout(ShowroomData.getContentPane());
        ShowroomData.getContentPane().setLayout(ShowroomDataLayout);
        ShowroomDataLayout.setHorizontalGroup(
            ShowroomDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowroomDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ShowroomDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmdModelDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdPrices, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAddModel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(cmdBack1)
                .addGap(37, 37, 37))
            .addGroup(ShowroomDataLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShowroomDataLayout.setVerticalGroup(
            ShowroomDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowroomDataLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(cmdPrices)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ShowroomDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAddModel)
                    .addComponent(cmdBack1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdModelDetails)
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("BOOKINGS");

        cmdMakeBook.setText("Make Booking");
        cmdMakeBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMakeBookActionPerformed(evt);
            }
        });

        cmdBack2.setText("<<Back");
        cmdBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BookingCancellationsLayout = new javax.swing.GroupLayout(BookingCancellations.getContentPane());
        BookingCancellations.getContentPane().setLayout(BookingCancellationsLayout);
        BookingCancellationsLayout.setHorizontalGroup(
            BookingCancellationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingCancellationsLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(cmdMakeBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(cmdBack2)
                .addGap(50, 50, 50))
            .addGroup(BookingCancellationsLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BookingCancellationsLayout.setVerticalGroup(
            BookingCancellationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingCancellationsLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(BookingCancellationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdBack2)
                    .addComponent(cmdMakeBook))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 0, 0));
        jLabel30.setText("GENERATE REPORTS");

        cmdBill.setText("Bill");
        cmdBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBillActionPerformed(evt);
            }
        });

        cmdBookingSlip.setText("Booking Slip");
        cmdBookingSlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBookingSlipActionPerformed(evt);
            }
        });

        cmdBack3.setText("<<Back");
        cmdBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBack3ActionPerformed(evt);
            }
        });

        jLabel27.setText("Enter Booking No :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmdBookingSlip, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdBill, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdBack3)
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRepBookingNo)
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel30)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtRepBookingNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(cmdBill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdBookingSlip)
                    .addComponent(cmdBack3))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ReportCheckLayout = new javax.swing.GroupLayout(ReportCheck.getContentPane());
        ReportCheck.getContentPane().setLayout(ReportCheckLayout);
        ReportCheckLayout.setHorizontalGroup(
            ReportCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ReportCheckLayout.setVerticalGroup(
            ReportCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Californian FB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("Enter New Prices");

        jLabel6.setText("Type Code :");

        comboTcode2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100cc", "125cc", "150cc", "175cc", "180cc" }));
        comboTcode2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTcode2ItemStateChanged(evt);
            }
        });
        comboTcode2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTcode2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Current Prices :");

        lblcurPrice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("New Prices :");

        lblError.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cmdChange.setText("Change");
        cmdChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EnterNewPricesLayout = new javax.swing.GroupLayout(EnterNewPrices.getContentPane());
        EnterNewPrices.getContentPane().setLayout(EnterNewPricesLayout);
        EnterNewPricesLayout.setHorizontalGroup(
            EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterNewPricesLayout.createSequentialGroup()
                .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EnterNewPricesLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(cmdChange))
                    .addGroup(EnterNewPricesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EnterNewPricesLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNewPrice)
                            .addComponent(lblcurPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTcode2, 0, 94, Short.MAX_VALUE)))
                    .addGroup(EnterNewPricesLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        EnterNewPricesLayout.setVerticalGroup(
            EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterNewPricesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboTcode2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addGroup(EnterNewPricesLayout.createSequentialGroup()
                        .addComponent(lblcurPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EnterNewPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNewPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdChange)
                .addGap(18, 18, 18)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jLabel8.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 0, 153));
        jLabel8.setText("Enter New Model Details");

        jLabel10.setText("Model Number :");

        txtModelno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModelnoActionPerformed(evt);
            }
        });

        jLabel11.setText("Type Code :");

        comboTcode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100cc", "125cc", "150cc", "175cc", "200cc" }));
        comboTcode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTcodeItemStateChanged(evt);
            }
        });

        lblError1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmdAddModelDetails.setText("Add");
        cmdAddModelDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddModelDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EnterNewModelLayout = new javax.swing.GroupLayout(EnterNewModel.getContentPane());
        EnterNewModel.getContentPane().setLayout(EnterNewModelLayout);
        EnterNewModelLayout.setHorizontalGroup(
            EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterNewModelLayout.createSequentialGroup()
                .addGroup(EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EnterNewModelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModelno, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTcode, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EnterNewModelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(cmdAddModelDetails))))
                    .addGroup(EnterNewModelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel8))
                    .addGroup(EnterNewModelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblError1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        EnterNewModelLayout.setVerticalGroup(
            EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterNewModelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtModelno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EnterNewModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboTcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmdAddModelDetails)
                .addGap(18, 18, 18)
                .addComponent(lblError1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 153, 0));
        jLabel12.setText("Enter The Model Number");

        cmdSearchModel.setText("Search");
        cmdSearchModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchModelActionPerformed(evt);
            }
        });

        jLabel13.setText("DETAILS OF THIS MODEL ARE :");

        taModelDetails.setColumns(20);
        taModelDetails.setRows(5);
        jScrollPane1.setViewportView(taModelDetails);

        lblError2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout EnterModelDetailsLayout = new javax.swing.GroupLayout(EnterModelDetails.getContentPane());
        EnterModelDetails.getContentPane().setLayout(EnterModelDetailsLayout);
        EnterModelDetailsLayout.setHorizontalGroup(
            EnterModelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                .addGroup(EnterModelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(lblError2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(EnterModelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtMno, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(cmdSearchModel))
                    .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EnterModelDetailsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(7, 7, 7))
        );
        EnterModelDetailsLayout.setVerticalGroup(
            EnterModelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterModelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtMno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdSearchModel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lblError2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jLabel15.setFont(new java.awt.Font("Baskerville Old Face", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Enter Booking Details");

        jLabel16.setText("Model Type :");

        comboTcode3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100cc", "125cc", "150cc", "200cc" }));

        jLabel17.setText("Booking Date :");

        cbDateb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" }));

        cbMonthb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbMonthb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMonthbItemStateChanged(evt);
            }
        });
        cbMonthb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonthbActionPerformed(evt);
            }
        });

        cbYearb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013" }));

        jLabel18.setText("Delivery Date :");

        cbDated.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "060", "7", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbMonthd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbMonthd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMonthdItemStateChanged(evt);
            }
        });

        cbYeard.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014" }));

        cmdSearch1.setText("Search");
        cmdSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearch1ActionPerformed(evt);
            }
        });

        lblReport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmdContinue.setText("Continue...");
        cmdContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EnterBookingDetailsLayout = new javax.swing.GroupLayout(EnterBookingDetails.getContentPane());
        EnterBookingDetails.getContentPane().setLayout(EnterBookingDetailsLayout);
        EnterBookingDetailsLayout.setHorizontalGroup(
            EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addGap(10, 10, 10)
                        .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmdSearch1)
                            .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                                .addComponent(cbDated, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMonthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbYeard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                        .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EnterBookingDetailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbDateb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EnterBookingDetailsLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTcode3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EnterBookingDetailsLayout.createSequentialGroup()
                                .addComponent(cbMonthb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbYearb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(cmdContinue))
                    .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel15))
                    .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblReport, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EnterBookingDetailsLayout.setVerticalGroup(
            EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnterBookingDetailsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(comboTcode3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbDateb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMonthb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbYearb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(EnterBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMonthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbYeard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(cmdSearch1)
                .addGap(22, 22, 22)
                .addComponent(lblReport, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdContinue)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel19.setText("Model No. :");

        txtModelNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModelNoActionPerformed(evt);
            }
        });

        jLabel20.setText("Price :");

        jLabel21.setText("Name :");

        jLabel22.setText("Address :");

        jLabel23.setText("Sex :");

        buttongrpSEX.add(rdMale);
        rdMale.setText("Male");

        buttongrpSEX.add(rdFemale);
        rdFemale.setText("Female");

        jLabel24.setText("Age :");

        jLabel25.setText("Booking Date :");

        jLabel26.setText("Delivery Date :");

        cmdBOOK.setFont(new java.awt.Font("Centaur", 1, 12)); // NOI18N
        cmdBOOK.setForeground(new java.awt.Color(153, 0, 0));
        cmdBOOK.setText("Book My Vehicle");
        cmdBOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBOOKActionPerformed(evt);
            }
        });

        jLabel29.setText("Advance :");

        javax.swing.GroupLayout BookingDetails2Layout = new javax.swing.GroupLayout(BookingDetails2.getContentPane());
        BookingDetails2.getContentPane().setLayout(BookingDetails2Layout);
        BookingDetails2Layout.setHorizontalGroup(
            BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingDetails2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                                        .addComponent(txtModelNo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(rdMale)
                                .addGap(59, 59, 59)
                                .addComponent(rdFemale))))
                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAdvance)
                                    .addComponent(txtDatedelivery, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel24))
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDatebook, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdBOOK)))
                .addContainerGap())
        );
        BookingDetails2Layout.setVerticalGroup(
            BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingDetails2Layout.createSequentialGroup()
                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtModelNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(txtPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(rdMale)
                            .addComponent(rdFemale))
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDatebook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDatedelivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAdvance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)))
                            .addGroup(BookingDetails2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdBOOK, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BookingDetails2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(BookingDetails2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Switzerland", 3, 27)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 102, 0));
        jLabel37.setText("PATSON Motorcycles");

        jLabel38.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 102));
        jLabel38.setText("Booking Slip");

        jLabel39.setText("Booking No :-");

        jLabel40.setText("Date :-");

        jLabel41.setText("Model No :-");

        jLabel42.setText("Name :-");

        jLabel43.setText("Address :-");

        jLabel44.setText("Advance :-");

        jLabel50.setText("Booking Date:-");

        jLabel51.setText("Delivery Date :-");

        jLabel52.setText("Sex :-");

        jLabel53.setText("Age :-");

        jLabel45.setText("Authorised Signature");

        cmdGetBill.setText("Get Bill");
        cmdGetBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGetBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BookingSlipLayout = new javax.swing.GroupLayout(BookingSlip.getContentPane());
        BookingSlip.getContentPane().setLayout(BookingSlipLayout);
        BookingSlipLayout.setHorizontalGroup(
            BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingSlipLayout.createSequentialGroup()
                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingSlipLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingSlipLayout.createSequentialGroup()
                                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BookingSlipLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(BookingSlipLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAdv, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BookingSlipLayout.createSequentialGroup()
                                        .addGap(169, 169, 169)
                                        .addComponent(jLabel45)
                                        .addGap(0, 43, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookingSlipLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel53))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BookingSlipLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtS, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BookingSlipLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(33, 33, 33)
                                .addComponent(txtBno, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))))
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(BookingSlipLayout.createSequentialGroup()
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BookingSlipLayout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jLabel38))
                            .addGroup(BookingSlipLayout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(cmdGetBill))
                            .addGroup(BookingSlipLayout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BookingSlipLayout.setVerticalGroup(
            BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookingSlipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(txtBno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BookingSlipLayout.createSequentialGroup()
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(txtBdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookingSlipLayout.createSequentialGroup()
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BookingSlipLayout.createSequentialGroup()
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BookingSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdGetBill)
                .addContainerGap())
        );

        jLabel63.setFont(new java.awt.Font("Bookman Old Style", 3, 27)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(153, 102, 0));
        jLabel63.setText("PATSON Motorcycles");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel64.setText("BILL");

        TblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No.", "Booking No.", "Model No.", "Booking Date", "Delivery Date", "Advance ", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TblBill);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel65.setText("Total Amount :");

        lblPriceFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmdBack6.setText("<<Back");
        cmdBack6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBack6ActionPerformed(evt);
            }
        });

        jLabel66.setText("Date :");

        javax.swing.GroupLayout BillLayout = new javax.swing.GroupLayout(Bill.getContentPane());
        Bill.getContentPane().setLayout(BillLayout);
        BillLayout.setHorizontalGroup(
            BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
            .addGroup(BillLayout.createSequentialGroup()
                .addGroup(BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillLayout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDateBill, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(BillLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel63)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addGroup(BillLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                    .addGroup(BillLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdBack6)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel65)
                        .addGap(7, 7, 7)
                        .addComponent(lblPriceFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BillLayout.setVerticalGroup(
            BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(BillLayout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel64))
                    .addGroup(BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(txtDateBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblPriceFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BillLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(BillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(cmdBack6))))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("WELCOME TO PATSON MOTORCYCLES");

        cmdLogIN.setText("Log In");
        cmdLogIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogINActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(cmdLogIN)
                .addContainerGap(288, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(79, 79, 79)
                .addComponent(cmdLogIN)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLogINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogINActionPerformed
MainMenu.setVisible(true);
    }//GEN-LAST:event_cmdLogINActionPerformed

    private void rdShowroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdShowroomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdShowroomActionPerformed

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
if(rdShowroom.isSelected())
{SData sd = new SData();
 ShowroomData.setVisible(true);
 this.setVisible(false);
}
else if(rdBORC.isSelected())
{BookCancel bc = new BookCancel();
 BookingCancellations.setVisible(true);
 this.setVisible(false);
}
else if(rdReport.isSelected())
{Status sta = new Status();
 ReportCheck.setVisible(true);
 this.setVisible(false);
}
else if(rdExit.isSelected())
{MainMenu.dispose();
}
    }//GEN-LAST:event_cmdNextActionPerformed

    private void txtModelnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModelnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelnoActionPerformed

    private void cmdAddModelDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddModelDetailsActionPerformed
lblError1.setText("");
String mdlno = txtModelno.getText();
String tcode = comboTcode.getSelectedItem().toString();
try
{Class.forName("java.sql.Driver");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "Insert into Model values('" + mdlno + "','" + tcode + "');";
        stmt.executeUpdate(sql);
        lblError1.setText("Information Added");
    }
}
catch(ClassNotFoundException | SQLException e)
{lblError1.setText("Incorrect Entry");
}
    }//GEN-LAST:event_cmdAddModelDetailsActionPerformed

    private void comboTcodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTcodeItemStateChanged
    }//GEN-LAST:event_comboTcodeItemStateChanged

    private void comboTcode2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTcode2ItemStateChanged
String code = comboTcode2.getSelectedItem().toString();
try
{Class.forName("java.sql.Driver");
String str;
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "SELECT Prices from Type where Typecode = '" + code + "';";
    try (ResultSet rs = stmt.executeQuery(sql)) {
        rs.next();
        str = rs.getString("Prices");
    }
    }
lblcurPrice.setText("Rs" + str);
}
catch(ClassNotFoundException | SQLException e){}
    }//GEN-LAST:event_comboTcode2ItemStateChanged

    private void cmdChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdChangeActionPerformed
String code = comboTcode2.getSelectedItem().toString();
int price = Integer.parseInt(txtNewPrice.getText());
try
{Class.forName("java.sql.Driver");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "Update Type set Prices = " + price + " where TypeCode = '" + code + "';";
        stmt.executeUpdate(sql);
        lblError.setText("Information Added");
    }
}
catch(ClassNotFoundException | SQLException e)
{lblError.setText("Invalid Data");
}
    }//GEN-LAST:event_cmdChangeActionPerformed

    private void cmdSearchModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchModelActionPerformed
int mdlno = Integer.parseInt(txtMno.getText());
try
{Class.forName("java.sql.Driver");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "SELECT Type.TypeCode,Description,Prices from Type,Model where ModelNo = '" + mdlno + "' and Type.TypeCode = Model.TypeCode;";
    try (ResultSet rs = stmt.executeQuery(sql)) {
        rs.next();
        String str = rs.getString("TypeCode");
        taModelDetails.setText(str);
        String str1 = rs.getString("Description");
        String str2 = rs.getString("Prices");
        taModelDetails.setText("Model Number :" + mdlno + "\nType :" + str + "\nDescription: " + str1 + "\nPrices :" + str2);
    }
    }
}
catch(ClassNotFoundException | SQLException e)
{lblError2.setText("Model Number not found");
}
    }//GEN-LAST:event_cmdSearchModelActionPerformed

    private void cmdBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBack1ActionPerformed
MainMenu.setVisible(true);
this.dispose();
    }//GEN-LAST:event_cmdBack1ActionPerformed

    private void cmdAddModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddModelActionPerformed
EnterNewModel.setVisible(true);
EnterNewPrices.setVisible(false);
EnterModelDetails.setVisible(false);
ShowroomData.setVisible(false);
    }//GEN-LAST:event_cmdAddModelActionPerformed

    private void cmdPricesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPricesActionPerformed
EnterNewModel.setVisible(false);
EnterNewPrices.setVisible(true);
EnterModelDetails.setVisible(false);
ShowroomData.setVisible(false);
    }//GEN-LAST:event_cmdPricesActionPerformed

    private void cmdModelDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModelDetailsActionPerformed
EnterNewModel.setVisible(false);
EnterNewPrices.setVisible(false);
EnterModelDetails.setVisible(true);
ShowroomData.setVisible(false);
    }//GEN-LAST:event_cmdModelDetailsActionPerformed

    private void cmdMakeBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMakeBookActionPerformed
BookingDetails2.setVisible(false);
EnterBookingDetails.setVisible(true);
BookingCancellations.setVisible(false);
    }//GEN-LAST:event_cmdMakeBookActionPerformed

    private void cmdBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBack2ActionPerformed
MainMenu.setVisible(true);
this.dispose();
    }//GEN-LAST:event_cmdBack2ActionPerformed

    private void cmdSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearch1ActionPerformed
int monthb = Integer.parseInt(cbMonthb.getSelectedItem().toString());
int monthd = Integer.parseInt(cbMonthd.getSelectedItem().toString());
int Dayb = Integer.parseInt(cbDateb.getSelectedItem().toString());
int Dayd = Integer.parseInt(cbDated.getSelectedItem().toString());
int yearb = Integer.parseInt(cbYearb.getSelectedItem().toString());
int yeard = Integer.parseInt(cbYeard.getSelectedItem().toString());
String Tcode = comboTcode3.getSelectedItem().toString();
lblReport.setText("Model Type "  + Tcode + " is Available. Click Continue to book the Model.");
cmdContinue.setVisible(true);
txtDatebook.setText("" + yearb + "/" + monthb + "/" + Dayb);
txtDatedelivery.setText("" + yeard + "/" + monthd + "/" + Dayd);
    }//GEN-LAST:event_cmdSearch1ActionPerformed

    private void cmdContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdContinueActionPerformed
String code = comboTcode3.getSelectedItem().toString();
try
{Class.forName("java.sql.Driver");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan"); Statement stmt = conn.createStatement()) {
        String sql = "SELECT Prices from Type where TypeCode = '" + code + "';";
    try (ResultSet rs = stmt.executeQuery(sql)) {
        rs.next();
        int price = rs.getInt("Prices");
        txtPrice1.setText("" + price);
    }
    }
}
catch(ClassNotFoundException | SQLException e)
{}
EnterBookingDetails.setVisible(false);
BookingDetails2.setVisible(true);
    }//GEN-LAST:event_cmdContinueActionPerformed

    private void cbMonthbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMonthbItemStateChanged
cbDateb.removeAllItems();
String m = "0";
int mon  = 0,days;
int yr = Integer.parseInt(cbYearb.getSelectedItem().toString());
if(cbMonthb.getSelectedIndex()>= 1)
{mon = Integer.parseInt(cbMonthb.getSelectedItem().toString());
}
if(mon==2)
    if(yr%4 != 0)
        days = 28;
else
        days = 29;
else if(mon==4 || mon == 6 || mon == 9 || mon == 11)
    days = 30;
else
    days = 31;
for(int i = 1; i<= days;i++)
{if(i>9)
    m = "";
cbDateb.addItem(m+i);
}
    }//GEN-LAST:event_cbMonthbItemStateChanged

    private void cbMonthdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMonthdItemStateChanged
cbDated.removeAllItems();
String m = "0";
int mon  = 0,days;
int yr = Integer.parseInt(cbYeard.getSelectedItem().toString());
if(cbMonthd.getSelectedIndex()>= 1)
{mon = Integer.parseInt(cbMonthd.getSelectedItem().toString());
}
if(mon==2)
    if(yr%4 != 0)
        days = 28;
else
        days = 29;
else if(mon==4 || mon == 6 || mon == 9 || mon == 11)
    days = 30;
else
    days = 31;
for(int i = 1; i<= days;i++)
{if(i>9)
    m = "";
cbDated.addItem(m+i);
}
    }//GEN-LAST:event_cbMonthdItemStateChanged
int bookingno;
    private void cmdBOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBOOKActionPerformed
char gender = 'n';
int monthb = Integer.parseInt(cbMonthb.getSelectedItem().toString());
int month = monthb;
if(rdMale.isSelected())
    gender = 'm';
else if(rdFemale.isSelected())
    gender = 'f';
int age = Integer.parseInt(txtAge.getText());
JOptionPane.showMessageDialog(null,"Done");
int mdlno = Integer.parseInt(txtModelNo.getText());
int advance = Integer.parseInt(txtAdvance.getText());
int tot = Integer.parseInt(txtPrice1.getText());
try
{Class.forName("java.sql.Driver");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","roshan")) {
        Statement stmt2 = conn.createStatement();
        String sql2 = "SELECT max(BookingNo) from booking;";
        ResultSet rs = stmt2.executeQuery(sql2);
        rs.next();
        int  bookingno = rs.getInt(1) + 1;
    try (Statement stmt = conn.createStatement()) {
        String sql = "INSERT INTO booking values ("+bookingno +",'" + txtName.getText() + "','" + txtAddress.getText() + "' , '" + gender + "' ," + age + "," + mdlno + ",'" + txtDatebook.getText() + "','" + txtDatedelivery.getText() + "'," + advance + "," +tot + ");";
        stmt.executeUpdate(sql);
        int monthd = Integer.parseInt(cbMonthd.getSelectedItem().toString());
        int Dayb = Integer.parseInt(cbDateb.getSelectedItem().toString());
        int Dayd = Integer.parseInt(cbDated.getSelectedItem().toString());
        while(month <=monthd)
        {int max = getmax(monthb,monthd,Dayd);
        int min = 0;
        if(month == monthb)
            min = Dayb;
        else
            min = 1;
//for(int d = min ;d<=max;d++)
//{String sql3 = "UPDATE Status" + month + "SET D" + d + "= 'b' where BookingNo = " + bookingno + ";";
//stmt.executeUpdate(sql3);
//}
        month++;
        }
        JOptionPane.showMessageDialog(null,"Model Booked. Booking Number is : " + bookingno);
    }
    }
}
catch(ClassNotFoundException | SQLException | NumberFormatException | HeadlessException e)
{JOptionPane.showMessageDialog(null,"Invalid Data"+e);
}
    }//GEN-LAST:event_cmdBOOKActionPerformed

    private void cmdBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBack3ActionPerformed
MainMenu.setVisible(true);
this.dispose();
    }//GEN-LAST:event_cmdBack3ActionPerformed

    private void cmdBookingSlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBookingSlipActionPerformed
int BNO = Integer.parseInt(txtRepBookingNo.getText());
BookingSlip bs = new BookingSlip(BNO);
BookingSlip.setVisible(true);
ReportCheck.setVisible(false);
    }//GEN-LAST:event_cmdBookingSlipActionPerformed

    private void cmdBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBillActionPerformed
int BNO = Integer.parseInt(txtRepBookingNo.getText());
Bill.setVisible(true);
Bill B = new Bill(BNO);
ReportCheck.setVisible(false);

    }//GEN-LAST:event_cmdBillActionPerformed

    private void cmdGetBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGetBillActionPerformed
int BNO = Integer.parseInt(txtRepBookingNo.getText());
Bill.setVisible(true);
Bill B = new Bill(BNO);
BookingSlip.setVisible(false);
    }//GEN-LAST:event_cmdGetBillActionPerformed

    private void cmdBack6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBack6ActionPerformed
MainMenu.setVisible(true);
Bill.dispose();
    }//GEN-LAST:event_cmdBack6ActionPerformed

    private void comboTcode2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTcode2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTcode2ActionPerformed

    private void cbMonthbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonthbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMonthbActionPerformed

    private void rdBORCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBORCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdBORCActionPerformed

    private void txtModelNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModelNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelNoActionPerformed

    private void rdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdExitActionPerformed

    }//GEN-LAST:event_rdExitActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Booking().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Bill;
    private javax.swing.JDialog BookingCancellations;
    private javax.swing.JDialog BookingDetails2;
    private javax.swing.JDialog BookingSlip;
    private javax.swing.JDialog EnterBookingDetails;
    private javax.swing.JDialog EnterModelDetails;
    private javax.swing.JDialog EnterNewModel;
    private javax.swing.JDialog EnterNewPrices;
    private javax.swing.JDialog MainMenu;
    private javax.swing.JDialog ReportCheck;
    private javax.swing.JDialog ShowroomData;
    private javax.swing.JTable TblBill;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttongrpSEX;
    private javax.swing.JComboBox cbDateb;
    private javax.swing.JComboBox cbDated;
    private javax.swing.JComboBox cbMonthb;
    private javax.swing.JComboBox cbMonthd;
    private javax.swing.JComboBox cbYearb;
    private javax.swing.JComboBox cbYeard;
    private javax.swing.JButton cmdAddModel;
    private javax.swing.JButton cmdAddModelDetails;
    private javax.swing.JButton cmdBOOK;
    private javax.swing.JButton cmdBack1;
    private javax.swing.JButton cmdBack2;
    private javax.swing.JButton cmdBack3;
    private javax.swing.JButton cmdBack6;
    private javax.swing.JButton cmdBill;
    private javax.swing.JButton cmdBookingSlip;
    private javax.swing.JButton cmdChange;
    private javax.swing.JButton cmdContinue;
    private javax.swing.JButton cmdGetBill;
    private javax.swing.JButton cmdLogIN;
    private javax.swing.JButton cmdMakeBook;
    private javax.swing.JButton cmdModelDetails;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdPrices;
    private javax.swing.JButton cmdSearch1;
    private javax.swing.JButton cmdSearchModel;
    private javax.swing.JComboBox comboTcode;
    private javax.swing.JComboBox comboTcode2;
    private javax.swing.JComboBox comboTcode3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblError1;
    private javax.swing.JLabel lblError2;
    private javax.swing.JLabel lblPriceFinal;
    private javax.swing.JLabel lblReport;
    private javax.swing.JLabel lblcurPrice;
    private javax.swing.JRadioButton rdBORC;
    private javax.swing.JRadioButton rdExit;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JRadioButton rdReport;
    private javax.swing.JRadioButton rdShowroom;
    private javax.swing.JTextArea taModelDetails;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAdv;
    private javax.swing.JTextField txtAdvance;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBdate;
    private javax.swing.JTextField txtBno;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDateBill;
    private javax.swing.JTextField txtDatebook;
    private javax.swing.JTextField txtDatedelivery;
    private javax.swing.JTextField txtDdate;
    private javax.swing.JTextField txtMno;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtModelNo;
    private javax.swing.JTextField txtModelno;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNewPrice;
    private javax.swing.JTextField txtPrice1;
    private javax.swing.JTextField txtRepBookingNo;
    private javax.swing.JTextField txtS;
    private javax.swing.JTextField txtage;
    // End of variables declaration//GEN-END:variables
}
