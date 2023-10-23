import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa a nave do jogador.
 * 
 * @author Julio César Alves
 * @version 2023.10.07
 */
public class Nave extends Actor
{
    private int velocidade;
    private boolean estaViva;
    private int tempoRecargaTiro;
    private int tempoDesdeUltimoTiro;
    
    public Nave() {
        velocidade = 7;
        tempoRecargaTiro = 20;
        tempoDesdeUltimoTiro = tempoRecargaTiro;
        inicializar();
    }
    
    public void inicializar()
    {                       
        // posição inicial da nave na tela
        setLocation(10, 200);
        
        estaViva = true;
    }
    
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
    
    public void atirar()
    {  
        if (estaViva && tempoDesdeUltimoTiro >= tempoRecargaTiro)
        {
            Tiro tiro = new Tiro(false);
            getWorld().addObject(tiro, getX(), getY());
            tempoDesdeUltimoTiro = 0;
        }
    }
    
    /**
     * Trata quando a nave do jogador toma um tiro, se ela estiver viva
     */
    public void tomarTiro()
    {
        if (estaViva)
        {
            // morre
            estaViva = false;
            Greenfoot.playSound("explosao.wav");
            setImage(new GreenfootImage("explosao.png"));
            Espaco espaco = (Espaco) getWorld();
            espaco.fimDeJogo();
        }
    }
    
    /**
     * Act - do whatever the Nave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        tempoDesdeUltimoTiro++;
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
