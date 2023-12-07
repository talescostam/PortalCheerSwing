package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Atleta {
	private int id;
	private String nome;
	private int idade;	
	private double altura;
	private int ginasio;
	public static int totalAtletas;


	public Atleta() {
		this.nome = "";
		Atleta.totalAtletas++;
	}

	Atleta(int idAtleta, String nome) {
		this();
		this.id = idAtleta;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}

	public String idade() {
		return nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String titular) {
		this.nome = titular;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public int getGinasio() {
		return ginasio;
	}

	public void setGinasio(int ginasio) {
		this.ginasio = ginasio;
	}

	public boolean cadastrarAtleta(int id, String nome, int idade, double altura, int ginasio) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta
			String sql = "insert into atleta set id=?, nome=?, idade=?, altura=?, ginasio_id=?;";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id); // Substitui o primeiro parâmetro da consulta pelo id informado
			ps.setString(2, nome); // Substitui o primeiro parâmetro da consulta pelo nome informado
			ps.setInt(3, idade); // Substitui o segundo parâmetro da consulta pela idade informada
			ps.setDouble(4, altura); // Substitui o terceiro parâmetro da consulta pela altura informada
			ps.setInt(5, ginasio); // Substitui o terceiro parâmetro da consulta pelo ginásio informada
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar Atleta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarAtleta(int id) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();
	        String sql = "SELECT a.*, g.nome AS nome_ginasio " +
	                     "FROM atleta a " +
	                     "JOIN ginasio g ON a.ginasio_id = g.id " +
	                     "WHERE a.id=?";
	        
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        ResultSet rs = ps.executeQuery();
	        if (!rs.isBeforeFirst()) {
	            System.out.println("Atleta não cadastrado!");
	            return false;
	        } else {
	            while (rs.next()) {
	                this.nome = rs.getString("nome");
	                this.idade = rs.getInt("idade");
	                this.altura = rs.getDouble("altura");
	                this.ginasio = rs.getInt("ginasio_id");
	            }
	            return true;
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao consultar o Atleta: " + erro.toString());
	        return false;
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	}
	
	public String consultarNomeGinasio(int id) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();
	        String sql = "SELECT g.nome AS nome_ginasio " +
	                     "FROM atleta a " +
	                     "JOIN ginasio g ON a.ginasio_id = g.id " +
	                     "WHERE a.id=?";
	        
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        ResultSet rs = ps.executeQuery();
	        if (!rs.isBeforeFirst()) {
	            System.out.println("Atleta não cadastrado!");
	            return ""; // Retorna uma string vazia se o atleta não for encontrado
	        } else {
	            if (rs.next()) {
	                return rs.getString("nome_ginasio"); // Retorna o nome do ginásio encontrado
	            }
	            return ""; // Retorna uma string vazia se o atleta não for encontrado
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao consultar o Atleta: " + erro.toString());
	        return ""; // Retorna uma string vazia em caso de erro
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	}



	public boolean atualizarAtleta(int id, String nome, int idade, double altura, int ginasio) {
		if (!consultarAtleta(id))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "update atleta set nome=?, idade=?, altura=?, ginasio_id=? where id=?";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setString(1, nome);
				ps.setInt(2, idade);
				ps.setDouble(3, altura);
				ps.setInt(4, id);
				ps.setInt(5, ginasio);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Atleta: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	public boolean excluirAtleta(int id) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();

	        String sql = "DELETE FROM atleta WHERE id=?";

	        PreparedStatement ps = conexao.prepareStatement(sql);

	        ps.setInt(1, id);

	        int totalRegistrosAfetados = ps.executeUpdate();
	        if (totalRegistrosAfetados == 0) {
	            System.out.println("Não foi possível excluir o registro!");
	            return false;
	        }
	        System.out.println("Registro excluído com sucesso!");
	        return true;
	    } catch (SQLException erro) {
	        System.out.println("Erro ao excluir o registro do Atleta: " + erro.toString());
	        return false;
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	}
	
	public static ArrayList<String> obterNomesGinasiosDoBanco() {
        ArrayList<String> nomesGinasios = new ArrayList<>();
        Connection conexao = null;
        try {
        	conexao = Conexao.conectaBanco();
        	
            String sql = "SELECT nome FROM ginasio"; 

            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nomeGinasio = rs.getString("nome");
                nomesGinasios.add(nomeGinasio);
            }
        } catch (SQLException erro) {
	        System.out.println("Erro ao consultar o Atleta: " + erro.toString());
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }

        return nomesGinasios;
    }
	
	public int consultarIdGinasio(String nomeGinasio) {
        Connection conexao = null;
        int idGinasio = -1; // Valor padrão para indicar que não encontrou o ginásio

        try {
            conexao = Conexao.conectaBanco();

            String sql = "SELECT id FROM ginasio WHERE nome = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nomeGinasio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idGinasio = rs.getInt("id");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar o ID do ginásio: " + erro.toString());
        } finally {
            Conexao.fechaConexao(conexao);
        }

        return idGinasio;
    }

}
