package control;

import java.util.ArrayList;

import view.Corrida;
import view.Resultado;

/** 
 * @author Felipe e Danyllo
 */
public class CtrlChegada {
	
	//CONSTANTES
	private static final int ZERO = 0;
	private static final int PONTOS_ACERTO = 100;
	private static final int PONTOS_ERRO = 50;
	private static final int TOTAL_DE_NAVES = 3;
	
	public static final int PRIMEIRO_COLOCADO = 0;
	public static final int SEGUNDO_COLOCADO = 1;
	public static final int TERCEIRO_COLOCADO = 2;
	
	public static final Integer NAVE_UM = 0;
	public static final Integer NAVE_DOIS = 1;
	public static final Integer NAVE_TRES = 2;
	
	// VARIAVEIS QUE REFERENCIAM AS COLOCAÇÕES
	private ArrayList<NaveThread> navesConcluintes = new ArrayList<NaveThread>();
	private ArrayList<NaveThread> navesParticipantes = new ArrayList<NaveThread>();
	
	//VARIAVEL QUE POSSIBILITA NÃO TERMOS MAIS DE UM CTRL
	private static CtrlChegada singleton;
	
	//VARIAVEL DE CONTROLE QUE POSSUI A QUANTIDADE DE NAVES QUE CHEGARAM NA LINHA DE CHEGADA
	private int qtdConcluintes = ZERO;
	
	//VARIAVEL QUE REFERENCIA A JANELA CORRIDA
	private Corrida corrida;
	
	// VARIAVEL QUE GUARDA A NAVE ESCOLHIDA PARA APOSTA
	private Integer naveEscolhida;
	
	// VARIAVEL QUE GUARDA O SCORE DO JOGADOR
	private int score = ZERO;
	// VARIAVEL QUE GUARDA A MENSAGEM FINAL
	private String mensagem;
	
	// CONSTRUTOR DEFAULT
	public CtrlChegada() {
	}

	//MÉTODO QUE PERMITE GUARDARMOS AS COLOCAÇÕES DAS NAVES
	public void setColocacao(Thread nave) {
		Integer posNave = Integer.parseInt(nave.getName());
		this.navesConcluintes.add(this.navesParticipantes.get(posNave));
	}

	public void naveChegou() {
		++qtdConcluintes;
		
		//SE TODOS TERMINARAM A CORRIDA IMPRIMI O RESULTADO
		if(qtdConcluintes == TOTAL_DE_NAVES){
			imprimirResultado();
			//ZERA A VARIAVEL PARA PODERMOS FAZER A PROXIMA CORRIDA, SE HOUVER
			qtdConcluintes = ZERO;
		}
	}
	
	/**QUANDO TODOS AS NAVES CHEGAREM A LINHA DE CHEGADA DISPARAM ESSE MÉTODO
	QUE CRIA UMA JANELA QUE MOSTRA AS DEVIDAS COLOCAÇÕES
	*/
	public void imprimirResultado() {

		if (PRIMEIRO_COLOCADO == naveEscolhida){
			score += PONTOS_ACERTO;
			mensagem = ("Sua nave ganhou !");
		}
		else{
			score -= PONTOS_ERRO;
			mensagem = ("Sua nave perdeu !");
		}
		new Resultado(this, this.corrida);
		//FAZ COM QUE A JANELA DA CORRIDA NÃO FIQUE VISIVEL
		this.corrida.setVisible(false);
	}
	
	/**QUANDO QUEREMOS FAZER UMA NOVA CORRIDA DISPARAMOS ESSE MÉTODO QUE
	 * TORNA VISIVEL A JANELA CORRIDA
	 * REMOVE TODOS OS COMPONENTES DA LABELPISTA PRINCIPALMENTE AS NAVES
	 * APOS ISSO DISPARA O METODO RESTAURAR
	 */
	public void novaCorrida() {
		corrida.setVisible(true);
		corrida.lblPista.removeAll();
		corrida.restaurar();
	}
	
	// METODO QUE POSSIBILITA TERMOS SOMENTE UM CTRLCHEGADA
	public static CtrlChegada getSingleton() {
		if (CtrlChegada.singleton == null)
			CtrlChegada.singleton = new CtrlChegada();
		return CtrlChegada.singleton;
	}

	//GETS E SETS DOS ATRIBUTOS DO CTRL NECESSÁRIOS
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getNaveEscolhida() {
		return naveEscolhida;
	}

	public void setNaveEscolhida(Integer naveEscolhida) {
		this.naveEscolhida = naveEscolhida;
	}

	public ArrayList<NaveThread> getNavesParticipantes() {
		return navesParticipantes;
	}

	public void setNavesParticipantes(ArrayList<NaveThread> navesParticipantes) {
		this.navesParticipantes = navesParticipantes;
	}

	public ArrayList<NaveThread> getNavesConcluintes() {
		return navesConcluintes;
	}

	public void setNavesConcluintes(ArrayList<NaveThread> navesConcluintes) {
		this.navesConcluintes = navesConcluintes;
	}

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

}
