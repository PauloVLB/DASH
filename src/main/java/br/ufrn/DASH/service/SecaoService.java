package br.ufrn.DASH.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.DASH.model.Secao;
import br.ufrn.DASH.repository.SecaoRepository;

@Service
public class SecaoService {

    @Autowired
    private SecaoRepository secaoRepository;

    public Secao create(Secao secao) {
        return secaoRepository.save(secao);
    }

    public List<Secao> getAll() {
        return secaoRepository.findAll();
    }

    public Secao getById(Long id) {
        return secaoRepository.findById(id).orElse(null);
    }

    public Secao update(Long id, Secao secao) {
        Secao secaoExistente = this.getById(id);
        
        if (secaoExistente == null) {
            return null;
        }
        
        secaoExistente.setTitulo(secao.getTitulo());
        secaoExistente.setOrdem(secao.getOrdem());
        secaoExistente.setNivel(secao.getNivel());

        return secaoRepository.save(secaoExistente);
    }

    public void delete(Long id) {
        secaoRepository.deleteById(id);
    }

    public void deleteAll() {
        secaoRepository.deleteAll();
    }
}
