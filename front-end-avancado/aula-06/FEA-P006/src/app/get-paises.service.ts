import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pais } from './models/pais.model';

@Injectable({
  providedIn: 'root'
})
export class GetPaisesService {

  constructor(private http : HttpClient) { }

  getFirstPais(){
    return this.http.get<Pais[]>("https://restcountries.com/v3.1/all");
  }


}
