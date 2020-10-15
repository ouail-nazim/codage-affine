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


    public void encryption(ActionEvent actionEvent) {
        if ((A.getText().length() >= 1) &&(B.getText().length() >= 1)&&(Message.getText().length() >= 1)){
            try {
                a=Integer.parseInt(String.valueOf(A.getText()));
                b=Integer.parseInt(String.valueOf(B.getText()));
                if (this.prem26(a)== true){
                    before=Message.getText().toUpperCase();
                    after=this.encryptionMessage(before,a,b);
                    Resultat.setText(after);
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
    public boolean prem26(Integer a){
        if (a%13 == 0 || a%2 == 0){
            return false;
        }
        return true;
    }
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
        return Cmsg;
    }
    public String deccryptionMessage(String Msg,int a ,int b){
        String DCmsg = "";
        for (int i = 0; i < Msg.length(); i++)
        {
            if (Msg.charAt(i) != '#' ){
                int Y=alphbie.indexOf(Msg.charAt(i));
               // this is the Error
                int X=((a*(Y-b))%26);
                String equ=Msg.charAt(i)+"=>"+Y+" :X=("+a+"*("+Y+'-'+b+"))mod 26 => X="+X ;
                System.out.println(equ);

                //DCmsg= DCmsg+alphbie.charAt(X);

            }else {
                DCmsg= DCmsg+"_";
            }
        }
        //System.out.println(DCmsg);
        //return DCmsg;
        return "not working yet";
    }


}
