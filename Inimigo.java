import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inimigo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inimigo extends Actor
{
    private int velocidade;
    private boolean estaVivo;
    private boolean movendoPraCima;
    private int posXFixo;
    private int posYAlvo;
    private int tempoParaRessuscitar;
    private int tempoDesdeInimigoMorreu;
    
    public Inimigo()
    {
        posXFixo = 750;
        tempoParaRessuscitar = 80;
        tempoDesdeInimigoMorreu = 0;
        // reinicia as característas da nave
        inicializar();
    }
    
    private void inicializar()
    {
        // define a posicao X fixa e o valor maximo de posicao Y.
        posYAlvo = Greenfoot.getRandomNumber(600);
        setImage(new GreenfootImage("inimigo.png"));
        setLocation(posXFixo, posYAlvo);

        velocidade = 5;
        
        estaVivo = true;
        movendoPraCima = false;                        
    }
    
    /**
     * Move a nave pra cima, se ela estiver viva
     */
    private void moverCima()
    {
        setLocation(getX(), getY()-velocidade);
    }

    /**
     * Move a nave pra baixo, se ela estiver viva
     */
    private void moverBaixo()
    {
        setLocation(getX(), getY()+velocidade);
    }
    
    public void atirar()
    {
        Tiro tiro = new Tiro(true);
        getWorld().addObject(tiro, getX(), getY());
    }
    
    /**
     * Trata quando a nave do jogador toma um tiro, se ela estiver viva
     */
    public void tomarTiro()
    {
        if (estaVivo)
        {
            // morre
            estaVivo = false;
            Greenfoot.playSound("explosao.wav");
            setImage(new GreenfootImage("explosao.png"));
            tempoDesdeInimigoMorreu = 0;
            Espaco espaco = (Espaco) getWorld();
            espaco.contabilizarMorteInimigo();
        }
    }
    
    /**
     * Executa a Inteligência Artificial do inimigo, tratando seus movimentos e tiros
     * 
     * @return retorna o tiro dado pelo inimigo (se ele tiver dado um), caso contrario retorna null
     */
    public void executarIA()
    {
        // realiza a movimentação do inimigo
        movimentar();
        
        // define aleatoriamente se o inimigo irá atirar
        // atirar com a probabilidade de 1 em 15
        if (Greenfoot.getRandomNumber(40) < 1)
        {
            atirar();
        }
    }
    
    /**
     * Trata a movimentação do inimigo
     */
    private void movimentar()
    {
        // se o inimigo esta se movendo pra cima e ainda nao alcancou a posicao alvo
        if (movendoPraCima && (getY() > posYAlvo))
        {
            // continua se movendo pra cima
            moverCima();
        }
        // se o inimigo esta se movendo pra baixo e ainda nao alcancou a posicao alvo
        else if (!movendoPraCima && (getY() < posYAlvo))        
        {
            // continua se movendo pra baixo
            moverBaixo();
        }
        // se a nave esta se movendo pra cima e passou da posicao alvo, ou movendo pra baixo e tambem passou da posição alvo
        else if ((movendoPraCima && (getY() <= posYAlvo)) || (!movendoPraCima && (getY() >= posYAlvo)))
        {
            // sorteia uma nova posicao alvo
            posYAlvo = Greenfoot.getRandomNumber(600);
            
            // descobre se deve movimentar pra cima ou pra baixo para alcancar a nova posicao alvo
            movendoPraCima = (posYAlvo < getY());
        }
    }
    
    
    /**
     * Act - do whatever the Inimigo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (estaVivo) {
            executarIA();
        }
        else {
            tempoDesdeInimigoMorreu++;
            
            if (tempoDesdeInimigoMorreu >= tempoParaRessuscitar) {
                inicializar();
            }
        }
    }
}
