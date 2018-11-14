
package project.game.investigation;

import project.game.UserInterface;

/**
 *
 * @author ISEN
 */
public abstract class Clue {
    protected String m_content;
    protected boolean m_isFounded;
    protected UserInterface m_console;

    
    
    /*$$ CONSTRUCTOR $$*/
    public Clue(String content, boolean isFounded) {
        this.m_content = content;
        this.m_isFounded = isFounded;
    }

    
    /*$$ GETTERS & SETTERS $$*/
    public String getContent() {
        return m_content;
    }

    public boolean isFounded() {
        return m_isFounded;
    }

    
    public void setFounded(boolean isFounded) {
        this.m_isFounded = isFounded;
    }
    
    
    /*$$ METHODS $$*/
    public abstract void display();
}