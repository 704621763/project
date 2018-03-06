package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
   
   String[] downloadableExtensions = {".doc", ".xls", ".zip", ".exe", ".rar", ".pdf", ".jar", ".png", ".jpg", ".gif"};
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   Label menuLabel = new Label("��������ģ��");
	   menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event) {
	    	   toBuildTmp();
	       }
	   });
	   Menu btnBuildTmp = new Menu();
	   btnBuildTmp.setGraphic(menuLabel);
	   menuBar.getMenus().add(btnBuildTmp);

	   //����
	   Label menuLabel2 = new Label("����");
	   menuLabel2.setOnMouseClicked(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event) {
	    	   toAbout();
	       }
	   });
	   Menu btnAboutTmp = new Menu();
	   btnAboutTmp.setGraphic(menuLabel2);
	   //
	   menuBar.getMenus().add(btnAboutTmp);
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
		try {
 			Stage window = new Stage();
			window.setTitle("��������ģ��");
			AnchorPane root = FXMLLoader.load(getClass()
			        .getResource("/ui/buildTmp.fxml"));
			Scene scene = new Scene(root);
			WebView buildTmpWv = (WebView)scene.lookup("#buildTmpWv");
			
			final WebEngine webEngine = buildTmpWv.getEngine();
			webEngine.load("http://rj.baidu.com/index.html?fxq");
			webEngine.locationProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
 	            	
 	                for(String downloadAble : downloadableExtensions) {
	                    if ( newValue.endsWith(downloadAble)) {
	                    	 try {
// 	                             if(!file.exists()) {
//	                                 file.mkdir();
//	                             }
	                            
 	                             File fileTmp = new File(newValue);
 	                     	     FileChooser fileChooser = new FileChooser();
	    	            	     fileChooser.setInitialFileName(fileTmp.getName());
	                             File download = fileChooser.showSaveDialog(null);
//	                             File download = new File(file + "/" + fileTmp.getName());
	                             if (null == download) {
	                            	 break;
	                             }
	                             if(download.exists()) {
 	                                 showDialog("��ʾ","���ص��ļ��Ѵ���");
	                                 break;
	                             }
  	                             FileUtils.copyURLToFile(new URL(webEngine.getLocation()), download);
  	                             showDialog("��ʾ","�������,����·��: " + download.getAbsolutePath());
   	                             break;
	                    	 } catch(Exception e) {
	                             e.printStackTrace();
	                         }
	                    }
	                }
	            }
	        });
			
			window.setScene(scene);
			//ʹ��showAndWait()�ȴ���������ڣ������������main�е��Ǹ����ڲ�����Ӧ
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
   
   public void toAbout(){
	   System.out.println("����ҳ��");
   }
   public void ttt(){
	   System.out.println("����ҳqweqwe��");
   }
   
   public Stage showDialog(String title,String message) throws IOException{
	   Stage window = new Stage();
		window.setTitle(title);
		AnchorPane root = new AnchorPane();
		Label label = new Label();
		label.setText(message);
		root.getChildren().add(label);
		Scene scene = new Scene(root);
		window.setScene(scene);
		//ʹ��showAndWait()�ȴ���������ڣ������������main�е��Ǹ����ڲ�����Ӧ
		window.showAndWait();
		return window;
   }
   
	 
}