# Melhor Oferta

Aplicativo desktop em Java para comparar e encontrar a melhor oferta entre produtos, considerando preço, frete e prazo de entrega.

## Sobre o Projeto

O **Melhor Oferta** permite cadastrar até 3 ofertas de produtos e calcular qual delas apresenta o melhor custo-benefício. O cálculo é feito através do módulo vetorial, que combina os três fatores (preço, frete e prazo) em um único valor:

```
módulo = √(preço² + frete² + prazo²)
```

A oferta com o menor módulo é considerada a melhor.

## Funcionalidades

- **Cadastrar** novas ofertas com produto, preço, frete e prazo de entrega.
- **Atualizar** ofertas existentes.
- **Excluir** ofertas cadastradas.
- **Calcular** a melhor oferta entre os produtos cadastrados.
- **Persistência** dos dados em arquivo CSV (`dados.csv`).

## Tecnologias

- **Java 21**
- **JavaFX 21.0.6** (interface gráfica)
- **Maven** (gerenciamento de dependências e build)
- **JUnit 5** (testes)

## Pré-requisitos

- JDK 21 ou superior
- Maven 3.x

## Como Executar

### Via Maven

```bash
mvn clean javafx:run
```

### Via IDE

Abra o projeto em sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code) e execute a classe `OfertasApplication`.

## Estrutura do Projeto

```
melhor_oferta/
├── dados.csv                              # Arquivo de persistência das ofertas
├── pom.xml                                # Configuração do Maven
├── mvnw / mvnw.cmd                       # Maven Wrapper
└── src/
    └── main/
        └── java/
            └── com/joaozanata/melhor_oferta/
                ├── OfertasApplication.java    # Classe principal (entrada do app)
                ├── OfertasController.java     # Controlador da interface (FXML)
                ├── Oferta.java                # Modelo de dados (entidade)
                ├── OfertasService.java        # Interface do serviço
                ├── OfertaCSVService.java      # Implementação do serviço com CSV
                └── Launcher.java              # Launcher para ambientes sem module-path
└── target/                                # Output do build
```

## Modelo de Dados

Cada oferta possui os seguintes campos:

| Campo   | Tipo     | Descrição              |
|---------|----------|------------------------|
| id      | int      | Identificador único    |
| produto | String   | Nome do produto        |
| preco   | double   | Preço do produto       |
| frete   | double   | Custo de frete         |
| prazo   | int      | Prazo de entrega (dias)|

## Licença

Projeto pessoal — sem licença específica.
