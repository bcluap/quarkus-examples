package guru.jini;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AsBean1 {

    @Inject
    AsBean2 bean;
    
    public void go(int calls) {
        calls--;
        if (calls == 0) {
            return;
        }
        bean.go(calls);
    }

}
