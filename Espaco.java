import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Espaço no qual o jogo acontece
 * 
 * @author Julio César Alves
 * @version 2024.11.09
 */
public class Espaco extends World
{
    private GreenfootSound somDeFundo;
    private Placar placar;

    /**
     * Construtpr de objetos Espaço
     */
    public Espaco()
    {    
        // Define que o espaço tem 800 pixels de largura e 600 de altura
        // e células de tamanho 1
        super(800, 600, 1);
        // cria os objetos fixos do espaço
        prepare();
        // carrega o arquivo do som de fundo
        somDeFundo = new GreenfootSound("space-adventure.mp3");
    }
    
    /**
     * Método chamado internamente pelo Greenfoot quando o mundo é iniciado
     */
    public void started() {
        // toca a música de fundo
        somDeFundo.playLoop();
    }
    
    /**
     * Prepara o mundo para o início do programa.
     * Ou seja: cria os objetos iniciais e adiciona-os ao mundo.
     */
    private void prepare()
    {
        Nave nave = new Nave();
        addObject(nave,58,302);
        placar = new Placar();
        addObject(placar,69,22);
        Inimigo inimigo = new Inimigo(placar);
        addObject(inimigo,747,299);
        placar.setLocation(166,21);
        Creditos creditos = new Creditos();
        addObject(creditos,437,572);
    }
    
    /**
     * Método chamado quando o jogo termina
     */
    public void fimDeJogo() {
        // Para a música de fundo
        somDeFundo.stop();
        // Exibe a tela de game over
        Greenfoot.setWorld(new TelaGameOver());
    }
}
