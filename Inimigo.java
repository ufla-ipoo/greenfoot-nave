import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa a nave inimiga no jogo
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Inimigo extends Actor
{
    // velocidade da nave
    private int velocidade;
    // indica se a nave está viva ou não
    private boolean estaVivo;
    // indica se a nave está se movendo para cima
    // (caso contrário, move-se pra baixo)
    private boolean movendoPraCima;
    // indica a posição X fixa da nave (ela se move
    // apenas na vertical)
    private int posXFixo;
    // indica para qual posição Y a nave está se
    // movendo no momento
    private int posYAlvo;
    // indica de quanto em quanto "tempo" a nave pode
    // atirar
    // obs: o tempo na verdade é a quantidade de vezes
    //      que o método act é chamado
    private int tempoNovoTiro;
    // indica quanto "tempo" demora para a nave ressuscitar
    // depois que ela morre
    private int tempoParaRessuscitar;
    // conta quanto "tempo" se passou desde que a nave morreu
    private int tempoDesdeQueMorreu;
    // referência para o placar para contar os pontos quando 
    // o inimigo morre
    private Placar placar;
    
    public Inimigo(Placar placar)
    {
        // inicializa os atributos que possuem valores constantes
        velocidade = 5;
        posXFixo = 750;
        tempoNovoTiro = 60;
        tempoParaRessuscitar = 80;
        
        // a nave ainda não morreu :)
        tempoDesdeQueMorreu = 0;
        
        // guarda a referência para o placar
        this.placar = placar;
        
        // reinicia as característas da nave
        inicializar();
    }
    
    /**
     * Inicializa a nave para começar de novo
     */
    private void inicializar()
    {
        // reseta a imagem da nave
        setImage(new GreenfootImage("inimigo.png"));
        // coloca a nave na posicao X fixa e sorteia o valor
        // da coordenada Y para onde nave vai se mover (e
        // já começa nessa posição)
        posYAlvo = Greenfoot.getRandomNumber(600);        
        setLocation(posXFixo, posYAlvo);

        // indica que a nave está viva
        estaVivo = true;
        // começa se movendo pra cima
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
    
    /**
     * Faz a nave atirar
     */
    public void atirar()
    {
        // cria um objeto tiro e o adiciona no mundo na
        // mesma posição onde está a nave
        Tiro tiro = new Tiro(true);
        getWorld().addObject(tiro, getX(), getY());
    }
    
    /**
     * Trata quando a nave do jogador toma um tiro
     */
    public void tomarTiro()
    {
        // Se a nave estiver viva
        if (estaVivo)
        {
            // ela morre
            estaVivo = false;
            // aparece uma imagem de explosão e é tocado som de explosão
            Greenfoot.playSound("explosao.wav");
            setImage(new GreenfootImage("explosao.png"));
            // reinicializa o tempo desde que a nave morreu
            tempoDesdeQueMorreu = 0;
            // contabiliza a morte do inimigo no jogo
            placar.contarMorteInimigo();
        }
    }
    
    /**
     * Executa a Inteligência Artificial do inimigo, tratando seus movimentos e tiros
     */
    public void executarIA()
    {
        // realiza a movimentação do inimigo
        movimentar();
        
        // define aleatoriamente se o inimigo irá atirar
        // com a probabilidade de 1 em 15
        if (Greenfoot.getRandomNumber(tempoNovoTiro) < 1)
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
        // se a nave esta se movendo pra cima e passou da posicao alvo, 
        // ou movendo pra baixo e tambem passou da posição alvo
        else if ((movendoPraCima && (getY() <= posYAlvo)) || (!movendoPraCima && (getY() >= posYAlvo)))
        {
            // sorteia uma nova posicao alvo
            posYAlvo = Greenfoot.getRandomNumber(600);
            
            // descobre se deve movimentar pra cima ou pra baixo para alcancar a nova posicao alvo
            movendoPraCima = (posYAlvo < getY());
        }
    }
    
    
    /**
     * Método que trata o que nave faz a cada iteração do jogo.
     */
    public void act()
    {
        // se a nave estiver viva, executa a IA da nave
        if (estaVivo) {
            executarIA();
        }
        else {
            // se a nave estiver morta, contabiliza o tempo desde que morreu
            tempoDesdeQueMorreu++;
            
            // e ressuscita a nave se chegou a hora de ressuscitar
            if (tempoDesdeQueMorreu >= tempoParaRessuscitar) {
                inicializar();
            }
        }
    }
}
