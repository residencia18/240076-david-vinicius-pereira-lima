import { Component, OnInit } from '@angular/core';
import { GetPaisesService } from '../get-paises.service';
import { Pais } from '../models/pais.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-paises-json',
  templateUrl: './paises-json.component.html',
  styleUrl: './paises-json.component.css'
})
export class PaisesJsonComponent implements OnInit{
  
  paisForm : FormGroup;

  ngOnInit(){
    this.onInit();
  }
  
  constructor(private getPaises : GetPaisesService){
    this.paisForm = new FormGroup({
      tipo:new FormControl(null,[Validators.required]),
      nome:new FormControl(null,[Validators.required]),
      rotulo:new FormControl(null,[Validators.required]),
    })

    this.paisForm.valueChanges.subscribe(
      (value)=>{
        console.log(value);
      }
    );
  }

  onInit(){
    const pais = new Pais(
      this.paisForm.value.tipo,
      this.paisForm.value.nome,
      this.paisForm.value.rotulo
    );
    this.getPaises.getFirstPais().subscribe(
      (response)=>{
        console.log(response);
      },
      (error)=>{
        console.log(error);
      }
    );
  }
  
}
