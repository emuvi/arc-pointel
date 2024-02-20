package pin.prk;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import pin.libbas.Retornos;
import pin.modrec.XMLReg;

public class ServProcessarRoboticoExc {

    private final ServProcessarRobotico robotico;
    private final Map<String, Object> variaveis;
    private Retornos retornos;

    public ServProcessarRoboticoExc(ServProcessarRobotico robotico) {
        this.robotico = robotico;
        this.variaveis = new HashMap<>();
        this.retornos = null;
    }

    public Retornos pRetornos() {
        return retornos;
    }

    public void mRetornos(Retornos retornos) {
        this.retornos = retornos;
    }

    public Map<String, Object> pVariaveis() {
        return variaveis;
    }

    public int pegaIndice(String comNome) {
        int pi = 0;
        for (RoboticoOpe ope : robotico.operacoes) {
            if (Objects.equals(ope.nome, comNome)) {
                return pi;
            }
            pi++;
        }
        return -1;
    }

    public void executa() throws Exception {
        XMLReg xml = XMLReg.novo();
        Retornos.bota(retornos, "Iniciado");
        if (robotico.operacoes != null) {
            int idx = 0;
            while (idx < robotico.operacoes.size()) {
                RoboticoOpe ope = robotico.operacoes.get(idx);
                RobAbs abs = (RobAbs) xml.pValor(ope.fonte);
                abs.mNome(ope.nome);
                abs.mRobotico(this);
                String cmd = abs.executa();
                switch (cmd) {
                    case "<próximo>":
                        idx++;
                        break;
                    case "<anterior>":
                        idx--;
                        break;
                    case "<último>":
                        idx = robotico.operacoes.size() - 1;
                        break;
                    case "<primeiro>":
                        idx = 0;
                        break;
                    case "<termina>":
                        idx = robotico.operacoes.size();
                        break;
                    default:
                        idx = pegaIndice(cmd);
                        if (idx == -1) {
                            throw new Exception("Operação Com Nome " + cmd + " Não Encontrada");
                        }
                        break;
                }
            }
        }
    }

}
