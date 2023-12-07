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

import br.senac.rj.banco.modelo.Ginasio;

public class JanelaGinasios {
	public static JFrame criarJanelaGinasios() {
		// Define a janela
		JFrame janelaGinasios = new JFrame("Atualização de Ginasios"); // Janela Normal
		janelaGinasios.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaGinasios.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaGinasios.setSize(600, 300); // Define tamanho da janela
		
		// Define o layout da janela
		Container caixa = janelaGinasios.getContentPane();
		caixa.setLayout(null);
		
		// Define os labels dos campos
		JLabel labelId = new JLabel("Id: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelEstado = new JLabel("Estado: ");
		JLabel labelMunicipio = new JLabel("Municipio: ");
		
	
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelEstado.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelMunicipio.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextEstado = new JTextField();
		JTextField jTextMunicipio = new JTextField();
		
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextEstado.setEnabled(false);
		jTextMunicipio.setEnabled(false);
		
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 350, 20);
		jTextEstado.setBounds(180, 120, 170, 20);
		jTextMunicipio.setBounds(180, 160, 170, 20);
		
		// Adiciona os rótulos e os input box na janela
		janelaGinasios.add(labelId);
		janelaGinasios.add(labelNome);
		janelaGinasios.add(labelEstado);
		janelaGinasios.add(labelMunicipio);
		janelaGinasios.add(jTextId);
		janelaGinasios.add(jTextNome);
		janelaGinasios.add(jTextEstado);
		janelaGinasios.add(jTextMunicipio);
		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaGinasios.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaGinasios.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(200, 200, 100, 20);
		janelaGinasios.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(350, 200, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaGinasios.add(botaoExcluir);
		
		// Define objeto atleta para pesquisar no banco de dados
		Ginasio ginasio = new Ginasio();
		
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					botaoExcluir.setEnabled(true);
					String nome;
					String estado;
					String municipio;
					if (!ginasio.consultarGinasio(id)) {
						nome = "";
						estado = "";
						municipio = "";
					} else {
						nome = ginasio.getNome();
						estado = ginasio.getEstado();
						municipio = ginasio.getMunicipio();
					}
		            jTextNome.setText(nome);
		            jTextEstado.setText(estado);
		            jTextMunicipio.setText(municipio);
					
					jTextId.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					jTextMunicipio.setEnabled(true);
					jTextEstado.setEnabled(true);
					jTextNome.requestFocus();
					jTextEstado.requestFocus();
					jTextMunicipio.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaGinasios,
							"Preencha o campo id corretamente!!");
				}
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaGinasios, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText();
					String estado = jTextEstado.getText();
					String municipio = jTextMunicipio.getText();
					
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaGinasios, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else {
						if (!ginasio.consultarGinasio(id)) {
							if (!ginasio.cadastrarGinasio(id, nome, estado, municipio))
								JOptionPane.showMessageDialog(janelaGinasios, "Erro na inclusão do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaGinasios, "Inclusão realizada!");
						} else {
							if (!ginasio.atualizarGinasio(id, nome, estado, municipio))
								JOptionPane.showMessageDialog(janelaGinasios, "Erro na atualização do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaGinasios, "Alteração realizada!");
						}

					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextEstado.setText(""); // Limpar campo
				jTextMunicipio.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextEstado.setEnabled(false);
				jTextMunicipio.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextId.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int id = Integer.parseInt(jTextId.getText());
		            
		            int resposta = JOptionPane.showConfirmDialog(janelaGinasios, "Deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
		            if (resposta == JOptionPane.YES_OPTION) {
		                if (ginasio.consultarGinasio(id)) {
		                    if (ginasio.excluirGinasio(id)) {
		                        JOptionPane.showMessageDialog(janelaGinasios, "Registro excluído com sucesso!");
		                        // Limpar os campos ou fazer outras ações após a exclusão
		                    } else {
		                        JOptionPane.showMessageDialog(janelaGinasios, "Erro ao excluir o registro!");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(janelaGinasios, "Registro não encontrado!");
		                }
		            }
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaGinasios, "Erro ao excluir o registro!");
		        }
		    }
		});
		
		return janelaGinasios;
	}
	
}
