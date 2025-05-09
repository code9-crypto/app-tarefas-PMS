package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;

public class ViewController implements Initializable {
	
	@FXML
	private ComboBox<String> cmb_programas;
	
	@FXML
	private Button btn_extrair;
	
	@FXML
	private ProgressBar prg_carrega;
	
	@FXML
	private Label lbl_msg;
	
	double tmp = 0.0; //variável para fazer o incremento na progress bar
	
	
	
	@FXML
	private void onBtExtrair() {
		btn_extrair.setDisable(true);
		lbl_msg.setText("COPIANDO");
		tmp = 0.0;
		//Pegando o nome do arquivo da combobox
		String arquivo = cmb_programas.getValue();
		
		//Instanciando o processBuilder para executar o comando copy
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "copy", "\\\\dados\\seplan\\detic__publico\\Willian_Rocha\\"+arquivo, "C:\\");
		
		//Copiando para a raiz do sistema, extraindo e depois apagando o arquivo zip
		try {
			//Este if é apenas para o app de padronização, pois é muito grande
			if( arquivo.equals("apps_para_padronizacao_V3.zip") ) {
				Process process = processBuilder.start();			
				// Verificar se o processo ainda está ativo
	            new Thread(() -> {	            	
	            	while (process.isAlive()) {
						tmp = tmp + 0.06; 
					    try {
					        Thread.sleep(1000); // Espera de 1 segundo antes de verificar novamente
					        prg_carrega.setProgress(tmp);					        
					    } catch (InterruptedException e) {
					        e.printStackTrace();
					    }				    
					}
	            	
	            	//Extraindo e depois apagando a pasta zip
	                try {
	                	//Este Platform.runLater() é usado para executar outra função dentro da mesma thread, a fim de ter mudança na tela
	                	Platform.runLater(() -> {
	                		lbl_msg.setText("EXTRAINDO E APAGANDO O .ZIP");
	                	});
						extract(arquivo);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }).start();
			}else {
				lbl_msg.setText("COPIANDO");
				//Este é para os de mais, porque são pequenos
				Process process = processBuilder.start();			
				// Verificar se o processo ainda está ativo
	            new Thread(() -> {	            	
					while (process.isAlive()) {
						tmp = tmp + 0.4; 
					    try {
					        Thread.sleep(1000); // Espera de 1 segundo antes de verificar novamente
					        prg_carrega.setProgress(tmp);					        
					    } catch (InterruptedException e) {
					        e.printStackTrace();
					    }				    
					}
					
					//Extraindo e depois apagando a pasta zip
	                try {
	                	Platform.runLater(() -> {
	                		lbl_msg.setText("EXTRAINDO E APAGANDO O .ZIP");
	                	});
						extract(arquivo);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }).start();
			}
		}catch(IOException e) {
			e.getMessage();
		}
		
	}
	
	//MÉTODO QUE IRÁ EXTRAIR O CONTEÚDO NA MESMA PASTA  E DEPOIS CHAMA O MÉTODO PARA APAGAR
	private void extract(String arquivo) throws IOException{
		tmp = 0.0;
		//Agora este processBuilder vai ser usado para extrair a pasta
        ProcessBuilder extracDel = new ProcessBuilder("cmd.exe", "/c", "tar", "-xf", "C:\\"+arquivo+"", "-C", "C:\\");
        Process prcExt = extracDel.start();			
		// Verificar se o processo ainda está ativo
        new Thread(() -> {        	
			while (prcExt.isAlive()) {
				tmp = tmp + 0.5;
			    try {
			        Thread.sleep(1000); // Espera de 1 segundo antes de verificar novamente
			        prg_carrega.setProgress(tmp);
			        
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
			
			try {
				Runtime.getRuntime().exec("cmd.exe /c del C:\\"+arquivo+"");
				Thread.sleep(30000);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
        try {
			Thread.sleep(20000);
			Platform.runLater(() -> {
				lbl_msg.setText("FINALIZADO");
				Alerts.showAlert("Concluído", null, "Agora já pode usar o programa", AlertType.INFORMATION);
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        btn_extrair.setDisable(false);
	}
	
	//Este é o método que já inicia assim que a aplicação é iniciada
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Listando os arquivos e em seguida inserindo numa lista
		List<String> programas = new ArrayList<String>();
		File path = new File("\\\\dados\\seplan\\detic__publico\\Willian_Rocha");
		File[] files = path.listFiles(File::isFile);
		for( File file : files ) {			
			if( file.toString().lastIndexOf(".zip") != -1 ) {				
				programas.add(file.getName());
			}
		}		
		
		//Iniciando a aplicação com a combobox já preenchida com os nomes dos programas
		cmb_programas.getItems().addAll(programas);
		cmb_programas.setValue(programas.get(0));
		
	}

}
