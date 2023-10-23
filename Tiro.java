import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tiro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tiro extends Actor
{
    private int velocidade;
    private boolean ehDoInimigo;
    private GreenfootImage tiroNave;
    private GreenfootImage tiroInimigo;
    
    public Tiro(boolean tiroDoInimigo)
    {
        // inicializa os atributos referentes aos parametros passados
        ehDoInimigo = tiroDoInimigo;
        
        if (ehDoInimigo) {
            setImage(new GreenfootImage("misselInimigo.png"));
            velocidade = 5;
        }
        else {
            setImage(new GreenfootImage("misselNave.png"));
            velocidade = 10;
        }
    }
    
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
    
    private boolean verificarColisao() {
        if (ehDoInimigo) {
            Nave nave = (Nave) getOneIntersectingObject(Nave.class);
            if (nave != null) 
            {
                nave.tomarTiro();
                return true;
            }
        }
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
     * Act - do whatever the Tiro wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mover();
        if (!verificarColisao()) {
            if (isAtEdge()) {
                getWorld().removeObject(this);
            }
        }
    }
}
