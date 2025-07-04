package org.pousadaPoo.Interfaces;
import org.pousadaPoo.Hospede;
import org.pousadaPoo.Quarto;
import org.pousadaPoo.Reserva;
import org.pousadaPoo.ServicoExtra;

import java.util.List;
import java.util.Scanner;

public interface MainInterface {
    void MostrarMenu(List<Hospede> hospedes, List<Quarto> quartos, List<Reserva> reservas, List<ServicoExtra> servicosExtrasDisponiveis);
    void AdicionarHospede(List<Hospede> hospedes, Scanner sc);
    void ListarHospedes(List<Hospede> hospedes);
    void CadastrarQuarto(List<Quarto> quartos, Scanner sc);
    void ListarQuartos(List<Quarto> quartos);
    void CriarReserva(List<Reserva> reservas, List<Hospede> hospedes, List<Quarto> quartos, List<ServicoExtra> servicosExtrasDisponiveis, Scanner sc);
    void AdicionarServicoExtraReserva(List<Reserva> reservas, List<ServicoExtra> servicosExtrasDisponiveis, Scanner sc);
    void ListarReservas(List<Reserva> reservas);
    void ConsultarHospedesPorNome(List<Hospede> hospedes, Scanner sc);
    void AdicionarDadosExemplo(List<Hospede> hospedes, List<Quarto> quartos, List<ServicoExtra> servicosExtras, List<Reserva> reservas);
}