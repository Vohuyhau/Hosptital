import Hospital.HospitalView;
import Hospital.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login  extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btLogin;
    private JButton btCancel;
    private JPanel mainPanel;

    public Login(String title){
        super (title);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private void cancel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void login() {
        String name = txtUsername.getText();
        String pass = String.valueOf(txtPassword.getPassword());
        HospitalView b  = null;
        User admin = new User("Doctor","123");
        User checkUser = new User(name,pass);

        boolean login = false;

        if(admin.equals(checkUser)){
            b = new HospitalView(name,this);
            login = true;
        }
        if(login){
            b.setVisible(true);
            this.setVisible(false);
        }else {
            showMess("Login Failed");
        }
    }
    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this,mess);
    }

}

