import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Placar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Placar extends Actor
{
    // pontos atuais do placar
    private int pontos;
    
    // pontos ganhos a cada inimigo morto
    private int pontoPorInimigo = 50;
    
    
    public Placar() {
        setLocation(20,20);
        inicializar();
    }
    
    public void inicializar()
    {        
        pontos = 0;
        setImage(new GreenfootImage("Placar: " + pontos, 20, Color.YELLOW, Color.BLACK));
    }
    
    public void contarMorteInimigo()
    {
        pontos += pontoPorInimigo;
        setImage(new GreenfootImage("Placar: " + pontos, 20, Color.YELLOW, Color.BLACK));
    }
    
    /**
     * Act - do whatever the Placar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
