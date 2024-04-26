import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { ChartModule } from "primeng/chart";
import { DynamicDialogConfig } from "primeng/dynamicdialog";
import { MessagesModule } from "primeng/messages";
import { PanelModule } from "primeng/panel";
import { HistoricoItem } from "../../../models/interfaces/historico.interface";

@Component({
  selector: "app-grafico-atividade",
  standalone: true,
  imports: [CommonModule, PanelModule, MessagesModule, ChartModule],
  templateUrl: "./grafico-atividade.component.html",
  styleUrls: ["./grafico-atividade.component.css"],
})
export class GraficoAtividadeComponent {
  atividades: any;
  chartData: any;
  chartOptions: any;

  constructor(private dynamicDialogConfig: DynamicDialogConfig) {
    const historicoAnimal = this.dynamicDialogConfig.data;
    this.atividades = this.getAtividades(historicoAnimal);
    this.chartData = this.getChartData(this.atividades);
    this.chartOptions = this.getChartOptions();
  }

  
  /**
   * Recupera as atividades do histórico do animal e conta o número de ocorrências de cada atividade.
   * @param historicoAnimal - O histórico do animal.
   * @returns Um objeto contendo o brinco do animal e as atividades contadas.
   */
  private getAtividades(historicoAnimal: any) {

    // Filtra as atividades concluídas
    const historicoConcluido = historicoAnimal.historico?.filter(
      (item: HistoricoItem) => item.resultado === "Concluído"
    );

    // Conta o número de ocorrências de cada atividade concluída
    const atividadesContadas = historicoConcluido?.reduce(
      (acc: any, item: HistoricoItem) => {
        if (acc[item.atividade]) {
          acc[item.atividade].quantidade++;
        } else {
          acc[item.atividade] = {
            nome: item.atividade,
            quantidade: 1,
          };
        }
        return acc;
      },
      {}
    );

    // Transforma o objeto em um array
    const historico = Object.values(atividadesContadas);

    return {
      brinco: historicoAnimal.brinco,
      historico: historico.length > 0 ? historico : undefined, // Retorna undefined se não houver atividades
    };
  }

  
  /**
   * Recupera os dados do gráfico a partir das atividades do histórico.
   * @param atividades - As atividades do histórico do animal.
   * @returns Os dados do gráfico.
   */
  private getChartData(atividades: any) {
    return atividades.historico
      ? {
          labels: atividades.historico.map((a: any) => a.nome), 
          datasets: [
            {
              label: "Quantidade de vezes",
              data: atividades.historico.map((a: any) => a.quantidade),
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)",
              ],
              borderColor: [
                "rgb(255, 99, 132)",
                "rgb(54, 162, 235)",
                "rgb(255, 206, 86)",
                "rgb(75, 192, 192)",
                "rgb(153, 102, 255)",
                "rgb(255, 159, 64)",
              ],
              borderWidth: 1,
            },
          ],
        }
      : undefined;
  }

  /**
   * Recupera as opções do gráfico.
   * @returns As opções do gráfico.
   */
  private getChartOptions() {
    return {
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            stepSize: 1,
            precision: 0,
          },
        },
      },
    };
  }
}