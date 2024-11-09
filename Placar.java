import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa o placar do jogo
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Placar extends Actor
{
    // pontuação atual do placar
    private int pontuacao;
    
    // pontos ganhos a cada inimigo morto
    private int pontoPorInimigo;
    
    /**
     * Construtor para objetos placar
     */
    public Placar() {
        // define quantos pontos ganhar por inimigo morto
        pontoPorInimigo = 50;
        // posiciona o placar no mundo
        setLocation(20,20);
        // reinicia o placar
        inicializar();
    }
    
    /**
     * Reinicializa o placar
     */
    public void inicializar()
    {        
        // Reinicializa a pontuação
        pontuacao = 0;
        // Redefine a imagem do placar usando a pontuação atual
        redefinirImagem();
    }
    
    /**
     * Contabiliza pontos por ter derrotado um inimigo
     */
    public void contarMorteInimigo()
    {
        // atualiza a pontuação
        pontuacao += pontoPorInimigo;
        // Redefine a imagem do placar usando a pontuação atual
        redefinirImagem();
    }
    
    /**
     * Atualiza a imagem do placar de acordo com a pontuação atual
     */
    private void redefinirImagem() {
       setImage(new GreenfootImage("Placar: " + pontuacao, 20, Color.YELLOW, Color.BLACK)); 
    }
    
    /**
     * O placar não faz nada a cada iteração do jogo
     */
    public void act()
    {
        // nada a fazer
    }
}
