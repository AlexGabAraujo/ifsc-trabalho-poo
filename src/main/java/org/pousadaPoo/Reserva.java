package org.pousadaPoo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private List<ServicoExtra> servicosExtras;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        if (hospede == null) throw new IllegalArgumentException("Hóspede inválido.");
        if (quarto == null) throw new IllegalArgumentException("Quarto inválido.");
        if (dataEntrada == null || dataSaida == null) throw new IllegalArgumentException("Datas inválidas.");
        if (!dataSaida.isAfter(dataEntrada)) throw new IllegalArgumentException("Data de saída deve ser após a entrada.");

        this.hospede = hospede;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.servicosExtras = new ArrayList<>();
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public List<ServicoExtra> getServicosExtras() {
        return servicosExtras;
    }

    public void setServicosExtras(List<ServicoExtra> servicosExtras) {
        this.servicosExtras = servicosExtras;
    }

    public void adicionarServicoExtra(ServicoExtra servico) {
        if (servico == null) throw new IllegalArgumentException("Serviço inválido.");
        servicosExtras.add(servico);
    }

    public double calcularTotal() {
        double total = 0;
        // Supondo que o valor do quarto seja por diária
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        total += quarto.getPreco() * dias;
        for (ServicoExtra s : servicosExtras) {
            total += s.getPreco();
        }
        return total;
    }
}
