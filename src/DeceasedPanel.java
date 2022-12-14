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
public class DeceasedPanel extends javax.swing.JFrame {

    private Colony colonyData;
    /**
     * Creates new form DeceasedPanel
     */
    public DeceasedPanel() throws FileNotFoundException {
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
        enter = new javax.swing.JButton();
        backtoMain = new javax.swing.JButton();
        successLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rankIDLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rankIDLabel.setText("Enter Colonist Rank ID Below");
        getContentPane().add(rankIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 430, 90));

        rankIDInput.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rankIDInput.setText("Enter Rank ID");
        rankIDInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankIDInputActionPerformed(evt);
            }
        });
        getContentPane().add(rankIDInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1260, 130));

        enter.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        enter.setText("Enter");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        getContentPane().add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 300, 60));

        backtoMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        backtoMain.setText("Back To Personnel");
        backtoMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backtoMainActionPerformed(evt);
            }
        });
        getContentPane().add(backtoMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 790, 690, 190));

        successLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(successLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 770, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        try {
            String rankID = rankIDInput.getText();
            colonyData.deceased(rankID);
            successLabel.setText("Data Recorded Succesfully");
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

    private void rankIDInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankIDInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rankIDInputActionPerformed

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
            java.util.logging.Logger.getLogger(DeceasedPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeceasedPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeceasedPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeceasedPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DeceasedPanel().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DeceasedPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backtoMain;
    private javax.swing.JButton enter;
    private javax.swing.JTextField rankIDInput;
    private javax.swing.JLabel rankIDLabel;
    private javax.swing.JLabel successLabel;
    // End of variables declaration//GEN-END:variables
}
