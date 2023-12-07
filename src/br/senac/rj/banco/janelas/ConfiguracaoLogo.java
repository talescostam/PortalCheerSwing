package br.senac.rj.banco.janelas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ConfiguracaoLogo {

    public static ImageIcon obterIcone() {
        return new ImageIcon(ConfiguracaoLogo.class.getResource("/images/logo.png"));
    }

    public static void definirIcone(JFrame janela) {
        ImageIcon icon = obterIcone();
        if (icon != null) {
            janela.setIconImage(icon.getImage());
        }
    }
}
