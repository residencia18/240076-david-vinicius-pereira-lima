import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataBaseService } from '../../services/database.service';
import { Pig } from '../../models/pig.model';
import { Message } from 'primeng/api';
import { PIG_COLLECTION } from '../../shared/constants';

@Component({
  selector: 'app-cadastro-suino',
  templateUrl: './cadastro-suino.component.html',
  styleUrls: ['./cadastro-suino.component.css']
})
export class CadastroSuinoComponent {
  cadastroSuinoForm: FormGroup;
  statusValues = [
    { name: 'Ativo' },
    { name: 'Vendido' },
    { name: 'Morto' }
  ];
  sexoValues = [
    { name: 'M' },
    { name: 'F' }
  ];
  msgs : Message[] = [];

  constructor(private databaseService: DataBaseService) {
    this.cadastroSuinoForm = new FormGroup({
      brincoAnimal: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      brincoPai: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      brincoMae: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]*$/)]),
      dataNasc: new FormControl(null, [Validators.required]),
      dataSaida: new FormControl(null),
      status: new FormControl(null, [Validators.required]),
      sexo: new FormControl(null, [Validators.required])
      })

      this.cadastroSuinoForm.valueChanges.subscribe(
        (value) => {
          console.log(value);
        }
      );
  }

  onSubmit(){
    const pig = new Pig(
      this.cadastroSuinoForm.value.brincoAnimal,
      this.cadastroSuinoForm.value.brincoPai,
      this.cadastroSuinoForm.value.brincoMae,
      this.cadastroSuinoForm.value.dataNasc,
      this.cadastroSuinoForm.value.dataSaida,
      this.cadastroSuinoForm.value.status.name,
      this.cadastroSuinoForm.value.sexo.name
    );
    this.databaseService.post(pig, PIG_COLLECTION).subscribe(
      (response) => {
        this.msgs = [{severity:'success', summary:'Sucesso', detail:'Suino cadastrado com sucesso'}];
        console.log(response);
      },
      (error) => {
        this.msgs = [{severity:'error', summary:'Erro', detail:'Erro ao cadastrar suino'}];
        console.log(error);
      }
    );

  }
  
}
