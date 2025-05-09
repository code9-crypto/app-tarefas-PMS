package gui;

import java.io.IOException;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class ViewController {
		
	@FXML
	private Button btInstalaOracle;
	
	@FXML
	private Button btInstalaTribus;
	
	@FXML
	private Button btInstalaCrystal;
	
	@FXML
	private Button btCorrecaoTribus;
	
	
	//botões sigsantos
	@FXML
	private Button btOracleSig;
	
	@FXML
	private Button btCrruntimeSig;
	
	@FXML
	private Button btSetupTecomSig;
	
	@FXML
	private Button btRegistroSig;
	
	@FXML
	private Button btSetupSig;
	
	@FXML
	private Button btCopiarIcone;
	
	
	//Método para instalação
	
	//TRIBUS
	
	@FXML
	private void onBtInstalarOracle() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__softwares\\banco_de_dados\\oracle\\cliente_oracle_11g_32bits_automatizado\\oracle11g_administrador.exe");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarTribus() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__softwares\\sistemas_pms\\tribus\\tribus-novo\\win_7\\setup.exe");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarCrystal() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__softwares\\relatorio\\crystal_report\\crystal_report-v9.0\\Crystal9Redist.msi");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtCorrecaoTribus() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__interacao-usuario\\Ferramentas\\arquivos_relacionados\\correcao_tribus\\rev7\\CorrecaoTribus.exe");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	//SIGSANTOS
	@FXML
	private void onBtInstalarOracleSig() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__softwares\\banco_de_dados\\oracle\\cliente_oracle_11g_32bits_automatizado\\oracle11g_administrador.exe");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarCrruntime() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\dados\\seplan\\detic_coengi_seserc__softwares\\relatorio\\crystal_report\\crystal_report-v13.0\\CRRuntime_32bit_13_0_13.msi");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarSetupTecom() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\pmsantos1\\SigSantos\\setupTecom.exe");
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarRegistrar(){
		try {
			Runtime.getRuntime().exec("cmd.exe /c copy \\\\pmsantos1\\SigSantos\\ewdecimalbox.ocx C:\\");
			Thread.sleep(5000);
			Runtime.getRuntime().exec("cmd.exe /c start regsvr32 C:\\ewdecimalbox.ocx");
		}catch(IOException | InterruptedException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtInstalarSetup() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start \\\\pmsantos1\\SigSantos\\novaversao11g\\setup.exe");
			
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	private void onBtCopiarIcone() {
		try {
			Runtime.getRuntime().exec("cmd.exe /c cd /");
			Runtime.getRuntime().exec("cmd.exe /c copy %userprofile%\\AppData\\Roaming\\Microsoft\\Windows\\\"Start Menu\"\\Programs\\\"PMS - Prefeitura Municipal de Santos\\SIGSantos.Net\"\\SIGSantos.Net.appref-ms C:\\Users\\Public\\Desktop");
			Alerts.showAlert("Sucesso", null, "Ícone copiado para área de trabalho pública com sucesso!", AlertType.CONFIRMATION);
		}catch(IOException e) {
			Alerts.showAlert("Falha", null, "Houve um problema: " + e.getMessage(), AlertType.ERROR);
		}
	}

}
