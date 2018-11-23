
package project.game.character.suspect;

import project.game.character.Sex;
import project.game.investigation.Clue;
import project.game.investigation.Deposition;
import project.game.investigation.DepositionType;

/**
 *
 * @author ISEN
 */

public class Innocent extends Suspect {
    /*$$ CONSTRUCTOR $$*/
    //nouvelle partie et chargement
    public Innocent(String fullName, Sex sex, int age, int stressLevel, int cooperationLevel, String look, String physicalAspect, String alibi, String heard, String seen) {
        super(fullName, sex, age, stressLevel, cooperationLevel, look, physicalAspect);
        
        //String depositor, String content, DepositionType category, boolean isLie
        m_heardTestimony = new Deposition(this.m_fullName, heard, DepositionType.HEARD, false);
        m_seenTestimony = new Deposition(this.m_fullName, seen, DepositionType.SEEN, false);
        m_alibi = new Deposition(this.m_fullName, alibi, DepositionType.ALIBI, false);
    }
    
    
    /*$$ METHODS $$*/
    @Override
    public void giveAlibi() {
        int[] validStage = {m_stress, m_cooperation};
        switch(rollMultiDice(validStage, null, false)) {
            case CRITIC_SUCCESS:
                //String text = "Je suis innocent. Concentrez-vous sur les autres suspects, plutôt que de perdre votre temps avec moi.";
                Deposition declaration = new Deposition(this.m_fullName, "innocent dans cette affaire", DepositionType.ROLE, false);
                declaration.display();
                
                if (!this.m_clueList.contains(declaration)) {
                    this.m_clueList.add(declaration);
                }
                break;
            case SUCCESS:
                m_alibi.display();
                
                if (!this.m_clueList.contains(m_alibi)) {
                    this.m_clueList.add(m_alibi);
                }
                break;
            case FAILURE:
                this.textNoSpeak();
                break;
            case CRITIC_FAILURE:
                this.textLawyer();
                break;
        }
    }

    @Override
    public void giveTestimony() {
        int[] validStage = {m_stress, m_cooperation};
        switch(rollMultiDice(validStage, null, false)) {
            case CRITIC_SUCCESS:
                m_seenTestimony.display();
                m_heardTestimony.display();
                
                //l'inspecteur enregistre ce qu'il entend de nouveau
                if (!m_clueList.contains(m_heardTestimony)) {
                    m_clueList.add(m_heardTestimony);
                }
                if (!m_clueList.contains(m_seenTestimony)) {
                    m_clueList.add(m_seenTestimony);
                }
                break;
            case SUCCESS:
                boolean tellSeen = Math.random() < 0.5;//1 chance sur 2 : soit ce qu'il a vu, soit ce qu'il a entendu
                (tellSeen? m_seenTestimony : m_heardTestimony).display();
                
                if (!m_clueList.contains((Clue)(tellSeen? m_seenTestimony : m_heardTestimony))) {
                    m_clueList.add((tellSeen? m_seenTestimony : m_heardTestimony));
                }
                break;
            case FAILURE:
                this.textNoSpeak();
                break;
            case CRITIC_FAILURE:
                this.textForget();//mais garde temoignage en mémoire
                break;
        }
    }
}
