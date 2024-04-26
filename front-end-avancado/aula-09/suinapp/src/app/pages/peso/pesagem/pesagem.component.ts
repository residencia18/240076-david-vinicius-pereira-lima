import { Component, NgZone, OnInit } from "@angular/core";
import { PrimeNGConfig } from "primeng/api";
import { DataBaseService } from "../../../core/services/database.service";
import { ActivatedRoute, Router } from "@angular/router";
import { WEIGHT_COLLECTION } from "../../../shared/constants";
import { DynamicDialogConfig } from "primeng/dynamicdialog";
import { Weight } from "../../../models/pig.model";
import { throwError } from "rxjs";

@Component({
  selector: "app-pesagem",
  templateUrl: "./pesagem.component.html",
  styleUrls: ["./pesagem.component.css"],
})
export class PesagemComponent implements OnInit {
  pesos: any;
  id: string | null = null;
  chartData: any;
  chartOptions: any;
  hasWeight = true;

  constructor(
    private router: Router,
    private databaseService: DataBaseService,
    private route: ActivatedRoute,
    private ngZone: NgZone,
    private config: DynamicDialogConfig
  ) {}

  ngOnInit(): void {
    this.id = this.config.data?.id || this.route.snapshot.paramMap.get("id");

    this.databaseService
      .getByColumn(this.id!, WEIGHT_COLLECTION, "pig")
      .subscribe({
        next: (response) => {
          this.pesos = this.handleWeightData(response);
          this.hasWeight = this.pesos !== null;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => {
          if (this.hasWeight) {
            this.chartData = this.getChartData();
            this.chartOptions = this.getChartOptions();
          }
          console.log("Pesos carregados");
        },
      });
  }

  /**
   * Lida com os dados de peso recebidos do banco de dados e os transforma em um array de objetos.
   * @param response - A resposta contendo os dados de peso.
   * @returns Um array de objetos representando os pesos do animal.
   */
  handleWeightData(response: any): Weight[] | null {
    if (Object.values(response).length === 0) {
      return null;
    }

    return Object.keys(response).map((key: string) => ({
      id: key,
      ...response[key],
    }));
  }

  /**
   * Recupera os dados do gráfico para exibir o peso de um suíno com um ID específico.
   * @returns Um objeto contendo os dados do gráfico.
   */
  getChartData(): any {
    return {
      labels: this.pesos.map((peso: any) =>
        new Date(peso.date).toLocaleDateString()
      ),
      datasets: [
        {
          label: "Peso do Suino de Brinco: " + this.id,
          data: this.pesos.map((peso: any) => peso.weight),
          fill: true,
          borderColor: "#4bc0c0",
        },
      ],
    };
  }

  /**
   * Retorna as opções do gráfico para o componente de pesagem e associa o evento de clique em um ponto do gráfico.
   * @returns Um objeto contendo as opções do gráfico.
   */
  getChartOptions(): any {
    return {
      responsive: true,
      maintainAspectRatio: false,
      aspectRatio: 0.6,
      onClick: this.onChartPointClick.bind(this),
      scales: {
        x: {
          title: {
            display: true,
            text: "Data",
          },
        },
        y: {
          title: {
            display: true,
            text: "Peso",
          },
        },
      },
    };
  }

  /**
   * Lida com o evento de clique em um ponto do gráfico e redireciona para a página de edição do peso.
   * @param event - O evento de clique no gráfico.
   * @param items - Os itens clicados no gráfico.
   */
  onChartPointClick(event: any, items: any) {
    console.log(items);
    if (items.length > 0) {
      const index = items[0].index;

      this.ngZone.run(() => {
        this.router.navigate(["/edicao-peso", this.pesos[index].id]);
      });
    }
  }
}
