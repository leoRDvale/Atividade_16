import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

/*
        Escreva um programa que leia um grupo del inhas de entrda. Cada linha contém um ‘C’, de chegada, e um ‘P’ de partida, além de um número de placa de licenciamento.
        Presume-se que os carros chegarão e partirão na ordem especificada pela entrada. O programa deve imprimir uma mensagem cada vez que um carro chegar ou partir.
        Quando um carro chegar, a mensagem deverá especificar se existe ou não vaga para o carro dentro do estacionamento. Se não existir vaga, o carro esperará pela vaga ou até que uma linha de partida seja lida para o carro. Quando houver espaço disponível, outra mensagem deverá ser impressa. Quando um carro partir, a mensagem deverá incluir o número de vezes que o carro foi deslocado dentro do estacionamento, incluindo a própria partida, mas não a chegada. Esse número será 0 se o carro for embora a partir da linha de espera.
         */

public class Main {
    public static void main(String[] args) {

        Queue<String> estacionamento = new LinkedList<>();
        Queue<String> filaDeEspera = new LinkedList<>();
        int limiteEstacionamento = 10;

        // C - Chegada de carro
        // P - Partida de carro
        String[] comandos;
        do {
            comandos = JOptionPane.showInputDialog("Digite os comandos C (Chegada) ou P (Partida) seguido da placa do carro): \nExemplo: C 1234").split(";");

        for (String comando : comandos) {
            String[] partes = comando.trim().split(" ");
            char tipo = partes[0].charAt(0);
            String placa = partes[1];

            if (tipo == 'C') { // Chegada de carro
                if (estacionamento.size() < limiteEstacionamento) {
                    estacionamento.add(placa);
                    JOptionPane.showMessageDialog(null, "Carro " + placa + " chegou e estacionou.");
                } else {
                    filaDeEspera.add(placa);
                    JOptionPane.showMessageDialog(null, "Carro " + placa + " chegou, mas não há vaga. Está na fila de espera.");
                }
            } else if (tipo == 'P') { // Partida de carro
                if (estacionamento.contains(placa)) {
                    int deslocamentos = 0;
                    while (!estacionamento.peek().equals(placa)) {
                        filaDeEspera.add(estacionamento.poll());
                        deslocamentos++;
                    }
                    estacionamento.poll(); // Remove o carro da saída
                    JOptionPane.showMessageDialog(null, "Carro " + placa + " partiu após " + deslocamentos + " deslocamentos.");

                    // Se há carros na fila de espera, coloca o primeiro da fila no estacionamento
                    if (!filaDeEspera.isEmpty()) {
                        String carroEsperando = filaDeEspera.poll();
                        estacionamento.add(carroEsperando);
                        JOptionPane.showMessageDialog(null, "Carro " + carroEsperando + " entrou no estacionamento.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Carro " + placa + " não estava no estacionamento.");
                }
            }
        }
    }while (JOptionPane.showConfirmDialog(null, "Deseja continuar?") == JOptionPane.YES_OPTION);
    }
}