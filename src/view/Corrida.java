package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import control.CtrlChegada;
import control.NaveThread;

/** 
 * @author Felipe e Danyllo
 */
public class Corrida extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2501862956264709607L;
	
	public JButton btnCorrida;
	private JButton btnVoltar;
	private JButton btnNave1;
	private JButton btnNave2;
	private JButton btnNave3;
	private ImageIcon imgNave1;
	private ImageIcon imgNave2;
	private ImageIcon imgNave3;
	private ImageIcon pista;
	private JanelaPrincipal jPrincipal;
	public JLabel lblPista;
	private JLabel lblScore;
	private JLabel lblNave1;
	private JLabel lblNave2;
	private JLabel lblNave3;
	
    //GUARDO A REFERENCIA PARA O CONTROLADOR DE CHEGADA
	CtrlChegada ctrl = CtrlChegada.getSingleton();

	//ARRAY DE NAVES
	private ArrayList<NaveThread> naves = new ArrayList<NaveThread>();

	public Corrida(JanelaPrincipal jPrincipal) {
		super();
		// GUARDO A REFERENCIA PARA A JANELA PRINCIPAL
		this.jPrincipal = jPrincipal;
		
		// COLOCAÇÃO DO LOGOTIPO NA JANELA
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/imagens/icon.png"));

		// ADICIONANDO ELEMENTOS AO FORMULÁRIO
		this.setLayout(null);
		this.setSize(1280, 650);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		
		this.setTitle("Ufo Race Championship 2014");

		// AO CLICAR NO BOTÃO FECHAR O PROGRAMA É ENCERRADO
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// COLOCA E INSTANCIA A IMAGEM DA PISTA
		this.pista = new ImageIcon("./src/imagens/pista.png");
		this.lblPista = new JLabel(pista);

		// POSICIONANDO OS ELEMENTOS DO FORM
		this.lblPista.setBounds(0, 0, 1280, 650);
		this.add(lblPista);
		this.lblPista.setBackground(Color.BLACK);

		this.btnCorrida = new JButton("Iniciar");
		this.btnVoltar = new JButton("Voltar");
		this.btnNave1 = new JButton("Apostar na Nave 1");
		this.btnNave2 = new JButton("Apostar na Nave 2");
		this.btnNave3 = new JButton("Apostar na Nave 3");

		// POSIONANDO OS BOTÕES
		this.btnCorrida.setBounds(500, 550, 120, 50);
		this.btnVoltar.setBounds(640, 550, 120, 50);
		this.btnNave1.setBounds(200, 100, 140, 30);
		this.btnNave2.setBounds(200, 250, 140, 30);
		this.btnNave3.setBounds(200, 400, 140, 30);
		
		//  COLOCO OS BOTÕES DE APOSTA COM O FUNDO BRANCO
		this.btnNave1.setBackground(Color.WHITE);
		this.btnNave2.setBackground(Color.WHITE);
		this.btnNave3.setBackground(Color.WHITE);

		// INSERINDO AS IMAGENS DAS NAVES
		int i1 = new Random().nextInt(5);
		int i2 = new Random().nextInt(5);
		int i3 = new Random().nextInt(5);
		
		// IMPEDE TERMOS NAVES IGUAIS NO MESMO JOGO
		// (IMPEDE QUE O MESMO INTEIRO SEJA ESCOLHIDO MAIS DE UMA VEZ)
		if (i1 == i2){
			i1 = 6;
		}
		
		if (i2 == i3){
			i2 = 7;
		}
		
		if (i1 == i3){
			i3 = 8;
		}
		
		//ESCOLHE AS IMAGENS
		this.imgNave1 = new ImageIcon("./src/imagens/nave" + i1 + ".png");
		this.imgNave2 = new ImageIcon("./src/imagens/nave" + i2 + ".png");
		this.imgNave3 = new ImageIcon("./src/imagens/nave" + i3 + ".png");
		
		this.lblPista.add(btnCorrida);
		this.btnCorrida.setEnabled(false);
		
		this.lblPista.add(btnVoltar);
		this.lblPista.add(btnNave1);
		this.lblPista.add(btnNave2);
		this.lblPista.add(btnNave3);

		this.setVisible(true);

		this.btnCorrida.addActionListener(this);
		this.btnVoltar.addActionListener(this);
		this.btnNave1.addActionListener(this);
		this.btnNave2.addActionListener(this);
		this.btnNave3.addActionListener(this);
		
		// COLOCAMOS AS IMAGENS DAS TRÊS NAVES PARA VISUALIZAR E APOSTAR EM UMA DELAS
		this.lblNave1 = new JLabel(imgNave1);
		this.lblNave1.setBounds(0, 50, 256, 141);
		this.lblNave1.setVisible(true);
		this.lblPista.add(lblNave1);
	
		this.lblNave2 = new JLabel(imgNave2);
		this.lblNave2.setBounds( 0, 200, 256, 141);
		this.lblNave2.setVisible(true);
		this.lblPista.add(lblNave2);
	
		this.lblNave3 = new JLabel(imgNave3);
		this.lblNave3.setBounds(0, 350, 256, 141);
		this.lblNave3.setVisible(true);
		this.lblPista.add(lblNave3);
		
		// COLOCA A LABEL QUE MOSTRA O SCORE DO JOGO
		this.lblScore = new JLabel();
		this.lblScore.setBounds(160, 0, 400, 60);
		this.lblScore.setText("Score: " + ctrl.getScore());  
		this.lblScore.setFont(new Font("Arial",Font.BOLD,30));
		this.lblScore.setForeground(Color.white);
		this.lblPista.add(lblScore);

		this.repaint();
	}

	// PROGRAMANDO A AÇÃO DOS BOTÕES
	@Override
	public void actionPerformed(ActionEvent dispara) {
		if (dispara.getSource() == this.btnCorrida) {
			lblNave1.setVisible(false);
			lblNave2.setVisible(false);
			lblNave3.setVisible(false);
			
			//CRIAR NAVES E REPASSA AS NAVES PARTICIPANTES PARA O CONTROLADOR
			naves = new ArrayList<NaveThread>();
			naves.add((NaveThread) criarLabelNave(CtrlChegada.NAVE_UM, imgNave1, 0, 50));
			naves.add((NaveThread) criarLabelNave(CtrlChegada.NAVE_DOIS, imgNave2, 0, 200));
			naves.add((NaveThread) criarLabelNave(CtrlChegada.NAVE_TRES, imgNave3, 0, 350));
			ctrl.setNavesParticipantes(naves);
			
			this.lblPista.add(naves.get(CtrlChegada.NAVE_UM));
			this.lblPista.add(naves.get(CtrlChegada.NAVE_DOIS));
			this.lblPista.add(naves.get(CtrlChegada.NAVE_TRES));
			
			btnCorrida.setEnabled(false);
			btnVoltar.setEnabled(false);
		}
		if (dispara.getSource() == this.btnVoltar) {
			this.dispose();
			jPrincipal.setVisible(true);
		}

		if (dispara.getSource() == this.btnNave1) {
			ctrl.setNaveEscolhida(CtrlChegada.NAVE_UM);
			prepararBotoes();
		}
		if (dispara.getSource() == this.btnNave2) {
			ctrl.setNaveEscolhida(CtrlChegada.NAVE_DOIS);
			prepararBotoes();
		}
		
		if (dispara.getSource() == this.btnNave3) {
			ctrl.setNaveEscolhida(CtrlChegada.NAVE_TRES);
			prepararBotoes();
		}
		
	}
	
	// INSERINDO AS JLABELS DOS NAVES
	public JLabel criarLabelNave(Integer numero, ImageIcon img, int posX, int posY) {
		NaveThread nave = new NaveThread(numero, img, posX, posY, this);
		nave.setSize(256, 141);
		nave.setVisible(true);
		this.add(nave);
		return nave;
	}
	
	//PREPARA CORRIDA HABILITANDO O BOTÃO DE CORRIDA E DESABILITANDO OS BOTÕES DE APOSTA
	private void prepararBotoes() {
		btnNave1.setVisible(false);
		btnNave2.setVisible(false);
		btnNave3.setVisible(false);
		btnCorrida.setEnabled(true);
	}

	//METODO QUE PERMITE ADICIONARMOS E HABILITAMOS OS BOTÕES NOVAMENTE 
	public void restaurar() {
		lblPista.add(btnCorrida);
		lblPista.add(btnVoltar);
		lblPista.add(btnNave1);
		lblPista.add(btnNave2);
		lblPista.add(btnNave3);
		lblPista.add(lblNave1);
		lblPista.add(lblNave2);
		lblPista.add(lblNave3);
		lblPista.add(lblScore);
		
		lblNave1.setVisible(true);
		lblNave2.setVisible(true);
		lblNave3.setVisible(true);
		
		lblScore.setText("Score: " + ctrl.getScore()); 
		lblScore.setVisible(true);
		
		btnCorrida.setEnabled(false);
		btnVoltar.setEnabled(true);
		
		btnNave1.setVisible(true);
		btnNave2.setVisible(true);
		btnNave3.setVisible(true);
	}

}