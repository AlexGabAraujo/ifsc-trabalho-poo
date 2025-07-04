package org.pousadaPoo;
import org.pousadaPoo.Interfaces.MainInterface;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main implements MainInterface {
    public static void main(String[] args) {
        Main app = new Main();
        Scanner sc = new Scanner(System.in);

        ArrayList<Hospede> hospedes = new ArrayList<>();
        ArrayList<Quarto> quartos = new ArrayList<>();
        ArrayList<Reserva> reservas = new ArrayList<>();
        ArrayList<ServicoExtra> servicosExtrasDisponiveis = new ArrayList<>();

        app.AdicionarDadosExemplo(hospedes, quartos, servicosExtrasDisponiveis, reservas);

        try {
            app.MostrarMenu(hospedes, quartos, reservas, servicosExtrasDisponiveis);
        } catch (Exception ex) {
            System.out.println("\nErro: " + ex.getMessage());
            System.out.println("\nAperte 'Enter' Para Tentar Novamente.");
            sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            app.MostrarMenu(hospedes, quartos, reservas, servicosExtrasDisponiveis);
        }
    }

    @Override
    public void MostrarMenu(List<Hospede> hospedes, List<Quarto> quartos, List<Reserva> reservas, List<ServicoExtra> servicosExtrasDisponiveis) {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("""
                -----------Bem Vindo ao Sistema da Pousada 'Luz da Lua'-----------
                Escolha uma opção:

                1- Adicionar um novo Hóspede.
                2- Visualizar Todos os Hóspedes.
                3- Cadastrar Quarto.
                4- Listar Quartos.
                5- Criar Reserva.
                6- Adicionar Serviços Extras a Reserva.
                7- Listar Reservas.
                8- Consultar Hóspedes por Nome.
                9- Sair.

                """);

            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.println("------------------------------------------\n");

            switch (opcao) {
                case 1:
                    AdicionarHospede(hospedes, sc);
                    break;
                case 2:
                    ListarHospedes(hospedes);
                    break;
                case 3:
                    CadastrarQuarto(quartos, sc);
                    break;
                case 4:
                    ListarQuartos(quartos);
                    break;
                case 5:
                    CriarReserva(reservas, hospedes, quartos, servicosExtrasDisponiveis, sc);
                    break;
                case 6:
                    AdicionarServicoExtraReserva(reservas, servicosExtrasDisponiveis, sc);
                    break;
                case 7:
                    ListarReservas(reservas);
                    break;
                case 8:
                    ConsultarHospedesPorNome(hospedes, sc);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println("\nAperte 'Enter' Para Continuar.");
            sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();

        } while (opcao != 9);
    }

    @Override
    public void AdicionarHospede(List<Hospede> hospedes, Scanner sc) {
        try {
            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) throw new IllegalArgumentException("O nome não pode estar vazio.");

            System.out.print("CPF (somente números): ");
            String cpf = sc.nextLine().trim();
            if (!cpf.matches("\\d{11}")) throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos numéricos.");

            System.out.print("Contato: ");
            String contato = sc.nextLine().trim();
            if (contato.isEmpty()) throw new IllegalArgumentException("O contato não pode estar vazio.");

            System.out.print("Endereço: ");
            String endereco = sc.nextLine().trim();
            if (endereco.isEmpty()) throw new IllegalArgumentException("O endereço não pode estar vazio.");

            Hospede novoHospede = new Hospede(nome, cpf, contato, endereco);
            hospedes.add(novoHospede);

            System.out.println("\nHóspede cadastrado com sucesso!");
        } catch (IllegalArgumentException ex) {
            System.out.println("\nErro ao cadastrar hóspede: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("\nErro inesperado: " + ex.getMessage());
        }
    }

    @Override
    public void ListarHospedes(List<Hospede> hospedes) {
        if (hospedes.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado.");
            return;
        }

        System.out.println("Lista de Hóspedes Cadastrados:\n");

        for (int i = 0; i < hospedes.size(); i++) {
            Hospede h = hospedes.get(i);
            System.out.println("Hóspede #" + (i + 1));
            System.out.println("Nome: " + h.getNome());
            System.out.println("CPF: " + h.getCpf());
            System.out.println("Contato: " + h.getContato());
            System.out.println("Endereço: " + h.getEndereco());
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void CadastrarQuarto(List<Quarto> quartos, Scanner sc) {
        try {
            System.out.print("Número do quarto: ");
            int numero = Integer.parseInt(sc.nextLine());

            System.out.print("Tipo (solteiro, casal, suíte, etc.): ");
            String tipo = sc.nextLine().trim();
            if (tipo.isEmpty()) throw new IllegalArgumentException("Tipo de quarto não pode ser vazio.");

            System.out.print("Preço por noite: ");
            double preco = Double.parseDouble(sc.nextLine());
            if (preco <= 0) throw new IllegalArgumentException("Preço inválido.");

            System.out.print("Está disponível? (true/false): ");
            boolean disponivel = Boolean.parseBoolean(sc.nextLine());

            Quarto novoQuarto = new Quarto(numero, tipo, preco, disponivel);
            quartos.add(novoQuarto);
            Collections.sort(quartos, Comparator.comparingInt(Quarto::getNumero));
            System.out.println("\nQuarto cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato numérico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public void ListarQuartos(List<Quarto> quartos) {
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado.");
            return;
        }

        System.out.println("Lista de Quartos Cadastrados:\n");

        for (int i = 0; i < quartos.size(); i++) {
            Quarto q = quartos.get(i);
            System.out.println("Quarto #" + (i + 1));
            System.out.println("Número: " + q.getNumero());
            System.out.println("Tipo: " + q.getTipo());
            System.out.println("Preço: R$ " + q.getPreco());
            System.out.println("Disponível: " + (q.isDisponivel() ? "Sim" : "Não"));
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void CriarReserva(List<Reserva> reservas, List<Hospede> hospedes, List<Quarto> quartos, List<ServicoExtra> servicosExtrasDisponiveis, Scanner sc) {
        try {
            if (hospedes.isEmpty()) {
                System.out.println("Não há hóspedes cadastrados. Cadastre um hóspede primeiro.");
                return;
            }
            if (quartos.isEmpty()) {
                System.out.println("Não há quartos cadastrados. Cadastre um quarto primeiro.");
                return;
            }

            System.out.println("Escolha o hóspede pelo número:");
            for (int i = 0; i < hospedes.size(); i++) {
                System.out.printf("%d - %s\n", i + 1, hospedes.get(i).getNome());
            }
            int hospedeIndex = Integer.parseInt(sc.nextLine()) - 1;
            if (hospedeIndex < 0 || hospedeIndex >= hospedes.size()) {
                System.out.println("Hóspede inválido.");
                return;
            }
            Hospede hospede = hospedes.get(hospedeIndex);

            System.out.println("Escolha o quarto pelo número:");
            for (int i = 0; i < quartos.size(); i++) {
                Quarto q = quartos.get(i);
                System.out.printf("%d - Quarto %d (%s) - R$ %.2f - %s\n", i + 1, q.getNumero(), q.getTipo(), q.getPreco(), q.isDisponivel() ? "Disponível" : "Indisponível");
            }
            int quartoIndex = Integer.parseInt(sc.nextLine()) - 1;
            if (quartoIndex < 0 || quartoIndex >= quartos.size()) {
                System.out.println("Quarto inválido.");
                return;
            }
            Quarto quarto = quartos.get(quartoIndex);
            if (!quarto.isDisponivel()) {
                System.out.println("Quarto não está disponível.");
                return;
            }

            System.out.print("Data de entrada (yyyy-MM-dd): ");
            LocalDate dataEntrada = LocalDate.parse(sc.nextLine());

            System.out.print("Data de saída (yyyy-MM-dd): ");
            LocalDate dataSaida = LocalDate.parse(sc.nextLine());

            Reserva reserva = new Reserva(hospede, quarto, dataEntrada, dataSaida);
            reservas.add(reserva);

            quarto.setDisponivel(false);

            System.out.println("Reserva criada com sucesso!");

            if (!servicosExtrasDisponiveis.isEmpty()) {
                System.out.println("\nDeseja adicionar serviços extras à reserva? (s/n)");
                String resposta = sc.nextLine().trim().toLowerCase();
                while (resposta.equals("s")) {
                    System.out.println("Serviços extras disponíveis:");
                    for (int i = 0; i < servicosExtrasDisponiveis.size(); i++) {
                        ServicoExtra s = servicosExtrasDisponiveis.get(i);
                        System.out.printf("%d - %s - R$ %.2f\n", i + 1, s.getNome(), s.getPreco());
                    }
                    System.out.print("Escolha o serviço extra para adicionar (0 para sair): ");
                    int servicoIndex = Integer.parseInt(sc.nextLine()) - 1;
                    if (servicoIndex == -1) {
                        break;
                    }
                    if (servicoIndex < 0 || servicoIndex >= servicosExtrasDisponiveis.size()) {
                        System.out.println("Serviço inválido.");
                    } else {
                        reserva.adicionarServicoExtra(servicosExtrasDisponiveis.get(servicoIndex));
                        System.out.println("Serviço extra adicionado!");
                    }
                    System.out.println("Deseja adicionar outro serviço extra? (s/n)");
                    resposta = sc.nextLine().trim().toLowerCase();
                }
            } else {
                System.out.println("Nenhum serviço extra disponível para adicionar.");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public void AdicionarServicoExtraReserva(List<Reserva> reservas, List<ServicoExtra> servicosExtrasDisponiveis, Scanner sc) {
        try {
            if (reservas.isEmpty()) {
                System.out.println("Não há reservas cadastradas.");
                return;
            }
            System.out.println("Escolha a reserva para adicionar serviços:");
            for (int i = 0; i < reservas.size(); i++) {
                Reserva r = reservas.get(i);
                System.out.printf("%d - %s - Quarto %d (%s) - %s até %s\n",
                        i + 1,
                        r.getHospede().getNome(),
                        r.getQuarto().getNumero(),
                        r.getQuarto().getTipo(),
                        r.getDataEntrada(),
                        r.getDataSaida());
            }
            int reservaIndex = Integer.parseInt(sc.nextLine()) - 1;
            if (reservaIndex < 0 || reservaIndex >= reservas.size()) {
                System.out.println("Reserva inválida.");
                return;
            }
            Reserva reserva = reservas.get(reservaIndex);

            System.out.println("Serviços extras disponíveis:");
            for (int i = 0; i < servicosExtrasDisponiveis.size(); i++) {
                ServicoExtra s = servicosExtrasDisponiveis.get(i);
                System.out.printf("%d - %s - R$ %.2f\n", i + 1, s.getNome(), s.getPreco());
            }
            System.out.print("Escolha o serviço extra para adicionar (número): ");
            int servicoIndex = Integer.parseInt(sc.nextLine()) - 1;
            if (servicoIndex < 0 || servicoIndex >= servicosExtrasDisponiveis.size()) {
                System.out.println("Serviço inválido.");
                return;
            }

            reserva.adicionarServicoExtra(servicosExtrasDisponiveis.get(servicoIndex));
            System.out.println("Serviço extra adicionado à reserva!");
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    @Override
    public void ListarReservas(List<Reserva> reservas) {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.println("Lista de Reservas:\n");

        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println("Reserva #" + (i + 1));
            System.out.println("Hóspede: " + r.getHospede().getNome());
            System.out.println("Quarto: " + r.getQuarto().getNumero() + " (" + r.getQuarto().getTipo() + ")");
            System.out.println("Data Entrada: " + r.getDataEntrada());
            System.out.println("Data Saída: " + r.getDataSaida());

            if (r.getServicosExtras().isEmpty()) {
                System.out.println("Serviços Extras: Nenhum");
            } else {
                System.out.println("Serviços Extras:");
                for (ServicoExtra s : r.getServicosExtras()) {
                    System.out.printf("- %s (R$ %.2f)\n", s.getNome(), s.getPreco());
                }
            }

            System.out.printf("Total Estimado: R$ %.2f\n", r.calcularTotal());
            System.out.println("----------------------------------------");
        }
    }

    @Override
    public void ConsultarHospedesPorNome(List<Hospede> hospedes, Scanner sc) {
        System.out.print("Digite o nome (ou parte dele) do hóspede para busca: ");
        String busca = sc.nextLine().toLowerCase().trim();

        List<Hospede> encontrados = new ArrayList<>();
        for (Hospede h : hospedes) {
            if (h.getNome().toLowerCase().contains(busca)) {
                encontrados.add(h);
            }
        }

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum hóspede encontrado com o nome informado.");
        } else {
            System.out.println("Hóspedes encontrados:");
            for (Hospede h : encontrados) {
                System.out.println("- " + h.getNome() + " | CPF: " + h.getCpf() + " | Contato: " + h.getContato() + " | Endereço: " + h.getEndereco());
            }
        }
    }

    @Override
    public void AdicionarDadosExemplo(List<Hospede> hospedes, List<Quarto> quartos, List<ServicoExtra> servicosExtras, List<Reserva> reservas) {
        hospedes.add(new Hospede("Ana Silva", "123.456.789-00", "1199999-9999", "Rua das Flores, 123"));
        hospedes.add(new Hospede("Bruno Costa", "987.654.321-00", "1198888-8888", "Av. Paulista, 456"));
        hospedes.add(new Hospede("Carlos Lima", "111.222.333-44", "1197777-7777", "Rua do Sol, 789"));

        quartos.add(new Quarto(101, "Solteiro", 100.00, true));
        quartos.add(new Quarto(202, "Casal", 150.00, true));
        quartos.add(new Quarto(303, "Suíte", 250.00, true));
        quartos.add(new Quarto(306, "Suíte", 300.00, true));

        servicosExtras.add(new ServicoExtra("Café da Manhã", 30.0));
        servicosExtras.add(new ServicoExtra("Spa", 150.0));
        servicosExtras.add(new ServicoExtra("Lavanderia", 50.0));

        reservas.add(new Reserva(hospedes.get(0), quartos.get(0), LocalDate.now().plusDays(1), LocalDate.now().plusDays(5)));
        reservas.add(new Reserva(hospedes.get(1), quartos.get(1), LocalDate.now().plusDays(2), LocalDate.now().plusDays(6)));
        reservas.add(new Reserva(hospedes.get(2), quartos.get(2), LocalDate.now().plusDays(3), LocalDate.now().plusDays(7)));

        quartos.get(0).setDisponivel(false);
        quartos.get(1).setDisponivel(false);
        quartos.get(2).setDisponivel(false);

        System.out.println("Dados de exemplo adicionados para hóspedes, quartos, serviços extras e reservas.");
    }
}