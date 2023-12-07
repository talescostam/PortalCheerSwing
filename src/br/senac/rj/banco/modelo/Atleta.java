package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Atleta {
	private int id;
	private String nome;
	private int idade;	
	protected double altura;
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
	
	public boolean cadastrarAtleta(int id, String nome, int idade, double altura) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta
			String sql = "insert into atleta set id=?, nome=?, idade=?, altura=?;";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id); // Substitui o primeiro parâmetro da consulta pelo id informado
			ps.setString(2, nome); // Substitui o primeiro parâmetro da consulta pelo nome informado
			ps.setInt(3, idade); // Substitui o segundo parâmetro da consulta pela idade informada
			ps.setDouble(4, altura); // Substitui o terceiro parâmetro da consulta pela altura informada
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
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from atleta where id=?";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id); // Substitui o primeiro parâmetro da consulta pelo id informado
			
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Atleta não cadastrado!");
				return false; // Atleta não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
//					this.id = rs.getInt("id");
					this.nome = rs.getString("nome");
					this.idade = rs.getInt("idade");
					this.altura = rs.getDouble("altura");
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

	public boolean atualizarAtleta(int id, String nome, int idade, double altura) {
		if (!consultarAtleta(id))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "update atleta set nome=?, idade=?, altura=? where id=?";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setString(1, nome);
				ps.setInt(2, idade);
				ps.setDouble(3, altura);
				ps.setInt(4, id);
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

}
