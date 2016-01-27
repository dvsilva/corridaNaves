package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import control.CtrlChegada;
import control.NaveThread;

/** 
 * @author Felipe e Danyllo
 */
public class Resultado extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 6316657168636440912L;

	private JButton btnReeniciar;
	private JButton btnNovoJogo;
	private JButton btnCancel;
	private JLabel lblFundo; 
	private ImageIcon fundo;

	// DECLARAÇÃO DO ATRIBUTO QUE REFERENCIA O CTRLCHEGADA
	private CtrlChegada ctrl;
	
	// DECLARAÇÃO DOS ATRIBUTOS QUE PERMITEM COLOCAR SE A NAVE ESCOLHIDA GANHOU
	// E O SCORE DO JOGADOR
	private JLabel lblScore;
	private JLabel lblMensagem;

	public Resultado(CtrlChegada ctrl, Corrida corrida) {
		super();

		//RECEBO ALGUMAS INFORMAÇÕES DO CTRLCHEGADA E GUARDO NAS RESPECTIVAS VARIÁVEIS
		this.ctrl = ctrl;

		// COLOCAÇÃO DO LOGOTIPO NA JANELA
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/icon.png"));

		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		getContentPane().setLayout(null);
		this.setSize(950, 500);
		this.setLocation(200, 100);
		this.setResizable(false);
		getContentPane().setBackground(Color.white);
		this.setTitle("Ufo Race Championship 2014");

		// AO CLICAR NO BOTÃO FECHAR O PROGRAMA É ENCERRADO
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// COLOCA E INSTANCIA A IMAGEM DA PISTA
		fundo = new ImageIcon("./src/imagens/final.png");
		this.lblFundo = new JLabel(fundo);
		
		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblFundo.setBounds(0, 0, 950, 500);
		this.add(lblFundo);
		this.lblFundo.setBackground(Color.BLACK);

		this.btnReeniciar = new JButton("Continuar Jogando");
		this.btnNovoJogo = new JButton("Novo Jogo");
		this.btnCancel = new JButton("Fechar");

		// POSIONADO OS BOTÕES
		this.btnReeniciar.setBounds(646, 411, 150, 50);
		this.btnNovoJogo.setBounds(728, 350, 130, 50);
		this.btnCancel.setBounds(806, 411, 120, 50);

		// COLOCA A LABEL QUE DIZ SE A NAVE ESCOLHIDA GANHOU OU NÃO
		this.lblMensagem = new JLabel();
		this.lblMensagem.setBounds(10, 0, 800, 60);
		lblMensagem.setText(this.ctrl.getMensagem());  
		lblMensagem.setFont(new Font("Arial",Font.BOLD,30));
		this.lblFundo.add(lblMensagem);
		
		// COLOCA A LABEL QUE MOSTRA O SCORE DO JOGO
		this.lblScore = new JLabel();
		this.lblScore.setBounds(10, 40, 400, 60);
		lblScore.setText("Score: " + this.ctrl.getScore());  
		lblScore.setFont(new Font("Arial",Font.BOLD,30));
		this.lblFundo.add(lblScore);

		// COLOCA AS NAVES NA TELA SEGUNDO SUAS COLOCAÇÕES
		ArrayList<NaveThread> navesConcluintes = ctrl.getNavesConcluintes();
		criarPrimeiroColocado(navesConcluintes.get(CtrlChegada.PRIMEIRO_COLOCADO));
		criarSegundoColocado(navesConcluintes.get(CtrlChegada.SEGUNDO_COLOCADO));
		criarTerceiroColocado(navesConcluintes.get(CtrlChegada.TERCEIRO_COLOCADO));

		this.lblFundo.add(btnReeniciar);
		this.lblFundo.add(btnCancel);
		this.lblFundo.add(btnNovoJogo);
		this.setVisible(true);

		btnReeniciar.addActionListener(this);
		btnNovoJogo.addActionListener(this);
		btnCancel.addActionListener(this);

		this.repaint();
	}

	private void criarPrimeiroColocado(NaveThread naveThread) {
		JLabel lblNave1 = new JLabel(naveThread.getImg());
		lblNave1.setBounds(260, 160, 256, 141);
		lblNave1.setVisible(true);
		this.lblFundo.add(lblNave1);
	}
	
	private void criarSegundoColocado(NaveThread naveThread) {
		JLabel lblNave3 = new JLabel(naveThread.getImg());
		lblNave3.setBounds(100, 220, 256, 141);
		lblNave3.setVisible(true);
		this.lblFundo.add(lblNave3);
	}
	
	private void criarTerceiroColocado(NaveThread naveThread) {
		JLabel lblNave3 = new JLabel(naveThread.getImg());
		lblNave3.setBounds(410, 260, 256, 141);
		lblNave3.setVisible(true);
		this.lblFundo.add(lblNave3);
	}

	//PROGRAMANDO A AÇÃO DOS BOTÕES
	@Override
	
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.btnReeniciar) {
			this.setVisible(false);
			this.ctrl.setNavesConcluintes(new ArrayList<NaveThread>());
			this.ctrl.novaCorrida();
		}
		if (dispara.getSource() == this.btnCancel) {
			System.exit(0);
		}
		if (dispara.getSource() == this.btnNovoJogo){
			int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?\nAo iniciar um novo jogo todo o progresso será perdido!", "Alerta de progresso", JOptionPane.YES_NO_OPTION);  
	        if (resp == JOptionPane.YES_NO_OPTION) {  
	        	new JanelaPrincipal();
	        	this.ctrl.setNavesConcluintes(new ArrayList<NaveThread>());
				this.ctrl.setScore(0);
				this.setVisible(false);  
	        }  
		}
	}
	
	
}