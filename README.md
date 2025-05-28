
# ProjetoEventos

Este é um sistema de gerenciamento de eventos desenvolvido em Java, com foco em operações de leitura e escrita de dados em arquivos. O projeto permite cadastrar, listar e gerenciar eventos e usuários.

```## 📁 Estrutura do Projeto


ProjetoEventos/
├── events.data                # Dados dos eventos salvos
├── users.data                 # Dados dos usuários salvos
├── README.md                  # Documentação do projeto
├── requirements.txt           # Requisitos (ex: ambiente Java)
└── src/
    └── br/com/Breno_Daris/projetoeventos/
        ├── Main.java                     # Classe principal (ponto de entrada)
        ├── SistemaEventos.java           # Lógica principal do sistema
        ├── models/
        │   ├── Categoria.java            # Representa a categoria de um evento
        │   ├── Evento.java               # Representa um evento
        │   └── Usuario.java              # Representa um usuário
        └── utils/
            ├── ArquivoUtils.java         # Utilitários para manipulação de arquivos
            ├── EventFileManager.java     # Gerenciador de arquivos de eventos ```


## ⚙️ Tecnologias Utilizadas

- **Java 8+**
- Manipulação de arquivos `.data` para persistência local
- Estrutura orientada a objetos com pacotes organizados

## 🚀 Como Executar

1. Certifique-se de ter o **Java JDK** instalado (versão 8 ou superior).
2. Compile os arquivos `.java` localizados em `src/`:
   ```bash
   javac src/br/com/Breno_Daris/projetoeventos/**/*.java
   ```
3. Execute o programa a partir da classe `Main`:
   ```bash
   java -cp src br.com.Breno_Daris.projetoeventos.Main
   ```

## 📝 Funcionalidades

- Cadastro de eventos com categorias
- Cadastro de usuários
- Leitura e escrita dos dados em arquivos `.data`
- Estrutura modular com boas práticas de organização

## 📌 Requisitos

O arquivo `requirements.txt` pode conter observações sobre o ambiente necessário, como:

```
Java JDK 8 ou superior
```

## 🔧 Melhorias Futuras (TODO)

- Interface gráfica (GUI) com JavaFX ou Swing
- Armazenamento com banco de dados (ex: SQLite, PostgreSQL)
- Sistema de autenticação mais robusto
- Exportação de relatórios em PDF/CSV

---

Desenvolvido por **Breno Daris** 📚
```
