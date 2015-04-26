
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Karen
 */
public class LobbyView extends javax.swing.JPanel {

    DefaultListModel<String> onlineModel, receivedModel, sentModel;
    LobbyController controller;
    String userSelected;
    String userSelectedReceived;

    /**
     * Creates new form FinalLobbyView
     */
    public LobbyView() {
        onlineModel = new DefaultListModel<String>();
        receivedModel = new DefaultListModel<String>();
        sentModel = new DefaultListModel<String>();

        initComponents();
    }

    public void setController(LobbyController lc) {
        this.controller = lc;
    }

    public void setLobbyView(LobbyView v) {
        controller.setLobbyView(v);
    }

    public void updateOnlinePlayerList(String[] online) {
        onlineModel.clear();
        for (int i = 1; i < online.length; i++) {
            onlineModel.addElement(online[i]);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gomokuLabel = new javax.swing.JLabel();
        onlinePlayerPane = new javax.swing.JScrollPane();
        onlinePlayersList = new javax.swing.JList();
        onlinePlayersList.setPrototypeCellValue("Index 1234567");
        challengesReceivedPane = new javax.swing.JScrollPane();
        challengesReceivedList = new javax.swing.JList();
        challengesReceivedList.setPrototypeCellValue("Index 1234567");
        challengesSentPane = new javax.swing.JScrollPane();
        challengesSentList = new javax.swing.JList();
        challengesSentList.setPrototypeCellValue("Index 1234567");
        quitB = new javax.swing.JButton();
        acceptB = new javax.swing.JButton();
        rejectB = new javax.swing.JButton();
        challengeB = new javax.swing.JButton();

        onlineUsersL = new javax.swing.JLabel();
        challengesRecdL = new javax.swing.JLabel();
        challengesSentL = new javax.swing.JLabel();

        challengeB.setText("Challenge");

        onlineUsersL.setText("Online Users");

        challengesRecdL.setText("Challenges Received");

        challengesSentL.setText("Challenges Sent");

        gomokuLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        gomokuLabel.setText("Gomoku");

        onlinePlayersList.setModel(onlineModel);
        onlinePlayersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onlinePlayersListValueChanged(evt);
            }
        });
        onlinePlayerPane.setViewportView(onlinePlayersList);

        challengesReceivedList.setModel(receivedModel);
        challengesReceivedPane.setViewportView(challengesReceivedList);
        challengesReceivedList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                challengesReceivedListValueChanged(evt);
            }
        });


        challengesSentList.setModel(sentModel);
        challengesSentPane.setViewportView(challengesSentList);

        quitB.setText("Quit");
        quitB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBActionPerformed(evt);
            }
        });

        acceptB.setText("Accept");
        acceptB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBActionPerformed(evt);
            }
        });


        rejectB.setText("Reject");
        rejectB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBActionPerformed(evt);
            }
        });

        challengeB.setText("Challenge");
        challengeB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                challengeBActionPerformed(evt);
            }
        });
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quitB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(challengeB)
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acceptB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rejectB, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(onlinePlayerPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(challengesReceivedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(challengesRecdL))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(challengesSentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(challengesSentL)
                                        .addGap(13, 13, 13))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(onlineUsersL)
                            .addComponent(gomokuLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gomokuLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onlineUsersL)
                    .addComponent(challengesRecdL)
                    .addComponent(challengesSentL, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(onlinePlayerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(challengesReceivedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(challengesSentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptB)
                    .addComponent(challengeB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rejectB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(quitB)
                .addContainerGap())
        );
    }// </editor-fold>                   

    private void quitBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitBActionPerformed

    private void acceptBActionPerformed(ActionEvent evt) {
        controller.sendAcceptResponse(userSelectedReceived);
    }

    private void rejectBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBActionPerformed
        controller.sendRejectResponse(userSelectedReceived);
    }

    private void challengeBActionPerformed(ActionEvent evt) {
        updateSentList(userSelected);
        controller.sendChallenge(userSelected);
    }

    private void onlinePlayersListValueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting() && !onlinePlayersList.isSelectionEmpty()) {
            userSelected = (String) onlinePlayersList.getSelectedValue();
        }
    }

    private void challengesReceivedListValueChanged(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting() && !challengesReceivedList.isSelectionEmpty()) {
            userSelectedReceived = (String) challengesReceivedList.getSelectedValue();
        }
    }

    private void updateSentList(String userSelected) {

        sentModel.addElement(userSelected);
    }

    public void updateReceivedList(String userSelected) {
        receivedModel.addElement(userSelected);

    }
    
    void removeFromSentChallenges(String rej) {
            
            sentModel.removeElement(rej);
            displayErrorMessage("User " + rej + " rejected your game challenge.");
    }
    
     void displayErrorMessage(String errorMessage) {
         
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptB;
    private javax.swing.JList challengesReceivedList;
    private javax.swing.JScrollPane challengesReceivedPane;
    private javax.swing.JList challengesSentList;
    private javax.swing.JScrollPane challengesSentPane;
    private javax.swing.JLabel gomokuLabel;
    private javax.swing.JScrollPane onlinePlayerPane;
    private javax.swing.JList onlinePlayersList;
    private javax.swing.JButton quitB;
    private javax.swing.JButton rejectB;
    private javax.swing.JButton challengeB;
    private javax.swing.JLabel challengesRecdL;
    private javax.swing.JLabel challengesSentL;
    private javax.swing.JLabel onlineUsersL;
    // End of variables declaration//GEN-END:variables

    
}
