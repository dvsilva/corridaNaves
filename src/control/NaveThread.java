package control;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.Corrida;

/** 
 * @author Felipe e Danyllo
 */
public class NaveThread extends JLabel implements Runnable {
	
	private static final long serialVersionUID = 9089986510592425416L;
	
	//CONSTANTES
	public final static int POSICAO_FINAL = 1050; 
	public final static int LINHA_CHEGADA_INI = 900; 
	public final static int LINHA_CHEGADA_FIM = 930; 
	
	//ATRIBUTOS
	private Thread naveThread = null;
	private int posX;
	private int posY;
	private ImageIcon img;
	
	//VARIAVEL QUE CONTROLA O LOOP DA THREAD
	private boolean stop = false;
	
	// ENVIO A MENSAGEM PARA O OBJETO CTRL A FIM MANTER SOMENTE UM CONTROLADOR
	// PARA TODAS AS THREADS - NAVES
	private CtrlChegada ctrl = CtrlChegada.getSingleton();
	
	private boolean chegou = false;

	// CONSTRUTOR DEFAULT
	public NaveThread() {
	}

	// CONSTRUTOR SOBRECARREGADO
	public NaveThread(Integer numero, ImageIcon img, int posX, int posY, Corrida corrida) {
		super(img);
		this.posX = posX;
		this.posY = posY;
		this.img = img;
		this.ctrl.setCorrida(corrida);
		
		naveThread = new Thread(this, String.valueOf(numero));
		naveThread.start();

	}

	// M�TODO RUN() DA INTERFACE RUNNABLE
	@Override
	public void run() {
		
		posX += new Random().nextInt(10);
		this.setLocation(posX, posY);

		//VERIFICA QUE NAVE CRUZOU A LINHA DE CHEGADA
		if ((posX >= LINHA_CHEGADA_INI)&&(posX <= LINHA_CHEGADA_FIM) &&(chegou == false)){
			ctrl.setColocacao(naveThread);
			chegou = true;
		}
		
		//AO CHEGAR NA POSI��O FINAL
		if (posX >= POSICAO_FINAL) {
			parar();
			ctrl.naveChegou();
		}

		/**ENQUANTO � VERDADEIRO QUE A NAVE DEVA ANDAR
		 * A THREAD "DORME" EXECUTA O M�TODO RUN
		 * */

		while (!stop) {

			try {
				Thread.sleep(50);
				run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**ENQUANTO FOR FALSO QUE A NAVE DEVA ANDAR A VARIAVEL STOP MUDA
	 * A THREAD N�O "DORME" N�O EXECUTA O M�TODO RUN
	*/
	public void parar() {
		this.stop = true;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
}