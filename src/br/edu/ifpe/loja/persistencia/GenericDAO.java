package br.edu.ifpe.loja.persistencia;

import br.edu.ifpe.loja.entidades.EntidadeBase;
import java.util.List;

public interface GenericDAO<T extends EntidadeBase> {
    void inserir(T entidade);
    void editar(T entidade);
    void remover(Long id);
    T consultar(Long id);
    List<T> listarTodos();
}
