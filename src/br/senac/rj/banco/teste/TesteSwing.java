package br.senac.rj.banco.teste;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.BackgroundPanel;
import br.senac.rj.banco.janelas.HyperLinkInMenuBar;
import br.senac.rj.banco.janelas.JanelaAtletas;
import br.senac.rj.banco.janelas.JanelaCampeonatos;
import br.senac.rj.banco.janelas.JanelaGinasios;

public class TesteSwing {

	public static void apresentarMenu() {
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Portal Cheer"); // Janela Normal
		janelaPrincipal.setTitle("Sistema Portal Cheer");
		janelaPrincipal.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");
				
        // Carrega a imagem que será usada como ícone da janela
        ImageIcon icon = new ImageIcon("src\\images\\logo.png"); // Insira o caminho para o seu ícone
        
        // Define o ícone da janela
        janelaPrincipal.setIconImage(icon.getImage());
        
        // Cria um painel com a imagem de fundo
        BackgroundPanel panel = new BackgroundPanel("src\\images\\banner PC.jpg"); // Insira o caminho para a sua imagem
        
        // Define o layout do painel como nulo para que a imagem cubra todo o conteúdo
        panel.setLayout(null);
        
        // Adiciona o painel ao JFrame
        janelaPrincipal.add(panel);
        
        Font customFont = new Font("Arial", Font.PLAIN, 14); // Altere para a fonte desejada, tamanho 14

        // Define a fonte para toda a aplicação
        UIManager.put("Label.font", customFont);
        UIManager.put("Button.font", customFont);
        UIManager.put("TextField.font", customFont);
        UIManager.put("TextArea.font", customFont);
		
		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		
		// Define e adiciona menu na barra de menu
		JMenu abaAtletas = new JMenu("Atletas");
		menuBar.add(abaAtletas);
		JMenu abaGinasios = new JMenu("Ginásios");
		menuBar.add(abaGinasios);
		JMenu abaCampeonatos = new JMenu("Campeonatos");
		menuBar.add(abaCampeonatos);
		
		JMenu abaSite = new JMenu("Conheça o nosso site");

        JMenuItem menuItem = new JMenuItem("Ir para o site do Portal Cheer");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HyperLinkInMenuBar.abrirLink("https://www.portalcheer.com");
            }
        });     

        abaSite.add(menuItem);
        menuBar.add(abaSite);
		
		// Cria e adiciona um item simples para o menu
		JMenuItem menuAtletas = new JMenuItem("Cadastrar / Atualizar");
		abaAtletas.add(menuAtletas);
		
		JMenuItem menuGinasios = new JMenuItem("Cadastrar / Atualizar");
		abaGinasios.add(menuGinasios);
		
		JMenuItem menuCampeonatos = new JMenuItem("Cadastrar / Atualizar");
		abaCampeonatos.add(menuCampeonatos);
		
		// Criar a janelas
		JFrame janelaAtletas = JanelaAtletas.criarJanelaAtletas();
		JFrame janelaGinasios = JanelaGinasios.criarJanelaGinasios();
		JFrame janelaCampeonatos = JanelaCampeonatos.criarJanelaCampeonatos();
		
		// Adiciona ação para o item do menu
		menuAtletas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaAtletas.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
		
		menuGinasios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaGinasios.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
		
		menuCampeonatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaCampeonatos.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
		
		
	}
	
	
	
	public static void main(String[] args) {
		apresentarMenu();
	}
}
