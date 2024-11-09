import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Representa a tela de Game Over
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class TelaGameOver extends World
{

    /**
     * Construtor para objetos TelaGameOver.
     * 
     */
    public TelaGameOver()
    {    
        // Define que o espaço tem 800 pixels de largura e 600 de altura
        // e células de tamanho 1
        super(800, 600, 1);
        // cria os objetos fixos do espaço
        prepare();
    }
    
    /**
     * Prepara o mundo para o início do programa.
     * Ou seja: cria os objetos iniciais e adiciona-os ao mundo.
     */
    private void prepare()
    {
        GreenfootImage imagemDeFundo = new GreenfootImage(getWidth(), getHeight());
        imagemDeFundo.setColor(Color.BLACK);
        imagemDeFundo.fill();
        imagemDeFundo.drawImage(new GreenfootImage("Game Over!", 80, Color.YELLOW, Color.BLACK), 150, getHeight()/2-50);
        setBackground(imagemDeFundo);
    }
}
