package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ginasio {
	private int id;
	private String nome;
	private String estado;	
	private String municipio;	
	public static int totalGinasios;


	public Ginasio() {
		this.nome = "";
		Ginasio.totalGinasios++;
	}

	Ginasio(int idGym, String nome) {
		this();
		this.id = idGym;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String titular) {
		this.nome = titular;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public boolean cadastrarGinasio(String nome, String estado, String municipio) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta
			String sql = "insert into ginasio set nome=?, estado=?, municipio=?;";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setString(1, nome); // Substitui o primeiro parâmetro da consulta pelo nome informado
			ps.setString(2, estado); // Substitui o segundo parâmetro da consulta pelo estado informado
			ps.setString(3, municipio); // Substitui o segundo parâmetro da consulta pelo município informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar Ginásio: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarGinasio(int id) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from ginasio where id=?";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id); // Substitui o primeiro parâmetro da consulta pelo nome informado
			
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Ginasio não cadastrado!");
				return false; // Atleta não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.nome = rs.getString("nome");
					this.estado = rs.getString("estado");
					this.municipio = rs.getString("municipio");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o Ginásio: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarGinasio(int id, String nome, String estado, String municipio) {
		if (!consultarGinasio(id))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "update ginasio set nome=?, estado=?, municipio=? where id=?";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setString(1, nome);
				ps.setString(2, estado);
				ps.setString(3, municipio);
				ps.setInt(4, id);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Ginásio: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}


}
