package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Campeonato {
	private int id;
	private String nome;
	private String categoria;
	private String divisao;
	public static int totalCampeonatos;


	public Campeonato() {
		this.nome = "";
		Campeonato.totalCampeonatos++;
	}

	Campeonato(int idCampeonato, String nome) {
		this();
		this.id = idCampeonato;
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
		return categoria;
	}

	public void setEstado(String estado) {
		this.categoria = estado;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}
	
	public boolean cadastrarCampeonato(int id, String nome, String categoria, String divisao) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			
			// Define a consulta
			String sql = "insert into campeonato set id=?, nome=?, categoria=?, divisao=?;";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id);
			ps.setString(2, nome);
			ps.setString(3, categoria);
			ps.setString(4, divisao);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar Campeonato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarCampeonato(int id) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from campeonato where id=?";
			
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			// Define os parâmetros da consulta
			ps.setInt(1, id); // Substitui o primeiro parâmetro da consulta pelo nome informado
			
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				System.out.println("Campeonato não cadastrado!");
				return false; // Atleta não cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.nome = rs.getString("nome");
					this.categoria = rs.getString("categoria");
					this.divisao = rs.getString("divisao");					
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o Campeonato: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarCampeonato(int id, String nome, String categoria, String divisao) {
		if (!consultarCampeonato(id))
			return false;
		else {
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaBanco();
				
				// Define a consulta
				String sql = "update campeonato set id =?, nome=?, categoria=?, divisao=? where id=?";
				
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				// Define os parâmetros da atualização
				ps.setInt(1, id);
				ps.setString(2, nome);
				ps.setString(3, categoria);
				ps.setString(4, divisao);
				ps.setInt(5, id);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Campeonato: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

}
