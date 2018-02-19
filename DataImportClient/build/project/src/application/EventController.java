package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class EventController implements Initializable {

   @FXML
   private Button btnSingleChooseTmp;//�����ļ�����ģ��ѡ��ť

   @FXML
   private TextField tfdSingleTmp;//�����ļ�����ģ��·����
   
   @FXML
   private Button btnMultiChooseTmp;//�ļ�Ŀ¼����ģ��ѡ��ť

   @FXML
   private TextField tfdMultiTmp;//�ļ�Ŀ¼����ģ��·����
   
   @FXML
   private MenuBar menuBar;//�˵���
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
   }
 
   /**
    * ���������ļ�����ģ��ѡ���
    */
   public void showSingleTemplateChooseDialog(){
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
       fileChooser.getExtensionFilters().add(extFilter);
       File file = fileChooser.showOpenDialog(btnSingleChooseTmp.getContextMenu());
       if (null != file) {
    	   tfdSingleTmp.setText(file.getAbsolutePath());
       }
   }
   
   /**
    * ��������ļ�����ģ��ѡ���
    */
   public void showMultiTemplateChooseDialog(){
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
       fileChooser.getExtensionFilters().add(extFilter);
       File file = fileChooser.showOpenDialog(btnMultiChooseTmp.getContextMenu());
       if (null != file) {
    	   tfdMultiTmp.setText(file.getAbsolutePath());
       }
   }
   
   /**
    * ������������ģ��ҳ��
    */
   public void toBuildTmp(){
	   System.out.println("��������ģ��ҳ��");
   }
   
   public void toAbout(){
	   System.out.println("����ҳ��");
   }
  
}