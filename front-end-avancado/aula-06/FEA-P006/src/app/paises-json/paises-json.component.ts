import { Component, OnInit } from '@angular/core';
import { GetPaisesService } from '../get-paises.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-paises-json',
  templateUrl: './paises-json.component.html',
  styleUrl: './paises-json.component.css'
})
export class PaisesJsonComponent implements OnInit {

  camposForm: any[] = [];
  formPais: FormGroup = new FormGroup({});

  constructor(private formBuilder : FormBuilder, private getPaisesService: GetPaisesService) { }

  ngOnInit(): void {
    this.getPaisesService.getFirstPais().subscribe(
      camposForm => {
        this.camposForm = camposForm;
        this.camposForm.forEach(campo => {
          this.formPais.addControl(campo.nome, this.formBuilder.control('', Validators.required));
        });

      },
      error => {
        console.log(error);
      });
    }

    onSubmit(){
      if(this.formPais.valid){
        console.log(this.formPais.value);
      } else {
        console.log("Formulário inválido");
      }
    }
  
}
