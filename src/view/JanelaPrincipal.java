package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** 
 * @author Felipe e Danyllo
 */

public class JanelaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 24228221340327553L;
	
	private JButton btnJogar;
	private JButton btnCancel;
	private JLabel lblFundo;
	private ImageIcon fundo;

	public JanelaPrincipal() {
		super();
		// COLOCAÇÃO DO LOGOTIPO NA JANELA
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/icon.png"));
		
		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		this.setLayout(null);
		this.setSize(1280, 650);
		this.setLocation(50, 50);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		this.setTitle("Ufo Race Championship 2014");
		
		// COLOCA E INSTANCIA A IMAGEM DE FUNDO
		fundo = new ImageIcon("./src/imagens/fundo.png");
		this.lblFundo = new JLabel(fundo);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 1280, 650);
		this.add(lblFundo);
		this.lblFundo.setBackground(Color.BLACK);

		this.btnJogar = new JButton("Jogar");
		this.btnCancel = new JButton("Fechar");
		
		// POSIONADO OS BOTÕES
		this.btnJogar.setBounds(500, 550, 120, 50);
		this.btnCancel.setBounds(640, 550, 120, 50);
		
		//	ADICIONANDO OS BOTÕES A JANELA
		this.lblFundo.add(btnJogar);
		this.lblFundo.add(btnCancel);

		this.setVisible(true);

		btnJogar.addActionListener(this);
		btnCancel.addActionListener(this);

		this.repaint();
	}

	// PROGRAMANDO A AÇÃO DOS BOTÕES
	@Override
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.btnJogar) {
			new Corrida(this);//CRIA A JANELA CORRIDA COM A PISTA E AS NAVES
			this.dispose();//FAZ JANELA PRINCIPAL NÃO FICAR VISIVEL 
		}
		if (dispara.getSource() == this.btnCancel) {
			System.exit(0);//FECHA O PROGRAMA
		}
	}
}