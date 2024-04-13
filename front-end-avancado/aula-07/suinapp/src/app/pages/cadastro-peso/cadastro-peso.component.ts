import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataBaseService } from '../../services/database.service';
import { Weight } from '../../models/pig.model';
import { Message } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { PIG_COLLECTION, WEIGHT_COLLECTION } from '../../shared/constants';


@Component({
  selector: 'app-cadastro-peso',
  templateUrl: './cadastro-peso.component.html',
  styleUrls: ['./cadastro-peso.component.css']

})
export class CadastroPesoComponent implements OnInit {
  weightForm!: FormGroup;
  msgs : Message [] = [];

  constructor(private databaseService: DataBaseService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.weightForm = new FormGroup({
      id: new FormControl(null),
      brinco: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]{1,3}$/)]),
      data: new FormControl(null, [Validators.required]),
      peso: new FormControl(null, [Validators.required, Validators.pattern(/^(\d{1,4}|\d{1,4}\.\d{1,3})$/)])
    });

    let pigBrinco = this.route.snapshot.paramMap.get('id');
    
    if (pigBrinco) {
      this.weightForm.patchValue({
        brinco: parseInt(pigBrinco)
      });
    }
  }

  onSubmit() {
    let pig: any = null;
  
    this.databaseService.getByColumn(this.weightForm.value.brinco, PIG_COLLECTION, 'id').subscribe(
      (response) => {
        pig = response;
        console.log("Porco encontrado", pig);
  
        if (Object.keys(pig).length === 0) {
          alert('Porco não encontrado');
          return;
        }
  
        if (this.weightForm.valid) {
          const weight = new Weight(
            this.weightForm.value.id,
            this.weightForm.value.brinco,
            this.weightForm.value.data,
            this.weightForm.value.peso
          );
          this.databaseService.post(weight, WEIGHT_COLLECTION).subscribe(
            (response) => {
              this.msgs = [{severity:'success', summary:'Sucesso', detail:'Peso cadastrado com sucesso'}];
              console.log(response);
            },
            (error) => {
              this.msgs = [{severity:'error', summary:'Erro', detail:'Erro ao cadastrar peso'}];
              console.error(error);
            }
          );
        }
      },
      (error) => {
        console.error(error);
      }
    );
  }  
}
