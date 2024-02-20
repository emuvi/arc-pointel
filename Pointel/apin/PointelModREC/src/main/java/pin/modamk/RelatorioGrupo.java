package pin.modamk;

import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;

public class RelatorioGrupo {
    public String campo;
    public RelatorioCampo.Formatos formato;
    public Integer indice;
    
    public ColumnGroupBuilder grupoGrafico;

    public RelatorioGrupo(String doCampo, RelatorioCampo.Formatos doFormato, Integer comIndice) {
        campo = doCampo;
        formato = doFormato;
        indice = comIndice;
    }
}
