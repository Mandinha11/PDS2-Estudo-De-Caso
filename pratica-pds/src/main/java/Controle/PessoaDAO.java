package Controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Pessoa;

public class PessoaDAO {

	public ArrayList<Pessoa> listar() {

		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();

		ArrayList<Pessoa> pessoas = new ArrayList();

		String query = "SELECT * FROM funcionario";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idFuncionario = rs.getInt("id_funcionario");
				String primeiroNome = rs.getString("primeiro_nome");

				Pessoa p = new Pessoa();
				p.setIdFuncionario(idFuncionario);
				p.setPrimeiroNome(primeiroNome);

				pessoas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		c.fecharConexao();
		return pessoas;

	}

	public boolean inserir(Pessoa p) {
		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();

		String query = "INSERT INTO funcionario (id_funcionario, primeiro_nome) VALUES (?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, p.getIdFuncionario());
			ps.setString(2, p.getPrimeiroNome());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean excluir(Pessoa p) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "DELETE FROM funcionario WHERE id_funcionario = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getIdFuncionario());
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	public boolean atualizar(Pessoa p) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "UPDATE funcionario SET primeiro_nome = ? WHERE  id_funcionario = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, p.getPrimeiroNome());
			ps.setInt(1, p.getIdFuncionario());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

}
