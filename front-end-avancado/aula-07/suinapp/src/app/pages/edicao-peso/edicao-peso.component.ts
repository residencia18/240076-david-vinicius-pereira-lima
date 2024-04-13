import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataBaseService } from '../../services/database.service';
import { ActivatedRoute } from '@angular/router';
import { Weight } from '../../models/pig.model';
import { Message } from 'primeng/api';
import { WEIGHT_COLLECTION } from '../../shared/constants';

@Component({
  selector: 'app-edicao-peso',
  templateUrl: './edicao-peso.component.html',
  styleUrls: ['./edicao-peso.component.css']
})
export class EdicaoPesoComponent {
  weightForm!: FormGroup;
  id: string | null = null;
  weightToEdit: any = null;
  msgs : Message[] = [];
  
  constructor(private databaseService: DataBaseService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.weightForm = new FormGroup({
      brinco: new FormControl(null, [Validators.required, Validators.pattern(/^[0-9]{1,3}$/)]),
      data: new FormControl(null, [Validators.required]),
      peso: new FormControl(null, [Validators.required, Validators.pattern(/^(\d{1,4}|\d{1,4}\.\d{1,3})$/)])
    });

    this.id = this.route.snapshot.paramMap.get('id');

    this.databaseService.get(this.id!, WEIGHT_COLLECTION).subscribe(
      (response) => {
        this.weightToEdit = response;
        console.log(this.weightToEdit);
        this.weightForm.setValue({
          brinco: this.weightToEdit.pig,
          data: new Date(this.weightToEdit.date),
          peso: this.weightToEdit.weight
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }

  onSubmit() {
    if (this.weightForm.valid) {
      console.log(this.weightForm.value.data);
      const weight = new Weight(
        this.weightToEdit.id,        
        this.weightForm.value.brinco,
        this.weightForm.value.data,
        this.weightForm.value.peso
      );
      console.log(weight);
      this.databaseService.put(this.id!, weight, WEIGHT_COLLECTION).subscribe(
        (response) => {
          this.msgs = [{severity:'success', summary:'Sucesso', detail:'Peso editado com sucesso'}];
          console.log(response);
        },
        (error) => {
          this.msgs = [{severity:'error', summary:'Erro', detail:'Erro ao editar peso'}];
          console.error(error);
        }
      );
    }
  }  
}
