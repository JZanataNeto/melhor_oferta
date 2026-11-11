package com.joaozanata.melhor_oferta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaCSVService implements OfertasService{
    private static final String SEPARADOR = ";";
    private static final Path ARQUIVO_SAIDA = Paths.get("./dados.csv");
    private List<Oferta> ofertas;
    public OfertaCSVService() {carregaDados();}

    @Override
    public void salvar(Oferta oferta) {
        oferta.setId(ultimoId() + 1);
        ofertas.add(oferta);
        salvaDados();
    }
    @Override
    public void atualizar(Oferta oferta) {
        Oferta ofertaantiga = buscarPorId(oferta.getId());
        ofertaantiga.setProduto(oferta.getProduto());
        ofertaantiga.setPreco(oferta.getPreco());
        ofertaantiga.setFrete(oferta.getFrete());
        ofertaantiga.setPrazo(oferta.getPrazo());
        salvaDados();
    }
    @Override
    public List<Oferta> buscarOfertas() {
        return ofertas;
    }
    @Override
    public void apagar(int id) {
        Oferta oferta = buscarPorId(id);
        ofertas.remove(oferta);
        salvaDados();
    }
    public Oferta buscarPorId(int id) {
        return ofertas.stream().filter(oferta ->oferta.getId() == id).findFirst().orElseThrow(() -> new Error("Oferta inexistente"));
    }
    private void salvaDados() {
        StringBuilder sb = new StringBuilder();
        for (Oferta of : ofertas) {
            String linha = criaLinha(of);
            sb.append(linha);
            sb.append(System.lineSeparator());
        }
        try {
            Files.write(ARQUIVO_SAIDA, sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private int ultimoId() {
        return ofertas.stream().mapToInt(Oferta::getId).max().orElse(0);
    }
    private void carregaDados() {
        try {
            if(!Files.exists(ARQUIVO_SAIDA)) {
                Files.createFile(ARQUIVO_SAIDA);
            }
            ofertas = Files.lines(ARQUIVO_SAIDA)
                    .filter(linha -> !linha.trim().isEmpty())
                    .map(this::leLinha)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private Oferta leLinha(String linha) {
        String[] colunas = linha.split(SEPARADOR);
        int id = Integer.parseInt(colunas[0]);
        Oferta oferta = new Oferta();
        oferta.setId(id);
        oferta.setProduto(colunas[1]);
        oferta.setPreco(Double.parseDouble(colunas[2]));
        oferta.setFrete(Double.parseDouble(colunas[3]));
        oferta.setPrazo(Integer.parseInt(colunas[4]));
        return oferta;
    }
    private String criaLinha(Oferta of) {
        String idStr = String.valueOf(of.getId());
        String precoStr = String.valueOf(of.getPreco());
        String freteStr = String.valueOf(of.getFrete());
        String prazoStr = String.valueOf(of.getPrazo());
        return String.join(SEPARADOR, idStr, of.getProduto(), precoStr, freteStr, prazoStr);
    }

}
