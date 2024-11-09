import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa o texto de créditos que fica aparecendo no jogo
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Creditos extends Actor
{
    /**
     * Construtor para objetos Credito
     */
    public Creditos() {
        // define uma image para representar os créditos do jogo
        setImage(new GreenfootImage("GAC125 (IPOO) - DAC-ICET-UFLA (v 1.1)", 15, Color.GRAY, Color.BLACK));
    }
    
    /**
     * Os creditos não fazem nada a cada iteração do jogo
     */
    public void act()
    {
        // nada a fazer
    }
}
