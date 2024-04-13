import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataBaseService } from '../../services/database.service';
import { Pig } from '../../models/pig.model';
import { ActivatedRoute } from '@angular/router';
import { Message } from 'primeng/api';
import { DatePipe } from '@angular/common';
import { PIG_COLLECTION } from '../../shared/constants';

@Component({
  selector: 'app-editar-suinos',
  templateUrl: './editar-suinos.component.html',
  styleUrls: ['./editar-suinos.component.css']
})
export class EditarSuinosComponent {
  edicaoSuinoForm: FormGroup;
  id: string | null = null;
  pigToEdit: any = null;
  msgs: Message[] = [];
  statusValues = [
    { name: 'Ativo' },
    { name: 'Vendido' },
    { name: 'Morto' }
  ];
  sexoValues = [
    { name: 'M' },
    { name: 'F' }
  ];

  constructor(private databaseService: DataBaseService, private route: ActivatedRoute) {
    this.edicaoSuinoForm = new FormGroup({
      brincoAnimal: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      brincoPai: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      brincoMae: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      dataNasc: new FormControl(null, [Validators.required]),
      dataSaida: new FormControl(null),
      status: new FormControl(null, [Validators.required]),
      sexo: new FormControl(null, [Validators.required])
      })

      this.id = this.route.snapshot.paramMap.get('id');

      this.databaseService.get(this.id!, PIG_COLLECTION).subscribe(
        (response) => {
          this.pigToEdit = response;
          this.edicaoSuinoForm.setValue({
            brincoAnimal: this.pigToEdit.id,
            brincoPai: this.pigToEdit.father,
            brincoMae: this.pigToEdit.mother,
            dataNasc: new Date(this.pigToEdit.birth),
            dataSaida: this.pigToEdit.exit ? new Date(this.pigToEdit.exit) : null,
            status: this.statusValues.find((status) => status.name === this.pigToEdit.status),
            sexo: this.sexoValues.find((sexo) => sexo.name === this.pigToEdit.sex)
          });
        },
        (error) => {
          console.error(error);
        }
      );
  }

  onSubmit(){
    console.log(this.edicaoSuinoForm.value.dataNasc);
   
    const pig = new Pig(
      this.edicaoSuinoForm.value.brincoAnimal,
      this.edicaoSuinoForm.value.brincoPai,
      this.edicaoSuinoForm.value.brincoMae,
      this.edicaoSuinoForm.value.dataNasc, 
      this.edicaoSuinoForm.value.dataSaida,
      this.edicaoSuinoForm.value.status.name,
      this.edicaoSuinoForm.value.sexo.name
    );
    this.databaseService.put(this.id!, pig, PIG_COLLECTION).subscribe(
      (response) => {
        this.msgs = [{ severity: 'success', summary: 'Sucesso', detail: 'Suíno editado com sucesso!' }];
        console.log(response);
      },
      (error) => {
        this.msgs = [{ severity: 'error', summary: 'Erro', detail: 'Erro ao editar suíno!' }];
        console.log(error);
      }
    );

  }
}
