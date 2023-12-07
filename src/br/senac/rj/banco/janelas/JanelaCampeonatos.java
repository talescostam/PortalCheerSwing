package br.senac.rj.banco.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Campeonato;

public class JanelaCampeonatos {
	public static JFrame criarJanelaCampeonatos() {
		// Define a janela
		JFrame janelaCampeonatos = new JFrame("Atualização de Campeonatos"); // Janela Normal
		janelaCampeonatos.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaCampeonatos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCampeonatos.setSize(600, 300); // Define tamanho da janela
		
		ConfiguracaoLogo.definirIcone(janelaCampeonatos);
		
		// Define o layout da janela
		Container caixa = janelaCampeonatos.getContentPane();
		caixa.setLayout(null);
		
		// Define os labels dos campos
		JLabel labelId = new JLabel("Id: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelCategoria = new JLabel("Categoria: ");
		JLabel labelDivisao = new JLabel("Divisão: ");
		
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelCategoria.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelDivisao.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextCategoria = new JTextField();
		JTextField jTextDivisao = new JTextField();
		
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextCategoria.setEnabled(false);
		jTextDivisao.setEnabled(false);
		
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 350, 20);
		jTextCategoria.setBounds(180, 120, 170, 20);
		jTextDivisao.setBounds(180, 160, 170, 20);
		
		// Adiciona os rótulos e os input box na janela
		janelaCampeonatos.add(labelId);
		janelaCampeonatos.add(labelNome);
		janelaCampeonatos.add(labelCategoria);
		janelaCampeonatos.add(labelDivisao);
		janelaCampeonatos.add(jTextId);
		janelaCampeonatos.add(jTextNome);
		janelaCampeonatos.add(jTextCategoria);
		janelaCampeonatos.add(jTextDivisao);
		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaCampeonatos.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCampeonatos.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(200, 200, 100, 20);
		janelaCampeonatos.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(350, 200, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaCampeonatos.add(botaoExcluir);
		
		// Define objeto atleta para pesquisar no banco de dados
		Campeonato campeonato = new Campeonato();
		
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					botaoExcluir.setEnabled(true);
					String nome;
					String categoria;
					String divisao;
					if (!campeonato.consultarCampeonato(id)) {
						nome = "";
						categoria = "";
						divisao = "";
					} else {
						nome = campeonato.getNome();
						categoria = campeonato.getCategoria();
						divisao = campeonato.getDivisao();
					}
		            jTextNome.setText(nome);
		            jTextCategoria.setText(categoria);
		            jTextDivisao.setText(divisao);
					
					jTextId.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					jTextDivisao.setEnabled(true);
					jTextCategoria.setEnabled(true);
					jTextNome.requestFocus();
					jTextCategoria.requestFocus();
					jTextDivisao.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCampeonatos,
							"Preencha o campo id corretamente!!");
				}
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaCampeonatos, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText();
					String categoria = jTextNome.getText();
					String divisao = jTextNome.getText();
					
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaCampeonatos, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else {
						if (!campeonato.consultarCampeonato(id)) {
							if (!campeonato.cadastrarCampeonato(id, nome, categoria, divisao))
								JOptionPane.showMessageDialog(janelaCampeonatos, "Erro na inclusão do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaCampeonatos, "Inclusão realizada!");
						} else {
							if (!campeonato.atualizarCampeonato(id, nome, categoria, divisao))
								JOptionPane.showMessageDialog(janelaCampeonatos, "Erro na atualização do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaCampeonatos, "Alteração realizada!");
						}

					}

				}
			}
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextCategoria.setText(""); // Limpar campo
				jTextDivisao.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextCategoria.setEnabled(false);
				jTextDivisao.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextId.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int id = Integer.parseInt(jTextId.getText());

		            int resposta = JOptionPane.showConfirmDialog(janelaCampeonatos, "Deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
		            if (resposta == JOptionPane.YES_OPTION) {
		                if (campeonato.consultarCampeonato(id)) {
		                    if (campeonato.excluirCampeonato(id)) {
		                        JOptionPane.showMessageDialog(janelaCampeonatos, "Registro excluído com sucesso!");
		                    } else {
		                        JOptionPane.showMessageDialog(janelaCampeonatos, "Erro ao excluir o registro!");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(janelaCampeonatos, "Registro não encontrado!");
		                }
		            }
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaCampeonatos, "Erro ao excluir o registro!");
		        }
		    }
		});
		
		return janelaCampeonatos;
	}
}
