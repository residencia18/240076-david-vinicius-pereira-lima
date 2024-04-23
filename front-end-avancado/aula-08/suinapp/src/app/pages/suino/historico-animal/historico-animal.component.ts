import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { Observable, map } from 'rxjs';
import { HistoricoAnimal, HistoricoItem } from '../../../models/interfaces/historico.interface';
import { DataHandlerService } from '../../../core/services/data-handler.service';

@Component({
  selector: 'app-historico-animal',
  standalone: true,
  imports: [
    CommonModule,
    PanelModule,
    TableModule,
    DropdownModule,
    FormsModule
  ],
  templateUrl: './historico-animal.component.html',
  styleUrls: ['./historico-animal.component.css']
})
export class HistoricoAnimalComponent implements OnInit {
  historicoAnimal$: Observable<HistoricoAnimal | undefined>;
  brincoSelecionado: number | undefined;
  brincos$: Observable<any[]>;
  listaBrincos: any[] = [];
  brincoInvalido = false;

  constructor(private dataHandlerService: DataHandlerService) {
    this.historicoAnimal$ = new Observable(); // Inicializa o Observable
    this.brincos$ = this.dataHandlerService.getAllBrincos();
  }

  ngOnInit(): void {
    this.brincos$.pipe(
      map(brincos => this.listaBrincos = brincos)
    ).subscribe();
  }

  /**
   * Atualiza o brinco selecionado e carrega o histórico do animal.
   * @param event - O evento de mudança do dropdown.
   */
  onBrincoChange(event: any): void {
    const brinco = parseInt(event.value, 10);
    if (!isNaN(event.value) && this.listaBrincos.includes(brinco)) {
      this.brincoInvalido = false;
      this.brincoSelecionado = parseInt(event.value, 10);
      this.historicoAnimal$ = this.dataHandlerService.getHistoricoAnimal(this.brincoSelecionado);
    } else {
      this.brincoInvalido = true; // Exibe mensagem de erro
      this.historicoAnimal$ = new Observable(); // Reseta o Observable se o brinco não for um número
    }
  }

}
