import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Espaco extends World
{
    private GreenfootSound somDeFundo;
    private Placar placar;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Espaco()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);         
        prepare();
        somDeFundo = new GreenfootSound("space-adventure.mp3");        
    }
    
    public void started() {
        somDeFundo.playLoop();
    }
    
    /**
     * Prepara o mundo para o início do programa.
     * Ou seja: criar os objetos iniciais e adicioná-los ao mundo.
     */
    private void prepare()
    {
        Nave nave = new Nave();
        addObject(nave,58,302);
        Inimigo inimigo = new Inimigo();
        addObject(inimigo,747,299);
        placar = new Placar();
        addObject(placar,69,22);
        placar.setLocation(166,21);
        Creditos creditos = new Creditos();
        addObject(creditos,737,572);
        creditos.setLocation(696,579);
    }
    
    public void fimDeJogo() {
        somDeFundo.stop();
        addObject(new GameOver(), getWidth()/2, getHeight()/2);
        // showText("GameOver", getWidth()/2, getHeight()/2);
    }
    
    public void contabilizarMorteInimigo() {
        placar.contarMorteInimigo();
    }
}
