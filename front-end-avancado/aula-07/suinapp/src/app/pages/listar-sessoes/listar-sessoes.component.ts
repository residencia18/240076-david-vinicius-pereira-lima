import { Component, OnInit } from '@angular/core';
import { Sessao, Tarefa } from '../../models/sessao.model';
import { DataBaseService } from '../../services/database.service';
import { ConfirmationService } from 'primeng/api';
import { SESSAO_COLLECTION } from '../../shared/constants';

interface TarefasPorBrinco {
  [brinco: string]: Tarefa[];
}

@Component({
  selector: 'app-listar-sessoes',
  templateUrl: './listar-sessoes.component.html',
  styleUrls: ['./listar-sessoes.component.css']
})
export class ListarSessoesComponent implements OnInit {
  sessoes: Sessao[] = [];
  tarefasPorBrinco: { [sessaoId: string]: TarefasPorBrinco } = {};
  first = 0;
  rows = 1;
  editar = false;
  displayConfirm = false;
  statusListPreEdicao: boolean[] = [];
  progressoTarefas : number = 0;

  constructor(
    private databaseService: DataBaseService,
    private confirmationService: ConfirmationService
    ) { }

  ngOnInit() {
    this.carregarSessoes();
  }

  ngDoCheck() {
    if (this.editar) {
      // Atualiza o progresso das tarefas enquanto o usuário edita uma sessão.
      this.progressoTarefas  = this.calcularProgressoTarefas();
    }
  }

  carregarSessoes() {
    this.databaseService.getAll(SESSAO_COLLECTION).subscribe({
      next: (response: any[]) => {
        this.sessoes = Object.keys(response).map((key: any) => {
          return {
            id: key,
            ...response[key],
            data : new Date(response[key].data),
          };
        });
      },
      error: (error) => console.error(error),
      complete: () => this.organizarTarefasPorBrinco()
    });
  }


  organizarTarefasPorBrinco() {
    this.sessoes.forEach(sessao => {
      this.tarefasPorBrinco[sessao.id] = sessao.brincos.reduce((acc, brinco) => {
        acc[brinco] = sessao.tarefas.filter(tarefa => tarefa.brinco === brinco);
        return acc;
      }, {} as TarefasPorBrinco);
    });
  }


  onPageChange(event: any) {
    this.first = event.first;
    this.rows = event.rows;
  }

  confirmSalvar(id: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja salvar as alterações?',
      header: 'Confirmação',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Sim',
      rejectLabel: 'Não',
      rejectButtonStyleClass: 'p-button-secondary',
      accept: () => this.atualizarSessao(id),
      reject: () => this.gerenciarEditar(id, false)
    });
  }


  /**
   * Reverte as alterações feitas nos status das tarefas de uma sessão.
   * @param sessaoId O ID da sessão.
   */
  reverterAlteracoesDeStatus(sessaoId: string) {
    const tarefasModificaveis = this.tarefasPorBrinco[sessaoId];
    Object.keys(tarefasModificaveis).forEach(brinco => {
      tarefasModificaveis[brinco].forEach(tarefa => {
        const status = this.statusListPreEdicao.shift();
        if (status !== undefined) {
          tarefa.status = status;
        }
      });
    });
  }

  /**
   * Gerencia a ação de editar uma sessão.
   * 
   * @param sessaoId - O ID da sessão a ser editada ou salva.
   * @param editar - Um valor booleano indicando se o usuário deseja editar a sessão.
   */
  gerenciarEditar(sessaoId: string, editar: boolean) {
    if (editar){
      // Se o usuário clicou em "Editar", então ele deseja editar a sessão.
      this.salvarEstadoDasTarefas(sessaoId);
    } else {
      // Se cancelou a edição, então reverter as alterações feitas nos status das tarefas.
      this.displayConfirm = false;
      this.reverterAlteracoesDeStatus(sessaoId);
    }
    
    // Atualiza o valor da variável "editar".
    this.editar = editar;
  }

  /**
   * Atualiza uma sessão no banco de dados.
   * @param sessaoId O ID da sessão a ser atualizada.
   * @param sessao A sessão atualizada.
   */
  atualizarSessao(sessaoId: string) {
    const sessao = this.sessoes.find(sessao => sessao.id === sessaoId);
    if (sessao) {
      this.databaseService.put(sessaoId, sessao, SESSAO_COLLECTION).subscribe({
        next: (response: any) => console.log(response),
        error: (error) => console.error(error),
        complete: () => console.log('Sessão editada: ', sessao)
      });
    }
    this.editar = false;
  }

  /**
   * Salva a lista de status das tarefas de uma sessão antes de serem editadas.
   * 
   * @param sessaoId O ID da sessão.
   */
  salvarEstadoDasTarefas(sessaoId: string) {
    this.statusListPreEdicao = [];
    const tarefasModificaveis = this.tarefasPorBrinco[sessaoId];
    Object.keys(tarefasModificaveis).forEach(brinco => {
      tarefasModificaveis[brinco].forEach(tarefa => {
        this.statusListPreEdicao.push(tarefa.status);
      });
    });
  }

  confirmDelete(id: string) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir a sessão?',
      header: 'Confirmação',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Sim',
      rejectLabel: 'Não',
      defaultFocus: 'reject',
      rejectButtonStyleClass: 'p-button-secondary',
      acceptButtonStyleClass: 'p-button-danger',
      accept: () => this.excluirSessao(id)
    });
  }

  excluirSessao(id: string) {
    this.databaseService.delete(id, SESSAO_COLLECTION).subscribe({
      next: (response: any) => console.log(response),
      error: (error) => console.error(error),
      complete: () => {
        this.carregarSessoes();
        console.log('Sessão excluída: ', id);
      }
    });
  }

  calcularProgressoTarefas(): number {  
    const sessao = this.sessoes[this.first];
    const tarefas = this.tarefasPorBrinco[sessao.id];

    const totalTarefas = Object.values(tarefas).reduce((acc, tarefasBrinco) => acc + tarefasBrinco.length, 0);
    const tarefasConcluidas = Object.values(tarefas).reduce((acc, tarefasBrinco) => {
      return acc + tarefasBrinco.filter(tarefa => tarefa.status).length;
    }, 0);

    return (tarefasConcluidas / totalTarefas) * 100;
  }  
}
