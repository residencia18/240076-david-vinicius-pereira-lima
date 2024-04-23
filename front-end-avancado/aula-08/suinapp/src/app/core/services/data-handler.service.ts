import { Injectable } from '@angular/core';
import { DataBaseService } from './database.service';
import { Observable, catchError, map, of, switchMap } from 'rxjs';
import { PIG_COLLECTION, SESSAO_COLLECTION, WEIGHT_COLLECTION } from '../../shared/shared.module';
import { HistoricoAnimal, HistoricoItem } from '../../models/interfaces/historico.interface';

@Injectable({
  providedIn: 'root'
})
export class DataHandlerService {

  constructor(private databaseService: DataBaseService) { }

  /**
  * Obtém todos os brincos cadastrados no sistema para exibição no dropdown.
  */
  getAllBrincos(): Observable<any[]> {
    return this.databaseService.getAll(PIG_COLLECTION).pipe(
      map(response => Object.values(response).map((item: any) => item.id)),
      catchError(error => {
        console.error(error);
        return [];
      })
    );
  }

  /**
  * Retorna o histórico de um animal com base no brinco selecionado.
  * 1. Obtém as pesagens do animal.
  * 2. Combina as pesagens com as tarefas das sessões das quais o animal participou.
  * 3. Atualiza o histórico do animal com todos os itens obtidos.
  * @param brincoSelecionado O número do brinco do animal.
  * @returns Um Observable contendo o histórico do animal.
  */
  getHistoricoAnimal(brincoSelecionado: number): Observable<HistoricoAnimal> {
    console.log("Buscando pesagens para o brinco: " + brincoSelecionado);
    return this.databaseService.getByColumn(brincoSelecionado.toString(), WEIGHT_COLLECTION, 'pig').pipe(
      switchMap(response => this.mapearPesagens(response)),
      switchMap(pesagens => this.combinarComSessoes(pesagens, brincoSelecionado)),
      map(historico => ({ brinco: brincoSelecionado, historico: historico.length > 0 ? this.ordernarHistorico(historico) : undefined }))
    );
  }


  /**
   * Mapeia as pesagens do animal a partir da resposta recebida.
   * @param response - A resposta contendo as pesagens do animal.
   * @returns Um Observable contendo um array de objetos representando as pesagens mapeadas.
   */
  mapearPesagens(response: any): Observable<HistoricoItem[]> {
    const pesagens = Object.keys(response).map((key: any) => {
      const pesagem = response[key];
      return {
        data: new Date(pesagem.date).toLocaleDateString(),
        atividade: 'Pesagem',
        resultado: pesagem.weight + 'kg',
      };
    });
    return of(pesagens);
  }

  /**
  * Combina as pesagens com as sessões, retornando um Observable com o histórico de itens atualizado.
  * @param pesagens As pesagens a serem combinadas com as sessões.
  * @returns Um Observable contendo o histórico de itens atualizado.
  */
  combinarComSessoes(pesagens: HistoricoItem[], brincoSelecionado: number): Observable<HistoricoItem[]> {
    console.log("Buscando sessões para o brinco: " + brincoSelecionado);
    return this.databaseService.getAll(SESSAO_COLLECTION).pipe(
      map(sessoes => {
        const historicoPesagens = [...pesagens];
        Object.keys(sessoes).forEach((key: any) => {
          const sessao = sessoes[key];
          historicoPesagens.push(...this.filtrarEMapearTarefas(sessao, brincoSelecionado));
        });
        return historicoPesagens;
      })
    );
  }


  /**
   * Ordena o histórico de itens por data.
   * @param historico O array de itens do histórico a ser ordenado.
   * @returns O array de itens do histórico ordenado por data.
   */
  ordernarHistorico(historico: HistoricoItem[]): HistoricoItem[] {
    return historico.sort((a, b) => {
      var dataA = a.data.split('/').reverse().join('-');
      var dataB = b.data.split('/').reverse().join('-');
      return new Date(dataA).getTime() - new Date(dataB).getTime();
    });
  }

  /**
  * Filtra e mapeia as tarefas de uma sessão para o formato de HistoricoItem.
  * @param sessao A sessão contendo as tarefas a serem filtradas e mapeadas.
  * @param brincoSelecionado O brinco selecionado para filtrar as tarefas.
  * @returns Um array de HistoricoItem representando as tarefas filtradas e mapeadas.
  */
  filtrarEMapearTarefas(sessao: any, brincoSelecionado: number): HistoricoItem[] {
    if (sessao && sessao.tarefas) {
      return sessao.tarefas
        .filter((tarefa: any) => tarefa.brinco === brincoSelecionado)
        .map((tarefa: any) => ({
          data: new Date(sessao.data).toLocaleDateString(),
          atividade: tarefa.descricao,
          resultado: tarefa.status ? 'Concluído' : 'Pendente',
        }));
    }
    return [];
  }
}
