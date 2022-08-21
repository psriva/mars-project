import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 236605
 */
public class JobLocationChangePanel extends javax.swing.JFrame {

    private Colony colonyData;
    /**
     * Creates new form JobLocationChangePanel
     */
    public JobLocationChangePanel() throws FileNotFoundException {
        initComponents();
        colonyData = new Colony();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rankIDLabel = new javax.swing.JLabel();
        rankIDInput = new javax.swing.JTextField();
        newPositionInput = new javax.swing.JTextField();
        newLocationLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        newPositionsPossible = new javax.swing.JTextArea();
        enter = new javax.swing.JButton();
        backtoMain = new javax.swing.JButton();
        successLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rankIDLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rankIDLabel.setText("Enter Colonist Rank ID Below");
        getContentPane().add(rankIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 430, 90));

        rankIDInput.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rankIDInput.setText("Enter Rank ID");
        getContentPane().add(rankIDInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 790, 80));

        newPositionInput.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        newPositionInput.setText("Enter New Job Location");
        getContentPane().add(newPositionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 790, 80));

        newLocationLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        newLocationLabel.setText("Enter New Job Location Below");
        getContentPane().add(newLocationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 430, 90));

        newPositionsPossible.setColumns(20);
        newPositionsPossible.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        newPositionsPossible.setRows(5);
        newPositionsPossible.setText("10 Command Center\n20 Storage Facility\n21 Junkyard\n30 Research Building\n40 Hydroponics\n41 Greenhouse\n50 Machinist Shop\n51 Smelter\n60 Water Treatment Facility\n70 Hospital");
        jScrollPane1.setViewportView(newPositionsPossible);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 170, 510, 270));

        enter.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        enter.setText("Enter");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        getContentPane().add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 300, 60));

        backtoMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        backtoMain.setText("Back To Personnel");
        backtoMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtoMainActionPerformed(evt);
            }
        });
        getContentPane().add(backtoMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 800, 690, 190));

        successLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(successLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 770, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        try {
            String rankID = rankIDInput.getText();
            String newPosition = newPositionInput.getText();
            colonyData.jobLocationChange(rankID, newPosition);
            successLabel.setText("Location Change Successful");
        } catch (IOException ex) {
            Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enterActionPerformed

    private void backtoMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backtoMainActionPerformed
        try {
            PersonnelPanel p = new PersonnelPanel();
            p.setVisible(true);
            dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PromotionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backtoMainActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JobLocationChangePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JobLocationChangePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JobLocationChangePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JobLocationChangePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JobLocationChangePanel().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JobLocationChangePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backtoMain;
    private javax.swing.JButton enter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel newLocationLabel;
    private javax.swing.JTextField newPositionInput;
    private javax.swing.JTextArea newPositionsPossible;
    private javax.swing.JTextField rankIDInput;
    private javax.swing.JLabel rankIDLabel;
    private javax.swing.JLabel successLabel;
    // End of variables declaration//GEN-END:variables
}
