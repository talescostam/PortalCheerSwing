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

import br.senac.rj.banco.modelo.Atleta;

public class JanelaAtletas {
	public static JFrame criarJanelaAtletas() {
		// Define a janela
		JFrame janelaAtletas = new JFrame("Atualização de Atletas"); // Janela Normal
		janelaAtletas.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaAtletas.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAtletas.setSize(600, 350); // Define tamanho da janela
		
		ConfiguracaoLogo.definirIcone(janelaAtletas);
		
		// Define o layout da janela
		Container caixa = janelaAtletas.getContentPane();
		caixa.setLayout(null);
		
		// Define os labels dos campos
		JLabel labelId = new JLabel("Id: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelIdade = new JLabel("Idade: ");
		JLabel labelAltura = new JLabel("Altura: ");
		JLabel labelGinasio = new JLabel("Ginásio: ");
		
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelIdade.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelAltura.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelGinasio.setBounds(50, 200, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextIdade = new JTextField();
		JTextField jTextAltura = new JTextField();
		JTextField jTextGinasio = new JTextField();
		
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextIdade.setEnabled(false);
		jTextAltura.setEnabled(false);
		jTextGinasio.setEnabled(false);
		
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 160, 20);
		jTextIdade.setBounds(180, 120, 50, 20);
		jTextAltura.setBounds(180, 160, 50, 20);
		jTextGinasio.setBounds(180, 200, 350, 20);
		
		// Adiciona os rótulos e os input box na janela
		janelaAtletas.add(labelId);
		janelaAtletas.add(labelNome);
		janelaAtletas.add(labelIdade);
		janelaAtletas.add(labelAltura);
		janelaAtletas.add(labelGinasio);
		janelaAtletas.add(jTextId);
		janelaAtletas.add(jTextNome);
		janelaAtletas.add(jTextIdade);
		janelaAtletas.add(jTextAltura);
		janelaAtletas.add(jTextGinasio);
		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(240, 40, 100, 20);
		janelaAtletas.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 250, 100, 20);
		botaoGravar.setEnabled(false);
		janelaAtletas.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(240, 250, 100, 20);
		janelaAtletas.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(430, 250, 100, 20);
		botaoExcluir.setEnabled(false);
		janelaAtletas.add(botaoExcluir);


		// Define objeto atleta para pesquisar no banco de dados
		Atleta atleta = new Atleta();
		
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					botaoExcluir.setEnabled(true);
					String nome;
					int idade;
					double altura;
					String nomeGinasio = atleta.consultarNomeGinasio(id);
					if (!atleta.consultarAtleta(id)) {
						nome = "";
						idade = 0;
						altura = 0.0;
						nomeGinasio = "";
					} else {
						nome = atleta.getNome();
						idade = atleta.getIdade();
						altura = atleta.getAltura();
					}
		            jTextNome.setText(nome);
		            jTextIdade.setText(String.valueOf(idade));
		            jTextAltura.setText(String.valueOf(altura));
		            jTextGinasio.setText(nomeGinasio);
					
					jTextId.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					jTextAltura.setEnabled(true);
					jTextIdade.setEnabled(true);
					jTextGinasio.setEnabled(true);
					jTextNome.requestFocus();
					jTextIdade.requestFocus();
					jTextAltura.requestFocus();
					jTextGinasio.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaAtletas,
							"Preencha o campo id corretamente!!");
				}
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaAtletas, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText();
					int idade = Integer.parseInt(jTextIdade.getText());
					double altura = Double.parseDouble(jTextAltura.getText());
					int ginasio = Integer.parseInt(jTextGinasio.getText());

					
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaAtletas, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else {
						if (!atleta.consultarAtleta(id)) {
							if (!atleta.cadastrarAtleta(id, nome, idade, altura, ginasio))
								JOptionPane.showMessageDialog(janelaAtletas, "Erro na inclusão do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaAtletas, "Inclusão realizada!");
						} else {
							if (!atleta.atualizarAtleta(id, nome, idade, altura, ginasio))
								JOptionPane.showMessageDialog(janelaAtletas, "Erro na atualização do Atleta!");
							else
								JOptionPane.showMessageDialog(janelaAtletas, "Alteração realizada!");
						}

					}

				}
			}
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextIdade.setText(""); // Limpar campo
				jTextAltura.setText(""); // Limpar campo
				jTextGinasio.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextIdade.setEnabled(false);
				jTextAltura.setEnabled(false);
				jTextGinasio.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextId.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int id = Integer.parseInt(jTextId.getText());

		            int resposta = JOptionPane.showConfirmDialog(janelaAtletas, "Deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
		            if (resposta == JOptionPane.YES_OPTION) {
		                if (atleta.consultarAtleta(id)) {
		                    if (atleta.excluirAtleta(id)) {
		                        JOptionPane.showMessageDialog(janelaAtletas, "Registro excluído com sucesso!");
		                        // Limpar os campos ou fazer outras ações após a exclusão
		                    } else {
		                        JOptionPane.showMessageDialog(janelaAtletas, "Erro ao excluir o registro!");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(janelaAtletas, "Registro não encontrado!");
		                }
		            }
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaAtletas, "Erro ao excluir o registro!");
		        }
		    }
		});

		return janelaAtletas;
	}
}
