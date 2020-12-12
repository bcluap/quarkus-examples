package reactive.scenarios;

import java.io.Serializable;

public class SomeClass
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
    
    
}
