package View;

public class Start extends javax.swing.JFrame {

    public Start() {
        initComponents();
    }

    @SuppressWarnings("unchecked")                       
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        appName = new javax.swing.JLabel();
        openAppTime = new javax.swing.JProgressBar();
        versionLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setLocation(new java.awt.Point(200, 100));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1000, 500));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setMaximumSize(getPreferredSize());
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));

        appName.setBackground(new java.awt.Color(204,255,255));
        appName.setFont(new java.awt.Font("JMH Typewriter", 0, 32));
        appName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appName.setText("Digital Signature Tool");
        appName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        appName.setAlignmentY(0.0F);
        appName.setPreferredSize(new java.awt.Dimension(1000, 240));

        openAppTime.setForeground(new java.awt.Color(255, 102, 102));
        openAppTime.setPreferredSize(new java.awt.Dimension(600, 10));

        versionLb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        versionLb.setText("version 2022.01");
        versionLb.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        versionLb.setPreferredSize(new java.awt.Dimension(900, 190));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(openAppTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(versionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(appName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(openAppTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(versionLb, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 500);

        pack();
        setLocationRelativeTo(null);
    }   

    private javax.swing.JLabel appName;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JProgressBar openAppTime;
    private javax.swing.JLabel versionLb;
}
