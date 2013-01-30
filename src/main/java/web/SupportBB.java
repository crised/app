package web;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named()
@ConversationScoped()
public class SupportBB implements Serializable {
    private static final long serialVersionUID = 1L;
    private String someValue;
    @Inject
    private Conversation conversation;

    // Control start and end of conversation
    public void start() {
        conversation.begin();
    }

    public void end() {
        conversation.end();
    }

    // Navigation
    public String onClick() {
        if(someValue.equals("") || conversation == null) {
            return "";//Dont go anywhere if the there was no input the field
        }
        start();
        return "nextpage?faces-redirect=true";
    }

    public String onKeepGoing() {
        return "finish?faces-redirect=true";
    }

    public String onFinish() {
        end();// If triggered when there is no conversation(i.e URL Navigation)
        // there will be an exception
        return "index?faces-redirect=true";
    }

    // Getters & Setters
    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }
}