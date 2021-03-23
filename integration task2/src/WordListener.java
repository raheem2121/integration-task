import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



public class WordListener implements PropertyChangeListener {
    private CardPanel cPanel;

    public WordListener(CardPanel panel) {
        this.cPanel = panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
             String value = evt.getNewValue().toString();
            if( evt.getPropertyName()=="difficulty") {
            	System.out.println("Old value"+ evt.getOldValue());
            	System.out.println("NEW VAL"+ evt.getNewValue());
                CardPanel.word.setDifficulty(value);;
            } else if(evt.getPropertyName() =="wordValue") {
            	System.out.println("Old value"+ evt.getOldValue());
            	System.out.println("NEW VAL"+ evt.getNewValue());
            	CardPanel.word.setWordValue(value);
           } 

    }
}
