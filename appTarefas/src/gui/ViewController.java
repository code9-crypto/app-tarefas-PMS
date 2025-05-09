package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
	private TextField txtPesquisarEndereco;

	// Botões
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnConsultar;
	@FXML
	private Button btnPesquisaRegiao;

	// Labels
	@FXML
	private Label lblMensagem;
	@FXML
	private Label lblRegiao;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// É necessário fazer esses set para fazer a inicialização das colunas na tabela
		colunaTarefa.setCellValueFactory(new PropertyValueFactory<>("Tarefas"));
		colunaSolicitante.setCellValueFactory(new PropertyValueFactory<>("Solicitante"));
		colunaDestino.setCellValueFactory(new PropertyValueFactory<>("Destino"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("Data"));
	}

	public void onBtPesquisaRegiao() {
		String[] vectR4 = new String[] { 
				"Rua João Pessoa, 246",
				"Rua João Pessoa, 266",
				"Rua João Pessoa, 246/266",
				"Rua Amador Bueno, 249",
				"Rua Amador Bueno, 225",
				"Rua Amador Bueno, 446", "Rua Amador Bueno, 237",
				"R. Vereador Freitas Guimarães, 13",
				"Rua Sete de Setembro, 34",
				"Rua Sete de Setembro, 22",
				"Pça Corrêa de Melo, 42",
				"R. Uruguai, 37",
				"Rua da Constituição, 395",
				"Pça José; Bonifácio, 50",
				"Rua General Camara, 245",
				"Rua Andrade Neves, 8",
				"Av. Cons. Nebias, 199 inclui n&#xBA; 201"
				};

		String[] vectR5 = new String[] { "Av. Jovino de Mello, 919" };

		String[] vectR6 = new String[] { "Av. Cons. Nébias, 737", "Av. Cons. Nébias, 739",
				"Av. Cons. Rodrigues Alves, 197", "Rua 28 de Setembro, 201", "Av. Senador Dantas, 410", };
		
		if( !txtPesquisarEndereco.getText().equals("") ) {			
			for (String reg : vectR4) {
				if( txtPesquisarEndereco.getText().equals(reg) ) {
					lblRegiao.setText("Região 4");
					txtPesquisarEndereco.setText("");
				}
			}			
		}
		
		if( !txtPesquisarEndereco.getText().equals("") ) {			
			for (String reg : vectR5) {
				if( txtPesquisarEndereco.getText().equals(reg) ) {
					lblRegiao.setText("Região 5");
					txtPesquisarEndereco.setText("");
				}
			}
		}
		
		if( !txtPesquisarEndereco.getText().equals("") ) {
			for (String reg : vectR6) {
				if( txtPesquisarEndereco.getText().equals(reg) ) {
					lblRegiao.setText("Região 6");
					txtPesquisarEndereco.setText("");
				}
			}
		}

	}
}
