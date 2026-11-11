package com.joaozanata.melhor_oferta;

import java.util.List;

public interface OfertasService {
    void salvar(Oferta oferta);
    void atualizar(Oferta oferta);
    List<Oferta> buscarOfertas();
    void apagar(int id);

    static OfertasService getInstance() {return new OfertaCSVService();}
}
