import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { Observable, map } from 'rxjs';
import { HistoricoAnimal, HistoricoItem } from '../../../models/interfaces/historico.interface';
import { DataHandlerService } from '../../../core/services/data-handler.service';
import { ButtonModule } from 'primeng/button';
import { DialogService, DynamicDialogModule, DynamicDialogRef } from 'primeng/dynamicdialog';
import { PesagemComponent } from '../../peso/pesagem/pesagem.component';
import { GraficoAtividadeComponent } from '../grafico-atividade/grafico-atividade.component';

@Component({
  selector: 'app-historico-animal',
  standalone: true,
  imports: [
    CommonModule,
    PanelModule,
    TableModule,
    DropdownModule,
    FormsModule,
    ButtonModule,
    DynamicDialogModule,
  ],
  templateUrl: './historico-animal.component.html',
  styleUrls: ['./historico-animal.component.css'],
  providers: [DialogService]
})
export class HistoricoAnimalComponent implements OnInit {
  historicoAnimal$: Observable<HistoricoAnimal | undefined>;
  brincoSelecionado: number | undefined;
  brincos$: Observable<any[]>;
  listaBrincos: any[] = [];
  brincoInvalido = false;
  refPesagem: DynamicDialogRef | undefined;
  refAtividades: DynamicDialogRef | undefined;

  constructor(
    private dataHandlerService: DataHandlerService,
    private dialogService: DialogService
  ) {
    this.historicoAnimal$ = new Observable(); // Inicializa o Observable
    this.brincos$ = this.dataHandlerService.getAllBrincos();
  }

  ngOnInit(): void {
    this.brincos$
      .pipe(map((brincos: any[]) => (this.listaBrincos = brincos)))
      .subscribe();
  }

  /**
   * Atualiza o brinco selecionado e carrega o histórico do animal.
   * @param event - O evento de mudança do dropdown.
   */
  onBrincoChange(event: any): void {
    const brinco = parseInt(event.value, 10);
    if (!isNaN(event.value) && this.listaBrincos.includes(brinco)) {
      this.brincoInvalido = false;
      this.brincoSelecionado = brinco;
      this.historicoAnimal$ = this.dataHandlerService.getHistoricoAnimal(
        this.brincoSelecionado
      );
    } else {
      this.brincoInvalido = true; // Exibe mensagem de erro
      this.historicoAnimal$ = new Observable(); // Reseta o Observable se o brinco não for um número
    }
  }

  /**
   * Abre o diálogo mostrar o historico de pesagem.
   */
  showHistoricoPesagem(): void {
    const dataToSend = this.brincoSelecionado ? { id: this.brincoSelecionado } : undefined;

    this.refPesagem = this.dialogService.open(PesagemComponent, {
      header: 'Histórico de Pesagem',
      width: '50%',
      contentStyle: { 'max-height': '500px', 'overflow': 'auto' },
      baseZIndex: 10000,
      data: dataToSend
    });
  }

  /**
   * Abre o diálogo de atividades e mapeia o histórico de atividades, onde cada atividade é um objeto com nome e quantidade.
   */
  showHistoricoAtividades(): void {
    this.historicoAnimal$.subscribe(
      (historicoAnimal: HistoricoAnimal | undefined) => {
        if (!historicoAnimal?.historico) {
          historicoAnimal = {
            brinco: this.brincoSelecionado ?? 0,
            historico: [],
          };
        }

        this.refAtividades = this.dialogService.open(
          GraficoAtividadeComponent,
          {
            header: "Histórico de Atividades",
            width: "50%",
            contentStyle: { "max-height": "500px", overflow: "auto" },
            data: historicoAnimal,
          }
        );
        
      }
    );
  }
}
