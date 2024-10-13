package br.ufrn.DASH.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.DASH.mapper.prontuario.ProntuarioCreate;
import br.ufrn.DASH.mapper.prontuario.ProntuarioMapper;
import br.ufrn.DASH.mapper.prontuario.ProntuarioOutput;
import br.ufrn.DASH.mapper.prontuario.ProntuarioUpdate;
import br.ufrn.DASH.mapper.secao.SecaoCreate;
import br.ufrn.DASH.mapper.secao.SecaoMapper;
import br.ufrn.DASH.mapper.secao.SecaoOutput;
import br.ufrn.DASH.model.Prontuario;
import br.ufrn.DASH.model.Secao;
import br.ufrn.DASH.service.ProntuarioService;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @Autowired
    private ProntuarioMapper prontuarioMapper;

    @Autowired
    private SecaoMapper secaoMapper;

    @PostMapping
    public ResponseEntity<ProntuarioOutput> create(@RequestBody ProntuarioCreate prontuarioCreate) {
        Prontuario prontuario = prontuarioMapper.toProntuarioFromCreate(prontuarioCreate);
        Prontuario prontuarioCriado = prontuarioService.create(prontuario);
        ProntuarioOutput prontuarioOutput = prontuarioMapper.toProntuarioOutput(prontuarioCriado);
        return new ResponseEntity<ProntuarioOutput>(prontuarioOutput, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioOutput> getById(@PathVariable Long id) {
        Prontuario prontuario = prontuarioService.getById(id);
        ProntuarioOutput prontuarioOutput = prontuarioMapper.toProntuarioOutput(prontuario);
        return new ResponseEntity<ProntuarioOutput>(prontuarioOutput, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProntuarioOutput>> getAll() {
        List<Prontuario> prontuarios = prontuarioService.getAll();
        List<ProntuarioOutput> prontuariosOutput = prontuarios
                .stream()
                .map(prontuarioMapper::toProntuarioOutput)
                .toList();
        return new ResponseEntity<List<ProntuarioOutput>>(prontuariosOutput, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioOutput> update(@PathVariable Long id, @RequestBody ProntuarioUpdate prontuarioUpdate) {
        Prontuario prontuario = prontuarioMapper.toProntuarioFromUpdate(prontuarioUpdate);
        Prontuario prontuarioAtualizado = prontuarioService.update(id, prontuario);
        ProntuarioOutput prontuarioOutput = prontuarioMapper.toProntuarioOutput(prontuarioAtualizado);
        return new ResponseEntity<ProntuarioOutput>(prontuarioOutput, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        prontuarioService.delete(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAll() {
        prontuarioService.deleteAll();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/{idProntuario}/addSecao")
    public ResponseEntity<SecaoOutput> addSecao(@PathVariable Long idProntuario, @RequestBody SecaoCreate secaoCreate) {
        Secao secaoNova = secaoMapper.toSecaoFromCreate(secaoCreate);
        Secao secaoCriada = prontuarioService.addSecao(idProntuario, secaoNova);
        SecaoOutput secaoOutput = secaoMapper.toSecaoOutput(secaoCriada);
        return new ResponseEntity<SecaoOutput>(secaoOutput, HttpStatus.CREATED);
    }

    @PostMapping("/{idProntuario}/duplicar")
    public ResponseEntity<ProntuarioOutput> duplicar(
        @PathVariable Long idProntuario,
        @RequestParam Long idUsuario                                                      
    ) {
        Prontuario prontuarioDuplicado = prontuarioService.duplicar(idProntuario, idUsuario);
        ProntuarioOutput prontuarioOutput = prontuarioMapper.toProntuarioOutput(prontuarioDuplicado);
        return new ResponseEntity<ProntuarioOutput>(prontuarioOutput, HttpStatus.CREATED);
    }
    

}
