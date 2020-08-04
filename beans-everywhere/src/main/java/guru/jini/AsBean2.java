package guru.jini;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AsBean2 {

    @Inject
    AsBean1 bean;
    
    public void go(int calls) {
        calls--;
        if (calls == 0) {
            return;
        }
        bean.go(calls);
    }

}
