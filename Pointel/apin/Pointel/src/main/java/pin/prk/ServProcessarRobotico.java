package pin.prk;

import java.util.ArrayList;
import java.util.List;
import pin.jrb.Serv;
import pin.jrb.Servico;

public class ServProcessarRobotico extends Serv {

    public Boolean mostrador;
    public List<RoboticoOpe> operacoes;

    public ServProcessarRobotico() {
        super(Servico.Tp.ProcessarRobotico);
        mostrador = false;
        operacoes = new ArrayList<>();
    }

    @Override
    public void executa() throws Exception {
        if (mostrador && pRetornos() != null) {
            pRetornos().mostrador("Processar Robótico " + nome);
            pRetornos().abre();
        }
        ServProcessarRoboticoExc exec = new ServProcessarRoboticoExc(this);
        exec.mRetornos(pRetornos());
        exec.executa();
    }
}
