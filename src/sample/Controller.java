package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.*;

public class Controller {

    private String before,after;
    private int a,b;
    private String alphbie="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @FXML private TextField A ;
    @FXML private TextField B ;
    @FXML private TextArea Message ;
    @FXML private TextArea Resultat ;
    @FXML private Label equ ;

    // click sur le button encryption
    public void encryption(ActionEvent actionEvent) {
        if ((A.getText().length() >= 1) &&(B.getText().length() >= 1)&&(Message.getText().length() >= 1)){
            try {
                a=Integer.parseInt(String.valueOf(A.getText()));
                b=Integer.parseInt(String.valueOf(B.getText()));
                if (this.prem26(a)== true){
                    before=Message.getText().toUpperCase();
                    after=this.encryptionMessage(before,a,b);
                    Resultat.setText(after);
                    Message.setText("");
                }else {
                    Resultat.setText("A doit être premier avec 26");
                }
            } catch(Exception e) {
                Resultat.setText("A and B they mast be integer");
            }
        }else{
            Resultat.setText("mal remplir");
        }
    }
    // click sur le button decryption
    public void decryption(ActionEvent actionEvent) {
        if ((A.getText().length() >= 1) &&(B.getText().length() >= 1)&&(Message.getText().length() >= 1)){
            try {
                a=Integer.parseInt(String.valueOf(A.getText()));
                b=Integer.parseInt(String.valueOf(B.getText()));
                if (this.prem26(a)== true){
                    before=Message.getText().toUpperCase();
                    a=this.getFlag(a);
                    after=this.deccryptionMessage(before,a,b);
                    Resultat.setText(after.toLowerCase());
                    Message.setText("");
                }else {
                    Resultat.setText("A doit être premier avec 26");
                }
            } catch(Exception e) {
                Resultat.setText("A and B they mast be integer");
            }
        }else{
            Resultat.setText("mal remplir");
        }
    }
    // encryption the message
    public String encryptionMessage(String Msg,int a ,int b){
        String Cmsg = "";
        for (int i = 0; i < Msg.length(); i++)
        {
            if (Msg.charAt(i) == ' ' ){
                Cmsg= Cmsg+"#";
            }else{
                int Y= ((a * alphbie.indexOf(Msg.charAt(i)))+b)%26;
                Cmsg= Cmsg+alphbie.charAt(Y);
            }


        }
        String equstr="X=("+a+"*Y)+"+b+"))mod 26";
        equ.setText("Chiffrement : "+equstr);
        return Cmsg;
    }
    // decryption the message
    public String deccryptionMessage(String Msg,int a ,int b){
        String DCmsg = "";
        for (int i = 0; i < Msg.length(); i++)
        {
            if (Msg.charAt(i) != '#' ){
                int Y=alphbie.indexOf(Msg.charAt(i));
                int X=Math.floorMod((a*(Y-b)), 26);
                DCmsg= DCmsg+alphbie.charAt(X);
            }else {
                DCmsg= DCmsg+" ";
            }
        }
        String equstr="X=("+a+"*(Y-"+b+"))mod 26";
        equ.setText("Déchiffrement : "+equstr);
        return DCmsg;
    }
    // check if a is premier avec 26
    public boolean prem26(Integer a){
        if (a%13 == 0 || a%2 == 0){
            return false;
        }
        return true;
    }
    // get l’inverse modulaire de a
    public int getFlag(Integer a){
        int flag = 0;
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;
            if (flag == 1)
            {
                return i;
            }
        }
        return -1;
    }
}
