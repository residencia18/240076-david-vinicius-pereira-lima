import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Message } from 'primeng/api';
import { DataBaseService } from '../../services/database.service';
import { Sessao } from '../../models/sessao.model';
import { Observer } from 'rxjs';
import { PIG_COLLECTION, SESSAO_COLLECTION } from '../../shared/constants';

@Component({
  selector: 'app-cadastro-sessao',
  templateUrl: './cadastro-sessao.component.html',
  styleUrls: ['./cadastro-sessao.component.css']
})
export class CadastroSessaoComponent {
  sessaoForm!: FormGroup;
  pigValues: any[] = [];
  taskValues = [
    { name: 'Vacinação Contra Raiva' },
    { name: 'Dar Banho' },
    { name: 'Alimentar' }
  ];
  msgs: Message[] = [];
  constructor(private dataBaseService: DataBaseService) {

  }

  ngOnInit(): void {
    this.sessaoForm = new FormGroup({
      id: new FormControl(null),
      data: new FormControl(null, [Validators.required]),
      descricao: new FormControl(null, [Validators.required]),
      brincos: new FormControl(null, [Validators.required]),
      tarefas: new FormControl(null, [Validators.required])
    });
    this.getAllBrincos();
  }

  onSubmit() {
    // convertendo os valores dos brincos e tarefas para um array de strings
    const brincos = this.sessaoForm.value.brincos.map((item: any) => item.name);
    const tarefas = this.sessaoForm.value.tarefas.map((item: any) => item.name);

    if (this.sessaoForm.valid) {
      const sessao : Sessao = {
        id: this.sessaoForm.value.id,
        data: this.sessaoForm.value.data,
        descricao: this.sessaoForm.value.descricao,
        brincos: brincos,
        tarefas: brincos.flatMap((brinco: any) =>
          tarefas.map((tarefa: any) => ({ brinco, descricao: tarefa, status: false }))
        )
      };

      // observer para tratar a resposta do servidor
      const observer: Observer<any> = {
        next: (response) => {
          console.log(response);
        },
        error: (error) => {
          this.msgs = [{ severity: 'error', summary: 'Erro', detail: 'Erro ao cadastrar a sessão' }];
          console.error(error);
        },
        complete: () => {
          this.msgs = [{ severity: 'success', summary: 'Sucesso', detail: 'Sessão cadastrada com sucesso' }];
        }
      };
      this.dataBaseService.post(sessao, SESSAO_COLLECTION).subscribe(observer);
    }
  }

  getAllBrincos(): void {
    const observer: Observer<any> = {
      next: (response) => {
        this.pigValues = Object.values(response).map((item: any) => ({ name: item.id }));
      },
      error: (error) => {
        console.error(error);
      },
      complete: () => {
        console.log('complete');
      }
    };

    this.dataBaseService.getAll(PIG_COLLECTION).subscribe(observer);
  }

}
