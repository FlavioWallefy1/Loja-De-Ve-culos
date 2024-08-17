package br.edu.ifpe.loja.persistencia;

import br.edu.ifpe.loja.entidades.EntidadeBase;
import java.util.ArrayList;
import java.util.List;

public class GenericDAOList<T extends EntidadeBase> implements GenericDAO<T> {

    private List<T> lista = new ArrayList<>();
    private Long idAtual = 1L;

    @Override
    public void inserir(T entidade) {
        entidade.setId(idAtual++);
        lista.add(entidade);
    }

    @Override
    public void editar(T entidade) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(entidade.getId())) {
                lista.set(i, entidade);
                return;
            }
        }
    }

    @Override
    public void remover(Long id) {
        lista.removeIf(entidade -> entidade.getId().equals(id));
    }

    @Override
    public T consultar(Long id) {
        for (T entidade : lista) {
            if (entidade.getId().equals(id)) {
                return entidade;
            }
        }
        return null;
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(lista);
    }
}
