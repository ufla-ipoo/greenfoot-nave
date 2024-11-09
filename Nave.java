import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa a nave do jogador
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Nave extends Actor
{
    // velocidade da nave
    private int velocidade;
    // indica se a nave está viva ou não
    private boolean estaViva;
    // "tempo" que demora para a nave ter outro tipo disponível
    // obs.: o tempo é simulado contando-se quantas vezes o
    //       método act é chamado.
    private int tempoRecargaTiro;
    // "tempo" que se passou desde o último tiro disparado pela
    // nave
    private int tempoDesdeUltimoTiro;
    
    /**
     * Construtor de objetos Nave
     */
    public Nave() {
        // inicializa a velocidade da nave e o tempo de recarga
        // do tiro
        velocidade = 4;
        tempoRecargaTiro = 25;
        // inicializa o tempo desde o último tiro de forma que
        // a nave possa já começar o jogo atirando
        tempoDesdeUltimoTiro = tempoRecargaTiro;
        // chama o método que inicializa a nave
        inicializar();
    }
    
    /**
     * Inicializa a nave para uma nova partida
     */
    public void inicializar()
    {                       
        // define a posição inicial da nave na tela
        setLocation(10, 200);
        // indica que a nave está viva
        estaViva = true;
    }
    
    /**
     * Retorna se a nave está viva
     */
    public boolean estaViva() {
        return estaViva;
    }
    
    /**
     * Move a nave pra direita, se ela estiver viva
     */
    public void moverDireita()
    {
        if (estaViva)
        {
            setLocation(getX()+velocidade, getY());
        }
    }
    
    /**
     * Move a nave pra esquerda, se ela estiver viva
     */
    public void moverEsquerda()
    {
        if (estaViva)
        {
            setLocation(getX()-velocidade, getY());
        }
    }

    /**
     * Move a nave pra cima, se ela estiver viva
     */
    public void moverCima()
    {
        if (estaViva)
        {
            setLocation(getX(), getY()-velocidade);
        }
    }

    /**
     * Move a nave pra baixo, se ela estiver viva
     */
    public void moverBaixo()
    {
        if (estaViva)
        {
            setLocation(getX(), getY()+velocidade);
        }
    }
    
    /**
     * Faz a nave atirar
     */
    public void atirar()
    {  
        // O tiro só acontece se já deu tempo de recarregar a arma
        if (estaViva && tempoDesdeUltimoTiro >= tempoRecargaTiro)
        {
            // cria um objeto tiro e o adiciona no mundo na
            // mesma posição onde está a nave
            Tiro tiro = new Tiro(false);
            getWorld().addObject(tiro, getX(), getY());
            // reinicializa o tempo desde o último tiro (com isso
            // um novo tiro precisará esperar o tempo de recarga)
            tempoDesdeUltimoTiro = 0;
        }
    }
    
    /**
     * Trata quando a nave do jogador toma um tiro
     */
    public void tomarTiro()
    {
        // Se a nave estiver viva, ela morre, aparece uma imagem de
        // explosão, é tocado um som de explosão e é acionado o fim do jogo.
        if (estaViva)
        {
            // morre
            estaViva = false;
            Greenfoot.playSound("explosao.wav");
            setImage(new GreenfootImage("explosao.png"));
            Espaco espaco = getWorldOfType(Espaco.class);
            espaco.fimDeJogo();
        }
    }
    
    /**
     * Método que trata o que nave faz a cada iteração do jogo.
     */
    public void act()
    {
        // conta mais uma unidade de tempo desde o último tiro
        tempoDesdeUltimoTiro++;
        
        // trata o que acontece quando o usuário pressiona alguma
        // tecla do teclado
        if (Greenfoot.isKeyDown("left")) {
            moverEsquerda();
        }
        if (Greenfoot.isKeyDown("right")) {
            moverDireita();
        }
        if (Greenfoot.isKeyDown("down")) {
            moverBaixo();
        }
        if (Greenfoot.isKeyDown("up")) {
            moverCima();
        }
        if (Greenfoot.isKeyDown("space")) {
            atirar();
        }
    }
}
