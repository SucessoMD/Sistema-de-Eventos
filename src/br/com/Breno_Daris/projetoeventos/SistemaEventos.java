package br.com.Breno_Daris.projetoeventos;

import br.com.Breno_Daris.projetoeventos.models.Categoria;
import br.com.Breno_Daris.projetoeventos.models.Evento;
import br.com.Breno_Daris.projetoeventos.models.Usuario;
import br.com.Breno_Daris.projetoeventos.utils.EventFileManager;
import br.com.Breno_Daris.projetoeventos.utils.UserFileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class SistemaEventos {
    private List<Evento> eventos;
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private Scanner scanner;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public SistemaEventos() {
        this.eventos = EventFileManager.carregarEventos();
        this.usuarios = UserFileManager.carregarUsuarios();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            if (usuarioLogado == null) {
                executando = menuInicial();
            } else {
                menuPrincipal();
            }
        }
        scanner.close();
        EventFileManager.salvarEventos(eventos);
        UserFileManager.salvarUsuarios(usuarios);
    }

    private boolean menuInicial() {
        System.out.println("\n=== Sistema de Eventos ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Cadastrar Novo Usuário");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                fazerLogin();
                return true;
            case 2:
                cadastrarUsuario();
                return true;
            case 3:
                return false;
            default:
                System.out.println("Opção inválida!");
                return true;
        }
    }

    private void menuPrincipal() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Cadastrar Novo Evento");
        System.out.println("2. Listar Todos os Eventos");
        System.out.println("3. Listar Eventos Atuais");
        System.out.println("4. Listar Eventos Futuros");
        System.out.println("5. Listar Eventos Passados");
        System.out.println("6. Meus Eventos Inscritos");
        System.out.println("7. Buscar Eventos por Categoria");
        System.out.println("8. Logout");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                cadastrarEvento();
                break;
            case 2:
                listarEventos(eventos);
                break;
            case 3:
                listarEventosAtuais();
                break;
            case 4:
                listarEventosFuturos();
                break;
            case 5:
                listarEventosPassados();
                break;
            case 6:
                gerenciarEventosInscritos();
                break;
            case 7:
                buscarEventosPorCategoria();
                break;
            case 8:
                logout();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrarUsuario() {
        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        String email = null;
        boolean emailExiste;
        do {
            System.out.print("Email: ");
            String emailInput = scanner.nextLine();
            emailExiste = usuarios.stream().anyMatch(u -> u.getEmail().equals(emailInput));
            if (emailExiste) {
                System.out.println("Este email já está cadastrado. Por favor, use outro email.");
            } else {
                email = emailInput;
            }
        } while (emailExiste);

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, email, senha, cidade, telefone);
        usuarios.add(novoUsuario);
        UserFileManager.salvarUsuarios(usuarios);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void fazerLogin() {
        System.out.println("\n=== Login ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                System.out.println("Login realizado com sucesso!");
                return;
            }
        }
        System.out.println("Email ou senha incorretos!");
    }

    private void logout() {
        usuarioLogado = null;
        System.out.println("Logout realizado com sucesso!");
    }

    private void cadastrarEvento() {
        System.out.println("\n=== Cadastro de Evento ===");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        Categoria categoria = null;
        while (categoria == null) {
            try {
                System.out.println("\nCategorias disponíveis:");
                Categoria[] categorias = Categoria.values();
                for (int i = 0; i < categorias.length; i++) {
                    System.out.printf("%d. %s%n", i + 1, categorias[i].getDescricao());
                }
                System.out.print("\nEscolha a categoria (1-" + categorias.length + "): ");
                int escolha = Integer.parseInt(scanner.nextLine().trim());
                
                if (escolha >= 1 && escolha <= categorias.length) {
                    categoria = categorias[escolha - 1];
                } else {
                    System.out.println("Por favor, escolha um número entre 1 e " + categorias.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }

        LocalDateTime horario = null;
        while (horario == null) {
            System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
            String dataHoraStr = scanner.nextLine().trim();
            try {
                horario = LocalDateTime.parse(dataHoraStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data/hora inválido! Use o formato dd/MM/yyyy HH:mm");
            }
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(novoEvento);
        EventFileManager.salvarEventos(eventos);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void listarEventos(List<Evento> eventosParaListar) {
        if (eventosParaListar.isEmpty()) {
            System.out.println("Nenhum evento encontrado!");
            return;
        }

        List<Evento> eventosOrdenados = new ArrayList<>(eventosParaListar);
        eventosOrdenados.sort(Comparator.comparing(Evento::getHorario));

        System.out.println("\n=== Lista de Eventos ===");
        for (int i = 0; i < eventosOrdenados.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + eventosOrdenados.get(i));
        }

        System.out.println("\nDeseja participar de algum evento? (S/N)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Digite o número do evento: ");
            int numeroEvento = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (numeroEvento > 0 && numeroEvento <= eventosOrdenados.size()) {
                Evento eventoSelecionado = eventosOrdenados.get(numeroEvento - 1);
                eventoSelecionado.adicionarParticipante(usuarioLogado);
                System.out.println("Inscrição realizada com sucesso!");
            } else {
                System.out.println("Número de evento inválido!");
            }
        }
    }

    private void listarEventosAtuais() {
        List<Evento> eventosAtuais = eventos.stream()
                .filter(Evento::isOcorrendoAgora)
                .toList();
        System.out.println("\n=== Eventos Acontecendo Agora ===");
        listarEventos(eventosAtuais);
    }

    private void listarEventosFuturos() {
        LocalDateTime agora = LocalDateTime.now();
        List<Evento> eventosFuturos = eventos.stream()
                .filter(e -> e.getHorario().isAfter(agora))
                .toList();
        System.out.println("\n=== Eventos Futuros ===");
        listarEventos(eventosFuturos);
    }

    private void listarEventosPassados() {
        List<Evento> eventosPassados = eventos.stream()
                .filter(Evento::jaOcorreu)
                .toList();
        System.out.println("\n=== Eventos Passados ===");
        listarEventos(eventosPassados);
    }

    private void gerenciarEventosInscritos() {
        List<Evento> eventosInscritos = usuarioLogado.getEventosInscritos();
        if (eventosInscritos.isEmpty()) {
            System.out.println("Você não está inscrito em nenhum evento!");
            return;
        }

        System.out.println("\n=== Meus Eventos ===");
        for (int i = 0; i < eventosInscritos.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + eventosInscritos.get(i));
        }

        System.out.println("\nDeseja cancelar a participação em algum evento? (S/N)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Digite o número do evento: ");
            int numeroEvento = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (numeroEvento > 0 && numeroEvento <= eventosInscritos.size()) {
                Evento eventoSelecionado = eventosInscritos.get(numeroEvento - 1);
                eventoSelecionado.removerParticipante(usuarioLogado);
                System.out.println("Participação cancelada com sucesso!");
            } else {
                System.out.println("Número de evento inválido!");
            }
        }
    }

    private void buscarEventosPorCategoria() {
        Categoria categoriaSelecionada = null;
        while (categoriaSelecionada == null) {
            try {
                System.out.println("\n=== Buscar por Categoria ===");
                System.out.println("Categorias disponíveis:");
                Categoria[] categorias = Categoria.values();
                for (int i = 0; i < categorias.length; i++) {
                    System.out.printf("%d. %s%n", i + 1, categorias[i].getDescricao());
                }
                System.out.print("\nEscolha a categoria (1-" + categorias.length + "): ");
                int escolha = Integer.parseInt(scanner.nextLine().trim());
                
                if (escolha >= 1 && escolha <= categorias.length) {
                    categoriaSelecionada = categorias[escolha - 1];
                } else {
                    System.out.println("Por favor, escolha um número entre 1 e " + categorias.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }

        final Categoria categoriaFinal = categoriaSelecionada;
        List<Evento> eventosDaCategoria = eventos.stream()
                .filter(e -> e.getCategoria() == categoriaFinal)
                .toList();
        
        System.out.println("\n=== Eventos da Categoria " + categoriaFinal.getDescricao() + " ===");
        listarEventos(eventosDaCategoria);
    }
}