package com.joaozanata.melhor_oferta;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class OfertasController implements Initializable {
    @FXML private Button btnAtualizar;
    @FXML private Button btnCalcular;
    @FXML private Button btnApagar;
    @FXML private Button btnLimpar;
    @FXML private Button btnSalvar;
    @FXML private TableView<Oferta> tblProduto;
    @FXML private TextField txtFrete;
    @FXML private TextField txtPrazo;
    @FXML private TextField txtPreco;
    @FXML private TextField txtProduto;
    @FXML private TextField txtResultado;
    @FXML private TableColumn<Oferta, String> clOferta;
    @FXML private TableColumn<Oferta,String> clProduto;
    @FXML private TableColumn<Oferta,String> clPreco;
    @FXML private TableColumn<Oferta,String> clFrete;
    @FXML private TableColumn<Oferta,String> clPrazo;

    private OfertasService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = OfertasService.getInstance();
        configuraColunas();
        configuraBindings();
        atualizarDadosTabela();
    }
    public void salvar() {
        Oferta of = new Oferta();
        pegaValores(of);
        service.salvar(of);
        atualizarDadosTabela();
    }
    public void atualizar() {
        Oferta of = tblProduto.getSelectionModel().getSelectedItem();
        pegaValores(of);
        service.atualizar(of);
        atualizarDadosTabela();
    }
    public void apagar() {
        Oferta of = tblProduto.getSelectionModel().getSelectedItem();
        service.apagar(of.getId());
        atualizarDadosTabela();
    }
    public void limpar() {
        tblProduto.getSelectionModel().select(null);
        txtProduto.setText("");
        txtPreco.setText("");
        txtFrete.setText("");
        txtPrazo.setText("");
        txtResultado.setText("");
    }
    private void pegaValores(Oferta of) {
        of.setProduto(txtProduto.getText());
        of.setPreco(Double.parseDouble(txtPreco.getText()));
        of.setFrete(Double.parseDouble(txtFrete.getText()));
        of.setPrazo(Integer.parseInt(txtPrazo.getText()));
    }
    private void atualizarDadosTabela() {
        tblProduto.getItems().setAll(service.buscarOfertas());
        limpar();
    }
    private void configuraColunas() {
        clOferta.setCellValueFactory(new PropertyValueFactory<>("id"));
        clProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clFrete.setCellValueFactory(new PropertyValueFactory<>("frete"));
        clPrazo.setCellValueFactory(new PropertyValueFactory<>("prazo"));
    }
    private void configuraBindings() {
        BooleanBinding camposVazios = txtProduto.textProperty().isEmpty()
                .or(txtPreco.textProperty().isNull())
                .or(txtFrete.textProperty().isNull())
                .or(txtPrazo.textProperty().isNull());
        BooleanBinding nadaSelecionado = tblProduto.getSelectionModel().selectedItemProperty().isNull();
        btnSalvar.disableProperty().bind(nadaSelecionado.not().or(camposVazios));
        btnAtualizar.disableProperty().bind(nadaSelecionado);
        btnApagar.disableProperty().bind(nadaSelecionado);
        btnLimpar.disableProperty().bind(nadaSelecionado);
        btnCalcular.disableProperty().bind(nadaSelecionado);
        tblProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtProduto.setText(newValue.getProduto());
                txtPreco.setText(String.valueOf(newValue.getPreco()));
                txtFrete.setText(String.valueOf(newValue.getFrete()));
                txtPrazo.setText(String.valueOf(newValue.getPrazo()));
            }
        });
    }
    public void calcularBestOferta() {
        ArrayList<Double> modulos = new ArrayList<>();
        for(Oferta of : tblProduto.getItems()) {
            double modulo = Math.sqrt(Math.pow(of.getPreco(), 2) + Math.pow(of.getFrete(), 2) + Math.pow(of.getPrazo(), 2));
            modulos.add(modulo);
        }
        if(modulos.size() == 3) {
            int bestOferta = modulos.indexOf(Collections.min(modulos));
            txtResultado.setText(String.valueOf(bestOferta + 1));
        } else if(modulos.size() < 3)  {
            txtResultado.setText("cadastre mais ofertas");
        } else {
            txtResultado.setText("somente 3 ofertas");
        }
    }


}
