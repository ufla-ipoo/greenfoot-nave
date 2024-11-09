import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objeto que representa os tiros tanto da nave do jogador
 * quanto da nave inimiga
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Tiro extends Actor
{
    // velocidade do tiro
    private int velocidade;
    // indica se o tiro é da nave inimiga (caso contrário, 
    // é da nave do jogador)
    private boolean ehDoInimigo;
    // imagem para tiro da nave do jogador
    private GreenfootImage tiroNave;
    // imagem para tiro da nave inimiga
    private GreenfootImage tiroInimigo;
    
    /**
     * Construtor para objetos tiro
     * 
     * @param tiroEhDoInimigo indica se o tiro que está sendo criado é do inimigo
     *                        (caso contrário, é da nave do jogador)
     */
    public Tiro(boolean tiroEhDoInimigo)
    {
        ehDoInimigo = tiroEhDoInimigo;
     
        // Define a imagem e a velocidade do tiro dependendo se é
        // da nave inimiga ou do jogador
        if (ehDoInimigo) {
            setImage(new GreenfootImage("misselInimigo.png"));
            velocidade = 5;
        }
        else {
            setImage(new GreenfootImage("misselNave.png"));
            velocidade = 10;
        }
    }
    
    /**
     * Trata a movimentação do tiro.
     * - Se for da nave inimiga se move para a esquerda.
     * - Se for da nave do jogador, se move para a direita.
     */
    private void mover()
    {
        if (ehDoInimigo)
        {
            setLocation(getX()-velocidade, getY());
        }
        else
        {
            setLocation(getX()+velocidade, getY());
        }
    }
    
    /**
     * Verifica se o tiro acertou o alvo
     */
    private boolean verificarColisao() {
        // se o tiro é da nave inimiga e acertou a nave do jogador,
        // a nave do jogador toma o tiro
        if (ehDoInimigo) {
            Nave nave = (Nave) getOneIntersectingObject(Nave.class);
            if (nave != null) 
            {
                nave.tomarTiro();
                return true;
            }
        }
        // se o tiro é da nave jogador e acertou a nave inimiga,
        // a nave inimiga toma o tiro
        else {
            Inimigo inimigo = (Inimigo) getOneIntersectingObject(Inimigo.class);
            if (inimigo != null) 
            {
                inimigo.tomarTiro();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método que trata o que o tiro faz a cada iteração do jogo
     */
    public void act()
    {
        // o tiro se move
        mover();
        // se o tiro não acertou ninguém e já chegou na borda do mundo
        // o tiro é removido do jogo
        if (!verificarColisao() && isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
