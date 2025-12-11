package Padaria.services;

import java.util.ArrayList;
import java.util.List;

import Padaria.model.entities.Insumo;
import Padaria.repository.RepositorioBinario;
import Padaria.utilitarios.Video;

public class ProducaoService {
    private RepositorioBinario<Insumo> repoInsumos;

    public ProducaoService() {
        this.repoInsumos = new RepositorioBinario<>("insumos");
    }

    public void alertasReposicao() {
        Video.cabecalho("ALERTAS DE REPOSIÇÃO");
        
        List<Insumo> insumos = repoInsumos.listarTodos();
        if (insumos.isEmpty()) {
            Video.mensagemInfo("Nenhum insumo cadastrado.");
            return;
        }
        
        List<Insumo> criticos = new ArrayList<>();
        List<Insumo> alertas = new ArrayList<>();
        
        for (Insumo insumo : insumos) {
            if (!insumo.precisaReposicao()) {
                continue;
            }
            
            if (insumo.getQuantidadeAtual() == 0) {
                criticos.add(insumo);
                continue;
            }
            
            alertas.add(insumo);
        }
        
        if (!criticos.isEmpty()) {
            Video.mensagemErro("\nCRÍTICOS (ESTOQUE ZERADO):");
            for (Insumo i : criticos) {
                Video.mensagemErro("   " + i.getNome() + " - " + i.getQuantidadeAtual() + " " + i.getUnidadeMedida());
            }
        }
        
        if (!alertas.isEmpty()) {
            Video.mensagemAlerta("\nABAIXO DO MÍNIMO:");
            for (Insumo i : alertas) {
                Video.mensagemAlerta("   " + i.getNome() + " - " + i.getQuantidadeAtual() + " " + i.getUnidadeMedida() + " (Mín: " + i.getEstoqueMinimo() + ")");
            }
        }
        
        if (criticos.isEmpty() && alertas.isEmpty()) {
            Video.mensagemOk("Todos os insumos estão em ordem!");
        }
    }
}