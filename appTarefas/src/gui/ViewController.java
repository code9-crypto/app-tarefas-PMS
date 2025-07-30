package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import classes.Tarefas;
import db.DB;
import gui.utils.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewController implements Initializable {
	// Para fazer com que o scene builder veja este ID
	// Deve ser escrito desta forma

	// Formatando data para o padrão Brasil
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Caixas de texto
	@FXML
	private TextField txtTarefa;
	@FXML
	private TextField txtSolicitante;
	@FXML
	private TextField txtDestino;
	@FXML
	private TextField txtConsultaCodigo;
	@FXML
	private TextField txtConsultaNome;
	@FXML
	private TextArea txtConversa;
	@FXML
	private TextField txtTafChat;

	// Botões
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnConsultar;
	@FXML
	private Button btnVerChat;

	// Labels
	@FXML
	private Label lblMensagem;

	// Variáveis da tableView
	@FXML
	private TableView<Tarefas> table;
	@FXML
	private TableColumn<Tarefas, String> colunaTarefa;
	@FXML
	private TableColumn<Tarefas, String> colunaSolicitante;
	@FXML
	private TableColumn<Tarefas, String> colunaDestino;
	@FXML
	private TableColumn<String, String> colunaData;

	// Preparando as variaveis do banco
	Connection conn = null;
	PreparedStatement st = null;
	Statement stt = null;
	ResultSet rs = null;

	public void onBtSalvar() {
		try {
			// Iniciando a conexão
			conn = DB.getConnection();
			// query do insert atrelado a uma variável
			String queryInsert = "insert into tarefas (tf_tarefa, tf_solicitante, tf_destino" + ") values (?,?,?) ";
			// preparando a query para ser executada
			st = conn.prepareStatement(queryInsert);
			// Substuindo as interrogações pelos valores dos campos
			st.setString(1, txtTarefa.getText());
			st.setString(2, txtSolicitante.getText());
			st.setString(3, txtDestino.getText());
			// executando a query e recebendo a quantidade de linhas afetadas
			int linhasAfetadas = st.executeUpdate();
			// caso a quantidade de linhas seja maior que 0, então foi salvo com sucesso
			// caso não, cairá no else
			if (linhasAfetadas > 0) {
				txtTarefa.setText("");
				txtSolicitante.setText("");
				txtDestino.setText("");
				lblMensagem.setText("Tarefa salva com sucesso");
				lblMensagem.setStyle("-fx-text-fill: green");
			} else {
				lblMensagem.setText("Houve um problema na conexão");
				lblMensagem.setStyle("-fx-text-fill: red");
			}
		} catch (SQLException e) {
			lblMensagem.setStyle("-fx-text-fill: red");
			lblMensagem.setText("Houve um problema na conexão");
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public void onBtConsultar() {
		if (!txtConsultaCodigo.getText().equals("")) {
			try {
				conn = DB.getConnection();
				stt = conn.createStatement();
				String querySelect = "select tf_tarefa, tf_solicitante, tf_destino, tf_data from tarefas "
						+ "where tf_tarefa = " + txtConsultaCodigo.getText();
				rs = stt.executeQuery(querySelect);
				// Declarando uma ObsersableList para receber os dados do banco
				// OBS.: para isso foi necessário criar uma classe chamada Tarefas
				ObservableList<Tarefas> listaTarefas = FXCollections.observableArrayList();
				// Fazendo um loop de repetição para pegar todos os valores e adicionar na
				// ObservableList
				while (rs.next()) {
					// Formatando data que vem do banco de dados
					Date data = rs.getDate("tf_data");
					String dataFormatada = sdf.format(data);

					listaTarefas.add(new Tarefas(rs.getString("tf_tarefa"), rs.getString("tf_solicitante"),
							rs.getString("tf_destino"), dataFormatada));
				}
				table.setItems(listaTarefas);
				txtConsultaCodigo.setText("");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Não consegui trazer os dados do bando de dados");
			} finally {
				DB.closeResultSet(rs);
				DB.closeStatement(st);
				DB.closeStatement(stt);
				DB.closeConnection();
			}
		} else if (!txtConsultaNome.getText().equals("")) {
			try {
				conn = DB.getConnection();
				stt = conn.createStatement();
				String querySelect = "select tf_tarefa, tf_solicitante, tf_destino, tf_data from tarefas "
						+ "where tf_solicitante like '%" + txtConsultaNome.getText() + "%'";
				rs = stt.executeQuery(querySelect);
				// Declarando uma ObsersableList para receber os dados do banco
				// OBS.: para isso foi necessário criar uma classe chamada Tarefas
				ObservableList<Tarefas> listaTarefas = FXCollections.observableArrayList();
				// Fazendo um loop de repetição para pegar todos os valores e adicionar na
				// ObservableList
				while (rs.next()) {
					// Formatando data que vem do banco de dados
					Date data = rs.getDate("tf_data");
					String dataFormatada = sdf.format(data);

					listaTarefas.add(new Tarefas(rs.getString("tf_tarefa"), rs.getString("tf_solicitante"),
							rs.getString("tf_destino"), dataFormatada));
				}
				table.setItems(listaTarefas);
				txtConsultaNome.setText("");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Não consegui trazer os dados do bando de dados");
			} finally {
				DB.closeResultSet(rs);
				DB.closeStatement(st);
				DB.closeStatement(stt);
				DB.closeConnection();
			}
		} else {
			Alerts.showAlert("Informação", null, "Preencha um dos campos para fazer a pesquisa", AlertType.INFORMATION);
		}
	}

	public void onBtnVerChat() {
		if( !txtConversa.equals("") ) {
			txtConversa.clear();
		}
		
		String pasta = "C:\\Users\\r0697537\\AppData\\Local\\harmoniTI\\chat";
		String tarefa = txtTafChat.getText().replace("/", "_");
		File path = new File(pasta);
		File[] files = path.listFiles(File::isFile);
		String arquivo = "";
		for( File file : files ) {
			if( file.getName().substring(0,10).equals(tarefa) ) {
				arquivo = file.getPath();
			}
		}
		BufferedReader br = null;
		FileReader fr = null;		
		try {
			fr = new FileReader(arquivo);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				txtConversa.appendText(line+"\n");
				line = br.readLine();							
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// É necessário fazer esses set para fazer a inicialização das colunas na tabela
		colunaTarefa.setCellValueFactory(new PropertyValueFactory<>("Tarefas"));
		colunaSolicitante.setCellValueFactory(new PropertyValueFactory<>("Solicitante"));
		colunaDestino.setCellValueFactory(new PropertyValueFactory<>("Destino"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("Data"));
	}

}
